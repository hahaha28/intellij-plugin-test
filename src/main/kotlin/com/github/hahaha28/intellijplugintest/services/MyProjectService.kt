package com.github.hahaha28.intellijplugintest.services

import com.intellij.openapi.project.Project
import com.github.hahaha28.intellijplugintest.MyBundle
import com.github.hahaha28.intellijplugintest.file.FileUtil
import com.github.hahaha28.intellijplugintest.file.ModifiedFiles

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
        println(project.basePath)
        FileUtil.init(project)

        ModifiedFiles.startListen(project);
    }
}
