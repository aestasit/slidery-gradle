/*
 * Copyright (C) 2011-2013 Aestas/IT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aestasit.markdown.slidery.gradle

import org.gradle.api.file.FileCollection
import org.gradle.api.file.FileTree
import org.gradle.api.internal.ConventionTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

import com.aestasit.markdown.slidery.configuration.Configuration
import com.aestasit.markdown.slidery.configuration.ConfigurationBuilder
import com.aestasit.markdown.slidery.configuration.SimpleConfiguration
import com.aestasit.markdown.slidery.converters.ConverterFactory

/**
 * Gradle task that calls Slidery converter based on the configuration.
 * 
 * @author Andrey Adamovich
 *
 */
public class Slidery extends ConventionTask {

  private Configuration config = new SimpleConfiguration()
  
  @TaskAction
  void doTransform() throws IOException {
    ConverterFactory.createConverter(format).render(buildConfiguration())
  }

  private ConfigurationBuilder buildConfiguration() {
    config.author(author)
          .company(company)
          .title(title)
          .description(description)
          .currentDate()
          .splitOutput(splitOutput)
          .includeNotes(includeNotes)
          .stripHtml(stripHtml)
          .incrementalLists(incrementLists)
          .theme(theme)
          .encoding(inputEncoding)
    return config
  }
  
  @Input String format = 'deck-js'
  @Input @Optional String theme 
  @Input @Optional String inputEncoding = 'UTF-8'
  
  @Input boolean splitOutput = false
  @Input boolean incrementLists = true
  @Input boolean includeNotes = true
  @Input boolean stripHtml = true
  
  @Input @Optional String author
  @Input @Optional String company
  @Input @Optional String title
  @Input @Optional String description

  @InputFiles
  def FileCollection getInputFiles() {
    FileCollection inputFiles = project.files(config.getInputFiles()) + project.files(config.getStaticFiles().values())
    if (config.logo) { 
      inputFiles += project.files(config.logo)      
    }
    if (config.templateFile) {
      inputFiles += project.files(config.templateFile)
    }
    return inputFiles
  }

  @OutputFile
  def getOutputFile() {
    return config.getOutputFile()
  }

  def inputFile(String inputFile) {
    return config.inputFile(project.file(inputFile))
  }

  def inputFile(File inputFile) {
    return config.inputFile(inputFile)
  }

  def inputFiles(Collection<File> inputFiles) {
    return config.inputFiles(inputFiles)
  }

  def inputFiles(FileCollection inputFiles) {
    return config.inputFiles(inputFiles.files)
  }

  def inputFiles(FileTree inputFiles) {
    return config.inputFiles(inputFiles.files)
  }

  def staticFile(String staticFile) {
    return config.staticFile(project.file(staticFile))
  }

  def staticFile(File staticFile) {
    return config.staticFile(staticFile)
  }

  def staticFiles(Collection<File> staticFiles) {
    return config.staticFiles(staticFiles)
  }

  def staticFiles(FileCollection staticFiles) {
    return config.staticFiles(staticFiles.files)
  }

  def staticFiles(FileTree staticFiles) {
    return config.staticFiles(staticFiles.files)
  }

  def staticFile(String relativePath, String staticFile) {
    return config.staticFile(relativePath, project.file(staticFile))
  }

  def staticFile(String relativePath, File staticFile) {
    return config.staticFile(relativePath, staticFile)
  }

  def staticFiles(String relativePath, Collection<File> staticFiles) {
    return config.staticFiles(relativePath, staticFiles)
  }

  def staticFiles(String relativePath, FileCollection staticFiles) {
    return config.staticFiles(relativePath, staticFiles.files)
  }

  def staticFiles(String relativePath, FileTree staticFiles) {
    return config.staticFiles(relativePath, staticFiles.files)
  }
  
  def templateFile(String templateFile) {
    return config.templateFile(project.file(templateFile))
  }

  def templateFile(File templateFile) {
    return config.templateFile(templateFile)
  }

  def outputFile(String outputFile) {
    return config.outputFile(project.file(outputFile))
  }
  
  def outputFile(File outputFile) {
    return config.outputFile(outputFile)
  }

  def logo(String logoFile) {
    return config.logo(project.file(logoFile))
  }

  def logo(File logoFile) {
    return config.logo(logoFile)
  }

  def option(String name, Object value) {    
    return config.option(name, value?.toString())
  }

  def option(String name, boolean value) {
    return config.option(name, value.toString())
  }

  def option(String name, String value) {
    return config.option(name, value)
  }
  
}
