package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgePlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
public class AutomataGradlePlugin implements Plugin<Project> {

	@Override
	public void apply(final Project project) {
		this.applyForge(project);

		// START: testing extensions and tasks

		this.applyExtensions(project);
		this.applyTasks(project);

		ForgePlugin forge = project.getPlugins().findPlugin(ForgePlugin.class);

	}

	private void applyForge(final Project project) {
		Map<String, String> mapPluginForge = new HashMap<>();
		mapPluginForge.put("plugin", "net.minecraftforge.gradle.forge");
		project.apply(mapPluginForge);
	}

	private void applyExtensions(final Project project) {
		ExtensionAutomata automataEXT = new ExtensionAutomata(project);
		project.getExtensions().add("automata", automataEXT);
	}

	private void applyTasks(final Project project) {
		TaskDetailsLoad taskLoad =
				project.getTasks().create("loadAutomata", TaskDetailsLoad.class);
		TaskDetailsDisplay taskDisplay =
				project.getTasks().create("displayDetails", TaskDetailsDisplay.class);
	}

}
