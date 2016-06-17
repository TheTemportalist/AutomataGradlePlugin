package temportalist.automata

import com.matthewprenger.cursegradle.CurseExtension
import com.matthewprenger.cursegradle.CurseProject
import com.matthewprenger.cursegradle.CurseUploadTask
import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension
import org.gradle.api.Project

/**
 *
 * Created by TheTemportalist on 6/15/2016.
 * @author TheTemportalist
 */
class ExtensionAutomata {

	private final Project project;
	private final ForgeExtension minecraft;
	private final CurseExtension curseforge;

	PropertyDependencies dependencies;

	// General project vars
	String organization, groupName;
	// Project versioning using Semver
	int versionMajor = 0, versionMinor = 0, versionPatch = 0;
	String manualBuildNumber = null, autoBuildNumber = null;

	String versionMinecraft, versionForge, runDir = "run", versionMappings;

	PropertyReplace replace;
	boolean resources;

	String curseKey = null;

	Map<String, PropertyChangelog> changelogs;

	PropertyArchives archives;

	public ExtensionAutomata(final Project project) {
		this.project = project;
		this.minecraft = project.getExtensions().findByType(ForgeExtension)
		this.curseforge = project.getExtensions().findByType(CurseExtension);
		this.init();
	}

	private void init() {

		this.minecraft.setRunDir(this.runDir);

		// Makes sure CurseGradle doesn't complain if the user doesn't use curse
		this.curseforge.setApiKey("");

		this.changelogs = new HashMap<>()

	}

	public void dependencies(Closure<?> closure) {
		this.dependencies = new PropertyDependencies()
		this.dependencies.with(closure)
		this.dependencies.load(this.project, this.minecraft)
	}

	public void setOrganization(String org) {
		this.organization = org;
		this.checkGroup();
	}

	public void setGroupName(String name) {
		this.groupName = name;
		this.checkGroup();
	}

	private void checkGroup() {
		if (this.organization == null || this.groupName == null)
			return;
		this.project.setGroup(String.format(
				"%s.%s", this.organization, this.groupName
		));
	}

	public void setVersionMajor(int versionMajor) {
		this.versionMajor = versionMajor;
		this.setVersionString();
	}

	public void setVersionMinor(int versionMinor) {
		this.versionMinor = versionMinor;
		this.setVersionString();
	}

	public void setVersionPatch(int versionPatch) {
		this.versionPatch = versionPatch;
		this.setVersionString();
	}

	public void setManualBuildNumber(String number) {
		this.manualBuildNumber = number;
		this.setVersionString();
	}

	public void setAutoBuildNumber(String number) {
		this.autoBuildNumber = number;
		this.setVersionString();
	}

	private void setVersionString() {
		if (this.versionMinecraft == null) return
		String versionStr = this.versionMinecraft + "-" + this.getVersionPure()
		this.project.setVersion(versionStr)
	}

	public String getVersion() {
		this.setVersionString()
		return this.project.getVersion()
	}

	public String getVersionPure() {
		String versionStr = this.getVersionNoBuild()
		if (this.manualBuildNumber != null) versionStr += "b" + this.manualBuildNumber
		if (this.autoBuildNumber != null) versionStr += "." + this.autoBuildNumber
		return versionStr
	}

	public String getVersionNoBuild() {
		return String.format(
				"%d.%d.%d",
				this.versionMajor, this.versionMinor, this.versionPatch
		)
	}

	public void setVersionMinecraft(String versionMinecraft) {
		this.versionMinecraft = versionMinecraft
		this.setVersionString()
		this.checkForgeVersions()
	}

	public String getVersionMinecraft() {
		return this.versionMinecraft
	}

	public void setVersionForge(String versionForge) {
		this.versionForge = versionForge;
		this.checkForgeVersions();
	}

	private void checkForgeVersions() {
		if (this.versionMinecraft == null || this.versionForge == null)
			return;
		this.minecraft.setVersion(String.format("%s-%s",
				this.versionMinecraft, this.versionForge
		));
	}

