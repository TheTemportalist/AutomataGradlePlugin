package temportalist.automata

/**
 *
 * Created by TheTemportalist on 6/16/2016.
 * @author TheTemportalist
 */
class PropertyDependency {

	String group, name, version, compileWith;
	PropertyReplaceDep replace;

	static class PropertyReplaceDep {
		String instruction, modid, versionRange;
	}

}
