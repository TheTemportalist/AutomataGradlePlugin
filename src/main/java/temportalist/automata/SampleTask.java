package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
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
			System.out.println(extension.sampleEXT);
			if (extension.sampleEXT instanceof ForgeExtension) {
				ForgeExtension minecraft = (ForgeExtension)extension.sampleEXT;
				System.out.println(minecraft.getForgeVersion());
				System.out.println(minecraft.getRunDir());
			}
		}
		catch(Exception e) {
			throw new TaskExecutionException(this,
					new Exception("Exception occurred while processing sampleTask", e)
			);
		}
	}

}
