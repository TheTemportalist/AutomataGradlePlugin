package temportalist.automata

import org.gradle.api.Project
import org.gradle.api.Task
import se.bjurr.gitchangelog.plugin.gradle.GitChangelogTask

/**
 *
 * Created by TheTemportalist on 6/16/2016.
 * @author TheTemportalist
 */
class PropertyChangelog {

	String id

	String fileExtension

	String template

	String fromRef, toRef

	private GitChangelogTask task;

	def createTask(Project project, ExtensionAutomata automata) {
		this.task = project.getTasks().create("makeChangelog" + this.id, GitChangelogTask)
		this.task.filePath = this.getFileName()
		this.task.untaggedName = automata.getVersionPure()
		this.task.fromCommit = this.fromRef
		this.task.toRef = this.toRef
		this.task.templateContent = this.template
	}

	def linkToCurseforge(Task curseforge) {
		curseforge.dependsOn(this.task)
	}

	def getFileName() {
		return "changelog" + this.id + "." + this.fileExtension
	}

}
