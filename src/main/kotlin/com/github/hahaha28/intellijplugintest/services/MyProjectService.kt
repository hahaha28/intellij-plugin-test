package com.github.hahaha28.intellijplugintest.services

import com.intellij.openapi.project.Project
import com.github.hahaha28.intellijplugintest.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
