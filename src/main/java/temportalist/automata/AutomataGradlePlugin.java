package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
import net.minecraftforge.gradle.user.patcherUser.forge.ForgePlugin;
import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
public class AutomataGradlePlugin implements Plugin<Project> {

	@Override
	public void apply(final Project project) {

		Map<String, String> mapPluginForge = new HashMap<>();
		mapPluginForge.put("plugin", "net.minecraftforge.gradle.forge");
		project.apply(mapPluginForge);

		// START: testing extensions and tasks

		ExtensionAutomata automataEXT = new ExtensionAutomata();
		project.getExtensions().add("automata", automataEXT);
		TaskDetailsLoad taskLoad =
				project.getTasks().create("loadAutomata", TaskDetailsLoad.class);
		TaskDetailsDisplay taskDisplay =
				project.getTasks().create("displayDetails", TaskDetailsDisplay.class);

		ForgePlugin plugin = project.getPlugins().findPlugin(ForgePlugin.class);

		project.beforeEvaluate(new Action<Project>() {
			@Override
			public void execute(Project project) {
				ForgeExtension minecraft = project.getExtensions().findByType(ForgeExtension.class);
				System.out.println("\n\n\n\n");
				System.out.println("Pre:  " + minecraft.getForgeVersion());
				minecraft.setForgeVersion("1.9.4-12.17.0.1932-1.9.4");
				System.out.println("Post: " + minecraft.getForgeVersion());
				System.out.println("\n\n\n\n");
			}
		});

	}

}
