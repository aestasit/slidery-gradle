package com.aestasit.markdown.slidedown.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.BeforeClass
import org.junit.Test

public class SlidedownTest {

  static Project project

  @BeforeClass
  def static void buildProject() {
    project = ProjectBuilder.builder().build()
    project.with {

      apply plugin: 'slidedown'

      task('presentation', type: Slidedown) {
        outputFile "$buildDir/"        
        format 'reveal.js'
      }

    }
  }

  @Test
  public void testSetup() {
    project.tasks.'convert'.execute()
    assert project != null
  }

  def static File getCurrentDir() {
    return new File(".").absoluteFile
  }
  
}
