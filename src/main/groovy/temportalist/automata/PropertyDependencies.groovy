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
	Map<String, Object>[] dependencies;

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
		if (this.dependencies != null && !this.dependencies.isEmpty()) {
			for (Map<String, Object> dependency : this.dependencies) {
				project.dependencies.add(
						dependency.get("compileWith"),
						String.format("%s:%s:%s",
								dependency.get("group"), dependency.get("name"), dependency.get("version")
						)
				)
				if (dependency.hasProperty("replace")) {
					def replaceClosure = dependency.get("replace") as Closure<?>
					def original = String.format("%s:%s",
							replaceClosure.getProperty("instruction"),
							replaceClosure.getProperty("modid")
					)
					minecraft.replace(original,
							String.format("%s@%s", original,
									replaceClosure.getProperty("versionRange")
							)
					)
				}
			}
		}
	}

}
