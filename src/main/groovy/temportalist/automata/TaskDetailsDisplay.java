package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.artifacts.repositories.ArtifactRepository;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;
import org.gradle.language.jvm.tasks.ProcessResources;

import java.util.Iterator;
import java.util.Map;

import static java.lang.System.out;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
public class TaskDetailsDisplay extends DefaultTask {

	@TaskAction
	public void run() throws TaskExecutionException {

		out.println("----------");
		try {
			Project project = this.getProject();
			out.println("Group: " + project.getGroup());
			out.println("Version: " + project.getVersion());

			out.println();

			out.println("Repositories;");
			Iterator<ArtifactRepository> iterator = project.getRepositories().iterator();
			while (iterator.hasNext()) {
				ArtifactRepository repo = iterator.next();
				out.println("\t" + repo.getName());
			}

			out.println();

			ForgeExtension minecraft = project.getExtensions().findByType(ForgeExtension.class);
			if (minecraft != null) {
				out.println("Minecraft:");
				out.println("Version: " + minecraft.getVersion());
				out.println("Forge: " + minecraft.getForgeVersion());
				out.println("Run Dir: " + minecraft.getRunDir());
				out.println("Mappings: " + minecraft.getMappings());
				out.println("Replacements;");
				for (Map.Entry<String, Object> replace : minecraft.getReplacements().entrySet()) {
					out.println("\t\"" + replace.getKey() + "\": \"" + replace.getValue() + "\"");
				}
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
					new Exception("Exception occurred while processing displayDetails", e)
			);
		}
		finally {
			out.println("----------");
		}
	}

}
