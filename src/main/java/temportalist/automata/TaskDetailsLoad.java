package temportalist.automata;

import org.gradle.api.DefaultTask;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
public class TaskDetailsLoad extends DefaultTask {

	@TaskAction
	public void run() throws TaskExecutionException {
		try {
			Project project = this.getProject();
			ExtensionAutomata automataEXT = project.getExtensions().findByType(ExtensionAutomata.class);

		}
		catch(Exception e) {
			throw new TaskExecutionException(this,
					new Exception("Exception occurred while processing sampleTask", e)
			);
		}
	}

}
