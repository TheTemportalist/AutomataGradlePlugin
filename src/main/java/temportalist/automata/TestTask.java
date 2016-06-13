package temportalist.automata;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
class TestTask extends DefaultTask {

	@TaskAction
	public void testAutomata() {
		System.out.println("Hello from Test Automata");
	}

}
