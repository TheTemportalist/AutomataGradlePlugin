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

	String message = "This is a ExtensionAutomata message";
	Object sampleEXT = null;

	String organization, groupName, archiveName;
	int versionMajor, versionMinor, versionPatch;

	String version_minecraft, version_forge, version_forge_b = null, version_mcp;

	String curseID = null;
	String curseBuildType = null;

	String runDir = "run";

	public void loadInto(Project project) {
		project.setGroup(String.format(
				"%1$s.%2$s", this.organization, this.groupName
		));
		project.setVersion(String.format(
				"%1$d.%2$d.%3$d",
				this.versionMajor, this.versionMinor, this.versionPatch
		));

		ForgeExtension minecraft = project.getExtensions().findByType(ForgeExtension.class);
		minecraft.setVersion(String.format("%1$s-%2$s",
				this.version_minecraft,
				this.version_forge_b == null
						? this.version_forge
						: this.version_forge + "-" + this.version_forge_b
		));
		minecraft.setRunDir(this.runDir);
		minecraft.setMappings(this.version_mcp);

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
