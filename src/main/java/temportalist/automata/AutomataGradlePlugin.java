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

		// START: testing extensions and tasks

		SamplePluginExtension sampleEXT = new SamplePluginExtension();
		project.getExtensions().add("sampleExtension", sampleEXT);
		project.getTasks().create("sampleTask", SampleTask.class);

		// END

		sampleEXT.minecraftEXT = project.getExtensions().findByName("minecraft");

	}

}
