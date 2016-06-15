package temportalist.automata;

/**
 * Created by TheTemportalist on 6/15/2016.
 *
 * @author TheTemportalist
 */
public enum EnumDeveloperStat {

	ID("id"),
	NAME("name"),
	ROLE("role");

	final String name;

	EnumDeveloperStat(String str) {
		this.name = str;
	}

	public String getName() {
		return name;
	}

	public static EnumDeveloperStat getByName(String name) {
		for (EnumDeveloperStat stat : EnumDeveloperStat.values()) {
			if (stat.getName().equals(name)) return stat;
		}
		return null;
	}

}
