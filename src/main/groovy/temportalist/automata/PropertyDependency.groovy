package temportalist.automata

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension
import org.gradle.api.Project

/**
 *
 * Created by TheTemportalist on 6/16/2016.
 * @author TheTemportalist
 */
class PropertyDependency {

	String group, name, version, compileWith;
	PropertyReplaceDep replace;

	def setReplace(Closure<?> closure) {
		this.replace = new PropertyReplaceDep();
		this.replace.with(closure)
	}

	def load(Project project, ForgeExtension minecraft) {
		project.dependencies.add(
				this.compileWith, String.format("%s:%s:%s", this.group, this.name, this.version)
		)
		if (this.replace != null) this.replace.load(minecraft)
	}

	static class PropertyReplaceDep {
		String instruction, modid, versionRange;

		def load(ForgeExtension minecraft) {
			def original = String.format("%s:%s", this.instruction, this.modid)
			minecraft.replace(original, String.format("%s@%s", original, this.versionRange))
		}

	}

}
