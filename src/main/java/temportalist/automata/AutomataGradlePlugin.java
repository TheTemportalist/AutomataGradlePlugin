package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
import org.gradle.api.Plugin;
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
public class AutomataGradlePlugin implements Plugin<Project> {

	@Override
	public void apply(final Project project) {

		// START: testing extensions and tasks

		ExtensionAutomata automataEXT = new ExtensionAutomata();
		project.getExtensions().add("automata", automataEXT);
		project.getTasks().create("displayDetails", TaskDisplayDetails.class);

		// END

		project.setGroup(String.format(
				"%1$s.%2$s", automataEXT.organization, automataEXT.groupName
		));
		project.setVersion("0.0.1");

		automataEXT.minecraft = project.getExtensions().findByType(ForgeExtension.class);
		automataEXT.minecraft.setVersion("1.9.4-12.17.0.1932-1.9.4");
		automataEXT.minecraft.setRunDir("runInThisDir");
		automataEXT.minecraft.setMappings("snapshot_20160518");

		Object processResourcesObj = project.getTasks().findByName("processResources");
		if (processResourcesObj instanceof ProcessResources) {
			automataEXT.processResources = (ProcessResources) processResourcesObj;

			automataEXT.processResources.getInputs().property(
					"version", project.getVersion()
			);
			automataEXT.processResources.getInputs().property(
					"mcversion", automataEXT.minecraft.getVersion()
			);

			SourceSetContainer sets = project.getConvention().getPlugin(JavaPluginConvention.class).getSourceSets();
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
