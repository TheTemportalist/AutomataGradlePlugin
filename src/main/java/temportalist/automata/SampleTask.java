package temportalist.automata;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

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
			System.out.println(extension.message);

			System.out.println(extension.sampleEXT);
			if (extension.sampleEXT != null) {
				System.out.println(extension.sampleEXT.getClass().getName());
			}

			if (extension.minecraft != null) {
				System.out.println(extension.minecraft.getForgeVersion());
				System.out.println(extension.minecraft.getRunDir());
			}

			if (extension.processResources != null) {
				System.out.println(
						extension.processResources.getInputs().getProperties().get("version")
				);
				System.out.println(
						extension.processResources.getInputs().getProperties().get("mcversion")
				);

			}

		}
		catch(Exception e) {
			throw new TaskExecutionException(this,
					new Exception("Exception occurred while processing sampleTask", e)
			);
		}
	}

}
