package temportalist.automata

import org.gradle.api.Project

/**
 *
 * Created by TheTemportalist on 6/15/2016.
 * @author TheTemportalist
 */
class PropertyArchives {

	String name, description;
	String repoUrl, repoUsername, repoPassword;
	String sourceUrl;
	String scmUrl;
	String issueTrackerType, issueTrackerUrl;
	String licenseType, licenseUrl;
	Map<EnumDeveloperStat, Object>[] developers;

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setRepoUrl(String repoUrl) {
		this.repoUrl = repoUrl;
	}

	public void setRepoUsername(String repoUsername) {
		this.repoUsername = repoUsername;
	}

	public void setRepoPassword(String repoPassword) {
		this.repoPassword = repoPassword;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public void setScmUrl(String scmUrl) {
		this.scmUrl = scmUrl;
	}

	public void setIssueTrackerType(String issueTrackerType) {
		this.issueTrackerType = issueTrackerType;
	}

	public void setIssueTrackerUrl(String issueTrackerUrl) {
		this.issueTrackerUrl = issueTrackerUrl;
	}

	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	public void setDevelopers(Map<String, Object>[] developers) {
		List<Map<EnumDeveloperStat, Object>> devs = new ArrayList<>();
		for (Map<String, Object> developerMap : developers) {
			Map<EnumDeveloperStat, Object> devMap = new HashMap<>();
			for (String key : developerMap.keySet()) {
				devMap.put(EnumDeveloperStat.getByName(key), developerMap.get(key));
			}
			devs.add(devMap);
		}
		this.developers = (Map<EnumDeveloperStat, Object>[]) devs.toArray();
	}

	public void setupArchives(final Project project) {
		Archives.setupArchives(project,
				this.name, this.description,
				this.repoUrl, this.repoUsername, this.repoPassword, this.sourceUrl, this.scmUrl,
				this.issueTrackerType, this.issueTrackerUrl,
				this.licenseType, this.licenseUrl,
				this.developers
		);
	}

}
