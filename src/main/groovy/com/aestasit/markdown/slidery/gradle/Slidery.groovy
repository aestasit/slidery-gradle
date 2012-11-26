package com.aestasit.markdown.slidery.gradle

import org.gradle.api.file.FileTree
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.SourceTask
import org.gradle.api.tasks.TaskAction

import com.aestasit.markdown.slidery.converters.Configuration
import com.aestasit.markdown.slidery.converters.ConverterFactory
import com.aestasit.markdown.slidery.converters.SimpleConfiguration

/**
 * @author Andrey Adamovich
 *
 */
public class Slidery extends SourceTask {

  @Input
  String format

  private destination

  void setDestination(destination) {
    this.destination = destination
  }

  @OutputFile
  def getDestination() {
    return project.file(destination)
  }

  @TaskAction
  void doTransform() throws IOException {
    ConverterFactory.createConverter(format).render(buildConfiguration())
  }

  private Configuration buildConfiguration() {
    SimpleConfiguration config = new SimpleConfiguration()
    def files = source.files.sort { it.name }
    for (File file : files) {
      config.inputFile(file)
    }
    config.outputFile(getDestination())
    return config
  }
}
