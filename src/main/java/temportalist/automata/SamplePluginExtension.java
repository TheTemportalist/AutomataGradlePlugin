package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
import org.gradle.api.Project;
import org.gradle.language.jvm.tasks.ProcessResources;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
class SamplePluginExtension {

	private final Project project;

	SamplePluginExtension(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return this.project;
	}

	String message = "This is a SamplePluginExtension message";
	Object sampleEXT = null;
	ForgeExtension minecraft = null;
	ProcessResources processResources = null;

}
