package temportalist.automata

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *
 * Created by TheTemportalist on 6/15/2016.
 * @author TheTemportalist
 */
class PluginAutomata implements Plugin<Project> {

	static ExtensionAutomata automataEXT;

	@Override
	void apply(Project project) {
		applyForge(project);
		applyCurseGradle(project);
		applyMaven(project);
		applyGitChangelog(project);

		applyExtensions(project);
		applyTasks(project);

		project.afterEvaluate {
			automataEXT.onAfterEvaluate()
		}
	}

	private static void applyPlugin(final Project project, String id) {
		Map<String, String> mapPlugin = new HashMap<>();
		mapPlugin.put("plugin", id);
		project.apply(mapPlugin);
	}

	private static void applyForge(final Project project) {
		applyPlugin(project, "net.minecraftforge.gradle.forge");
	}

	private static void applyCurseGradle(final Project project) {
		applyPlugin(project, "com.matthewprenger.cursegradle");
	}

	private static void applyMaven(final Project project) {
		applyPlugin(project, "maven");
	}

	private static def applyGitChangelog(Project project) {
		applyPlugin(project, "se.bjurr.gitchangelog.git-changelog-gradle-plugin")
	}

	private static void applyExtensions(final Project project) {
		automataEXT = new ExtensionAutomata(project);
		project.getExtensions().add("automata", automataEXT);
	}

	private static void applyTasks(final Project project) {
		TaskDetailsDisplay taskDisplay =
				project.getTasks().create("displayDetails", TaskDetailsDisplay);
	}

}
