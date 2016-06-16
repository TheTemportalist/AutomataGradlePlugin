package temportalist.automata

import net.minecraftforge.gradle.user.patcherUser.forge.ForgeExtension
import org.gradle.api.Project

/**
 *
 * Created by TheTemportalist on 6/15/2016.
 * @author TheTemportalist
 */
class Archives {

	static def setupArchives(final Project projectObj,
							 final String archiveName,
							 final String descriptionObj,
							 final String urlRepo, final String username, final String password,
							 final String urlSource, final String urlSCM,
							 final String issueTracker, final String urlIssues,
							 final String licenseType, final String urlLicense,
							 final Map<EnumDeveloperStat, Object>[] developerMaps
	) {
		def minecraft = projectObj.getExtensions().findByType(ForgeExtension)
		projectObj.uploadArchives.repositories.mavenDeployer {
			repository(url: urlRepo) {
				authentication(userName: username, password: password)
			}
			pom {
				groupId = projectObj.group
				version = projectObj.version
				artifactId = archiveName
				project {
					name = archiveName
					packaging = "jar"
					description = descriptionObj
					url = urlSource

					scm {
						url = urlSource
						connection = urlSCM
						developerConnection = urlSCM
					}
					issueManagement {
						system = issueTracker
						url = urlIssues
					}

					licenses {
						license {
							name = licenseType
							url = urlLicense
							distribution = 'repo'
						}
					}

					developers {
						for (developerMap in developerMaps) {
							developer {
								id = developerMap.get(EnumDeveloperStat.ID)
								name = developerMap.get(EnumDeveloperStat.NAME)
								roles {
									role developerMap.get(EnumDeveloperStat.ROLE).toString()
								}
							}
						}
					}
				}
			}
		}
	}

}
