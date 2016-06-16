package temportalist.automata

/**
 *
 * Created by TheTemportalist on 6/16/2016.
 * @author TheTemportalist
 */
class PropertyDependency {

	String group, name, version, compileWith;
	PropertyReplaceDep replace;

	def replace(Closure<?> closure) {
		this.replace = new PropertyReplaceDep();
		this.replace.with(closure)
	}

	static class PropertyReplaceDep {
		String instruction, modid, versionRange;
	}

}
