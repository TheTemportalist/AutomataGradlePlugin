package temportalist.automata;

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension;
import org.gradle.language.jvm.tasks.ProcessResources;

/**
 * Created by TheTemportalist on 6/12/2016.
 *
 * @author TheTemportalist
 */
class ExtensionAutomata {

	String message = "This is a ExtensionAutomata message";
	Object sampleEXT = null;
	ForgeExtension minecraft = null;
	ProcessResources processResources = null;

	String organization, groupName, archiveName;
	int versionMajor, versionMinor, versionPatch;

	String version_minecraft, version_forge, version_forge_b = null, version_mcp;

	String curseID = null;
	String curseBuildType = null;

}
