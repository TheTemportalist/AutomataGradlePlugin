package temportalist.automata;

import com.matthewprenger.cursegradle.CurseExtension;
import groovy.lang.Closure;
import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.language.jvm.tasks.ProcessResources;

/**
 *
 * Created by TheTemportalist on 6/12/2016.
 * @author TheTemportalist
 */
public class ExtensionAutomata {

	private final Project project;
	private final ForgeExtension minecraft;
	private final CurseExtension curseforge;

	// General project vars
	String organization, groupName;
	// Project versioning using Semver
	int versionMajor = 0, versionMinor = 0, versionPatch = 0;
	private boolean isManualBuild = false, isAutoBuild = false;
	int manualBuildNumber = 0, autoBuildNumber = 0;

	String versionMinecraft, versionForge, runDir = "run", versionMappings;

	PropertyReplace replace;

	String curseKey = null;

	PropertyArchives archives;

	public ExtensionAutomata(final Project project) {
		this.project = project;
		this.minecraft = project.getExtensions().findByType(ForgeExtension.class);
		this.curseforge = project.getExtensions().findByType(CurseExtension.class);
		this.init();
	}

	private void init() {

		this.minecraft.setRunDir(this.runDir);

		this.replace = new PropertyReplace();

		// Makes sure CurseGradle doesn't complain if the user doesn't use curse
		this.curseforge.setApiKey("");

		this.archives = new PropertyArchives();

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
				"%1$s.%2$s", this.organization, this.groupName
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

	public void setManualBuildNumber(int number) {
		this.manualBuildNumber = number;
		this.isManualBuild = true;
		this.setVersionString();
	}

	public void setAutoBuildNumber(int number) {
		this.autoBuildNumber = number;
		this.isAutoBuild = true;
		this.setVersionString();
	}

	private void setVersionString() {
		String versionStr = String.format(
				"%1$d.%2$d.%3$d",
				this.versionMajor, this.versionMinor, this.versionPatch
		);
		if (this.isManualBuild) versionStr += "b" + this.manualBuildNumber;
		if (this.isAutoBuild) versionStr += "." + this.autoBuildNumber;
		this.project.setVersion(versionStr);
	}

	public void setVersionMinecraft(String versionMinecraft) {
		this.versionMinecraft = versionMinecraft;
		this.checkForgeVersions();
	}

	public void setVersionForge(String versionForge) {
		this.versionForge = versionForge;
		this.checkForgeVersions();
	}

	private void checkForgeVersions() {
		if (this.versionMinecraft == null || this.versionForge == null)
			return;
		this.minecraft.setVersion(String.format("%1$s-%2$s",
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
		closure.setResolveStrategy(Closure.DELEGATE_FIRST);
		closure.setDelegate(this.replace);
		closure.call();
	}

	public void setCurseKey(String curseKey) {
		this.curseKey = curseKey;
		this.curseforge.setApiKey(this.curseKey);
	}

	public void curseProject(Closure<?> closure) {
		this.curseforge.project(closure);
	}

	public void archives(Closure<?> closure) {
		closure.setResolveStrategy(Closure.DELEGATE_FIRST);
		closure.setDelegate(this.archives);
		closure.call();
		this.archives.setupArchives(this.project);
	}

	public void loadInto(Project project) {

		Object processResourcesObj = project.getTasks().findByName("processResources");
		if (processResourcesObj instanceof ProcessResources) {
			ProcessResources processResources = (ProcessResources) processResourcesObj;

			processResources.getInputs().property(
					"version", project.getVersion()
			);
			processResources.getInputs().property(
					"mcversion", minecraft.getVersion()
			);

			SourceSetContainer sets = project.getConvention().getPlugin(JavaPluginConvention.class)
					.getSourceSets();
			SourceSet mainSet = sets.findByName("main");
			/* TODO
				from(sourceSets.main.resources.srcDirs) {
					include 'mcmod.info'
					expand 'version': project.version, 'mcversion': project.minecraft.version
				}

				from(sourceSets.main.resources.srcDirs) {
					exclude 'mcmod.info'
				}
			*/

		}
	}

}
