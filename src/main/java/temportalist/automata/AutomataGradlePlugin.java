package temportalist.automata;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
public class AutomataGradlePlugin implements Plugin<Project> {

	@Override
	public void apply(final Project project) {

		project.getExtensions().add("sampleExtension", new SamplePluginExtension());
		project.getTasks().create("sampleTask", SampleTask.class);

	}

}
