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
		this.applyMaven(project);

		this.applyExtensions(project);
		this.applyTasks(project);

	}

	private void applyPlugin(final Project project, String id) {
		Map<String, String> mapPlugin = new HashMap<>();
		mapPlugin.put("plugin", id);
		project.apply(mapPlugin);
	}

	private void applyForge(final Project project) {
		this.applyPlugin(project, "net.minecraftforge.gradle.forge");
	}

	private void applyCurseGradle(final Project project) {
		this.applyPlugin(project, "com.matthewprenger.cursegradle");
	}

	private void applyMaven(final Project project) {
		this.applyPlugin(project, "maven");
	}

	private void applyExtensions(final Project project) {
		ExtensionAutomata automataEXT = new ExtensionAutomata(project);
		project.getExtensions().add("automata", automataEXT);
	}

	private void applyTasks(final Project project) {
		TaskDetailsDisplay taskDisplay =
				project.getTasks().create("displayDetails", TaskDetailsDisplay.class);
	}

}
