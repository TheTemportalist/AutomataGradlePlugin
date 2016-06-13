package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
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

		SamplePluginExtension sampleEXT = new SamplePluginExtension();
		project.getExtensions().add("sampleExtension", sampleEXT);
		project.getTasks().create("sampleTask", SampleTask.class);

		// END

		project.setVersion("0.0.1");

		sampleEXT.minecraft = project.getExtensions().findByType(ForgeExtension.class);
		sampleEXT.minecraft.setForgeVersion("1.9.4-12.17.0.1932-1.9.4");
		sampleEXT.minecraft.setRunDir("runInThisDir");
		sampleEXT.minecraft.setMappings("snapshot_20160518");

		sampleEXT.sampleEXT = project.getExtensions().findByName("sourceSets");

		Object processResourcesObj = project.getTasks().findByName("processResources");
		if (processResourcesObj instanceof ProcessResources) {
			sampleEXT.processResources = (ProcessResources) processResourcesObj;

			sampleEXT.processResources.getInputs().property(
					"version", project.getVersion()
			);
			sampleEXT.processResources.getInputs().property(
					"mcversion", sampleEXT.minecraft.getForgeVersion()
			);



		}

	}

}
