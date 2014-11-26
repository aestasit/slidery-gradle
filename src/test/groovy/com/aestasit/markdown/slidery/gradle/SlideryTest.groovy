/*
 * Copyright (C) 2011-2014 Aestas/IT
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
        stripHtml false
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
