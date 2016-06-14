package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;
import org.gradle.language.jvm.tasks.ProcessResources;

import static java.lang.System.out;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
public class TaskDetailsDisplay extends DefaultTask {

	@TaskAction
	public void run() throws TaskExecutionException {
		try {
			ExtensionAutomata extension = this.getProject().getExtensions().findByType(ExtensionAutomata.class);
			out.println(extension.message);

			out.println(extension.sampleEXT);
			if (extension.sampleEXT != null) {
				out.println(extension.sampleEXT.getClass().getName());
			}

			out.println("----------");

			out.println(extension.organization);

			Project project = this.getProject();
			out.println("Group: " + project.getGroup());
			out.println("Version: " + project.getVersion());

			out.println();

			ForgeExtension minecraft = project.getExtensions().findByType(ForgeExtension.class);
			if (minecraft != null) {
				out.println("Minecraft:");
				out.println("Version: " + minecraft.getVersion());
				out.println("Forge: " + minecraft.getForgeVersion());
				out.println("Run Dir: " + minecraft.getRunDir());
				out.println("Mappings: " + minecraft.getMappings());
				out.println();
			}

			Object processResourcesObj = project.getTasks().findByName("processResources");
			if (processResourcesObj != null &&
					processResourcesObj instanceof ProcessResources) {
				ProcessResources processResources = (ProcessResources) processResourcesObj;

				out.println("Process Resources:");

				out.println("Version: " +
						processResources.getInputs().getProperties().get("version")
				);
				out.println("MCVersion: " +
						processResources.getInputs().getProperties().get("mcversion")
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
