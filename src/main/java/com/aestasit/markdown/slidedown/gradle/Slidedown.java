package com.aestasit.markdown.slidedown.gradle;

import java.io.File;
import java.io.IOException;

import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.SourceTask;
import org.gradle.api.tasks.TaskAction;

import com.aestasit.markdown.slidedown.converters.Configuration;
import com.aestasit.markdown.slidedown.converters.ConverterFactory;
import com.aestasit.markdown.slidedown.converters.SimpleConfiguration;

/**
 * @author Andrey Adamovich
 *
 */
public class Slidedown extends SourceTask {

  @Input
  String         format;

  private String outputFile;

  void setOutputFile(String outputFile) {
    this.outputFile = outputFile;
  }

  public void format(String formatId) {
    this.format = formatId;
  }
  
  @OutputFile
  public File getOutputFile() {
    return getProject().file(outputFile);
  }

  @TaskAction
  void doTransform() throws IOException {
    ConverterFactory.createConverter(format).render(buildConfiguration());
  }

  private Configuration buildConfiguration() {
    // TODO: build config
    return new SimpleConfiguration();
  }

}
