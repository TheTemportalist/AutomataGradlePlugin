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
	public void run() throws TaskExecutionException {
		try {
			SamplePluginExtension extension = this.getProject().getExtensions().findByType(SamplePluginExtension.class);
			System.out.println(extension.message);
			System.out.println(extension.minecraftEXT);
		}
		catch(Exception e) {
			throw new TaskExecutionException(this,
					new Exception("Exception occurred while processing sampleTask", e)
			);
		}
	}

}
