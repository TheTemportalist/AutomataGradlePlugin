package temportalist.automata

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension
import org.gradle.api.Project

/**
 *
 * Created by TheTemportalist on 6/16/2016.
 * @author TheTemportalist
 */
class PropertyDependencies {

	Map<String, String> repositories;
	PropertyDependency[] dependencies;

	def setDependencies(Closure<?>[] closures) {
		this.dependencies = new PropertyDependency[closures.length]
		for (int i = 0; i < closures.length; i++) {
			this.dependencies[i] = new PropertyDependency()
			this.dependencies[i].with(closures[i])
		}
	}

	def load(Project project, ForgeExtension minecraft) {
		project.getRepositories().mavenCentral()
		project.getRepositories().jcenter()
		if (this.repositories != null && !this.repositories.isEmpty()) {
			for (Map.Entry<String, String> entry : this.repositories) {
				project.repositories.maven {
					name = entry.getKey()
					url = entry.getValue()
				}
			}
		}

		if (this.dependencies != null && this.dependencies.length > 0) {
			for (PropertyDependency dependency : this.dependencies) {
				dependency.load(project, minecraft)
			}
		}
	}

}
