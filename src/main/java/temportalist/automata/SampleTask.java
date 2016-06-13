package temportalist.automata;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.TaskExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
public class SampleTask extends DefaultTask {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@TaskAction
	public void samplePluginTasks() throws TaskExecutionException {
		try {
			SamplePluginExtension extension = this.getProject().getExtensions().findByType(SamplePluginExtension.class);
			this.log.info("Completed sampleTask");
		}
		catch(Exception e) {
			throw new TaskExecutionException(this,
					new Exception("Exception occured while processing sampleTask", e)
			);
		}
	}

}
