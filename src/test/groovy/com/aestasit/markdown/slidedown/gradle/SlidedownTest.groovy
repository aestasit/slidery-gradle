package com.aestasit.markdown.slidedown.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.BeforeClass
import org.junit.Test

/**
 * @author Andrey Adamovich
 *
 */
class SlidedownTest {

  static Project project

  @BeforeClass
  def static void buildProject() {
    project = ProjectBuilder.builder().build()
    project.with {

      apply plugin: 'slidedown'

      task('presentation', type: Slidedown) {
        include "src/test/resources/*.md"
        outputFile "$buildDir/presentation/slides.html"        
        format 'reveal-js-base'
      }

    }
  }

  @Test
  public void testSetup() {
    project.tasks.'presentation'.execute()
    assert project != null
  }

  def static File getCurrentDir() {
    return new File(".").absoluteFile
  }
  
}
