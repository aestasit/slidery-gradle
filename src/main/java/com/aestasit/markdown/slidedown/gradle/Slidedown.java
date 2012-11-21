package com.aestasit.markdown.slidedown.gradle;

import java.io.File;
import java.io.IOException;

import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.SourceTask;
import org.gradle.api.tasks.TaskAction;

import com.aestasit.markdown.slidedown.converters.Configuration;
import com.aestasit.markdown.slidedown.converters.ConverterFactory;
import com.aestasit.markdown.slidedown.converters.SimpleConfiguration;

class Slidedown extends SourceTask {

  private String destination;
  private String format;

  void setDestination(String destination) {
    this.destination = destination;
  }

  @OutputFile
  public File getDestination() {
    return getProject().file(destination);
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
