package temportalist.automata;

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
		this.applyCurseGradle(project);

		this.applyExtensions(project);
		this.applyTasks(project);

	}

	private void applyForge(final Project project) {
		Map<String, String> mapPluginForge = new HashMap<>();
		mapPluginForge.put("plugin", "net.minecraftforge.gradle.forge");
		project.apply(mapPluginForge);
	}

	private void applyCurseGradle(final Project project) {
		Map<String, String> mapPlugin = new HashMap<>();
		mapPlugin.put("plugin", "com.matthewprenger.cursegradle");
		project.apply(mapPlugin);
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
