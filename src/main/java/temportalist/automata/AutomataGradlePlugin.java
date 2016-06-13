package temportalist.automata;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
public class AutomataGradlePlugin implements Plugin<Project> {

	@Override
	public void apply(final Project project) {

		final Task testTask = project.getTasks().create("testAutomata", TestTask.class);
		testTask.setDescription("Test task to test the plugin");
		testTask.setGroup("build");

	}

}
