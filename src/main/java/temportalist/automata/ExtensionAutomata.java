package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginConvention;
import org.gradle.api.tasks.SourceSet;
import org.gradle.api.tasks.SourceSetContainer;
import org.gradle.language.jvm.tasks.ProcessResources;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
public class ExtensionAutomata {

	private final Project project;
	private final ForgeExtension minecraft;

	public ExtensionAutomata(final Project project) {
		this.project = project;
		this.minecraft = project.getExtensions().findByType(ForgeExtension.class);
	}

	String message = "This is a ExtensionAutomata message";
	Object sampleEXT = null;

	String organization, groupName, archiveName;
	int versionMajor = 0, versionMinor = 0, versionPatch = 0;

	String versionMinecraft, versionForge, versionMappings;

	String curseID = null;
	String curseBuildType = null;

	String runDir = "run";

	public void setMessage(String message) {
		this.message = message;
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

	public void setArchiveName(String name) {
		this.archiveName = name;
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

	private void setVersionString() {
		this.project.setVersion(String.format(
				"%1$d.%2$d.%3$d",
				this.versionMajor, this.versionMinor, this.versionPatch
		));
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

	public void setCurseID(String curseID) {
		this.curseID = curseID;
	}

	public void setCurseBuildType(String curseBuildType) {
		this.curseBuildType = curseBuildType;
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
