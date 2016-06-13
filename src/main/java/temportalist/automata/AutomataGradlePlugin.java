package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
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
		ForgeExtension minecraft = project.getExtensions().findByType(ForgeExtension.class);
		sampleEXT.minecraftEXT = minecraft;
		minecraft.setForgeVersion("1.9.4-12.17.0.1932-1.9.4");
		minecraft.setRunDir("runInThisDir");
		minecraft.setMappings("snapshot_20160518");

	}

}
