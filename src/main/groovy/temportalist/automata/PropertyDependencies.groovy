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

	def dependencies(Closure<?>[] closures) {
		this.dependencies = new PropertyDependency[closures.length]
		for (int i = 0; i < closures.length; i++)
			this.dependencies.with closures[i]
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

				project.dependencies.add(
						dependency.compileWith,
						String.format("%s:%s:%s",
								dependency.group, dependency.name, dependency.version
						)
				)
				if (dependency.replace != null) {
					System.out.println(dependency.replace.properties)
					/*
					def replaceClosure = dependency.get("replace") as Closure<?>
					System.out.println(replaceClosure.properties.entrySet())
					def original = String.format("%s:%s",
							replaceClosure.getProperty("instruction"),
							replaceClosure.getProperty("modid")
					)
					minecraft.replace(original,
							String.format("%s@%s", original,
									replaceClosure.getProperty("versionRange")
							)
					)
					*/
				}
			}
		}
	}

}
