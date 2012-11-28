package com.aestasit.markdown.slidery.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.BeforeClass
import org.junit.Test

import com.aestasit.markdown.slidery.gradle.Slidery;

/**
 * @author Andrey Adamovich
 *
 */
class SlideryTest {

  static Project project

  @BeforeClass
  def static void buildProject() {
    project = ProjectBuilder.builder().build()
    project.with {

      apply plugin: 'slidery'

      task('presentation', type: Slidery) {
        inputFiles fileTree("$currentDir/src/test/resources") { 
          include "*.md" 
        }  
        outputFile "$currentDir/tmp/presentation/slides.html"
        option "renderSyntaxHighlighting", true
        format 'reveal-js'
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
