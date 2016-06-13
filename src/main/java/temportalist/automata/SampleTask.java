package temportalist.automata;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

import static java.lang.System.out;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
public class SampleTask extends DefaultTask {

	@TaskAction
	public void run() throws TaskExecutionException {
		try {
			SamplePluginExtension extension = this.getProject().getExtensions().findByType(SamplePluginExtension.class);
			out.println(extension.message);

			out.println(extension.sampleEXT);
			if (extension.sampleEXT != null) {
				out.println(extension.sampleEXT.getClass().getName());
			}

			out.println("----------");

			Project project = extension.getProject();
			out.println("Group: " + project.getGroup());
			out.println("Version: " + project.getVersion());

			out.println();

			if (extension.minecraft != null) {
				out.println("Minecraft:");
				out.println("Version: " + extension.minecraft.getVersion());
				out.println("Run Dir: " + extension.minecraft.getRunDir());
				out.println("Mappings: " + extension.minecraft.getMappings());
				out.println();
			}

			if (extension.processResources != null) {
				out.println("Process Resources:");

				out.println("Version: " +
						extension.processResources.getInputs().getProperties().get("version")
				);
				out.println("MCVersion: " +
						extension.processResources.getInputs().getProperties().get("mcversion")
				);

				out.println();
			}

		}
		catch(Exception e) {
			throw new TaskExecutionException(this,
					new Exception("Exception occurred while processing sampleTask", e)
			);
		}
	}

}