	public void setRunDir(String runDir) {
		this.runDir = runDir;
		this.minecraft.setRunDir(this.runDir);
	}

	public void setVersionMappings(String versionMappings) {
		this.versionMappings = versionMappings;
		this.minecraft.setMappings(this.versionMappings);
	}

	public void replace(Closure<?> closure) {
		this.replace = new PropertyReplace()
		this.replace.with closure

		Map<String, Object> replaceMap = new HashMap<>()
		replaceMap.putAll(this.replace.replaceMap)
		replaceMap.put(this.replace.version, this.getVersionPure())
		replaceMap.put(this.replace.forge,
				this.replace.forge + "@[" + this.getVersionForge() + ",)")
		this.minecraft.replace(replaceMap)
	}

	void setResources(boolean resources) {
		this.resources = resources
		if (!this.resources) return
		project.processResources {
			inputs.property "version", this.getVersionPure()
			inputs.property "mcversion", this.minecraft.getVersion()

			from(project.sourceSets.main.resources.srcDirs) {
				include 'mcmod.info'
				expand 'version': this.getVersionPure(), 'mcversion': this.minecraft.getVersion()
			}

			from(project.sourceSets.main.resources.srcDirs) {
				exclude 'mcmod.info'
			}

		}
	}

	def changelog(Closure<?> closure) {
		def changelog = new PropertyChangelog()
		changelog.with(closure)
		changelog.createTask(this.project, this)
		this.changelogs.put(changelog.getId(), changelog)
	}

	public void setCurseKey(String curseKey) {
		this.curseKey = curseKey;
		this.curseforge.setApiKey(this.curseKey);
	}

	public void curseProject(Closure<?> closure) {

		// todo work around for https://github.com/matthewprenger/CurseGradle/pull/7
		// this.curseforge.project(closure)
		CurseProject curseProject = new CurseProject()
		curseProject.with(closure)
		if (curseProject.apiKey == null) {
			curseProject.apiKey = this.curseforge.apiKey
		}
		this.curseforge.curseProjects.add(curseProject)

		if (curseProject.changelog != null) return

		def changelogObj
		if (this.changelogs.containsKey(curseProject.getId())) {
			PropertyChangelog changelogProp = this.changelogs.get(curseProject.getId())
			changelogObj = project.file(changelogProp.getFileName())
		} else {
			changelogObj = "No changelog defined"
		}
		curseProject.changelog = changelogObj

	}

	public void archives(Closure<?> closure) {
		this.archives = new PropertyArchives()
		this.archives.with closure
		this.archives.setupArchives(this.project);
	}

	// ~~~ Start: Miscellaneous Functions

	def getBranch() {
		if (project.hasProperty("GIT_BRANCH"))
			return "${GIT_BRANCH}"
		else {
			def proc = "git rev-parse --abbrev-ref HEAD".execute()
			proc.waitFor()
			return proc.text.trim()
		}
	}

	def getDate() {
		def date = new Date()
		def formattedDate = date.format('MM_dd_yyyy_HH_mm_ss')
		return formattedDate
	}

	def getBuildNumber() {
		if (System.getenv("BUILD_NUMBER") != null) return "${System.getenv("BUILD_NUMBER")}"
		else if (project.hasProperty("bambooBuildNumber")) return project.bambooBuildNumber
		else return getDate()
	}

	def onAfterEvaluate() {

		for (CurseProject curseProject : this.curseforge.getCurseProjects()) {
			CurseUploadTask curseforgeTask = curseProject.uploadTask
			if (curseforgeTask != null) {
				this.changelogs.get(curseProject.getId()).linkToCurseforge(curseforgeTask)
			} else
				System.out.println("ERROR: curseforge" + curseProject.getId() +
						" (CurseUploadTask) does not exist.")
		}
	}

	// ~~~~~ End: Miscellaneous Functions

}
