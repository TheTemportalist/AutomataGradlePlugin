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

		project.getExtensions().add("sampleExtension", new SamplePluginExtension());
		project.getTasks().create("sampleTask", SampleTask.class);

		// END

		/* Try to replace the following:
			buildscript {
				repositories {
					jcenter()
					maven {
						name = "forge"
						url = "http://files.minecraftforge.net/maven"
					}
				}
				dependencies {
					classpath 'net.minecraftforge.gradle:ForgeGradle:2.2-SNAPSHOT'
				}
			}

			apply plugin: 'net.minecraftforge.gradle.forge'
		 */
		project.getBuildscript().getRepositories(); // add
		project.getBuildscript().getDependencies().add("classpath", "net.minecraftforge.gradle:ForgeGradle:2.2-SNAPSHOT");
		project.getPlugins().apply("net.minecraftforge.gradle.forge");

	}

}
