package temportalist.automata

/**
 *
 * Created by TheTemportalist on 6/15/2016.
 * @author TheTemportalist
 */
class PropertyReplace {

	String version, forge;
	Map<String, Object> replaceMap = new HashMap<>();

	public void setVersion(String version) {
		this.version = version;
	}

	public void setForge(String forge) {
		this.forge = forge;
	}

	public void setReplaceMap(Map<String, Object> replaceMap) {
		this.replaceMap = replaceMap;
	}

}
