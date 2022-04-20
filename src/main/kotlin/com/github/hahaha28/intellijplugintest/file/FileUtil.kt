package com.github.hahaha28.intellijplugintest.file

import com.intellij.openapi.project.Project
import com.intellij.psi.search.FilenameIndex
import java.io.File

object FileUtil {

    lateinit var project:Project
    lateinit var pModules: List<PModule>
    lateinit var projectBasePath:String

    fun init(project: Project){
        this.project = project;
        pModules = getProjectModules(project);
        projectBasePath = project.basePath!!;
        println("modules:")
        pModules.forEach {
            println("${it.name},${it.path}")
        }
    }

    private fun getProjectModules(project:Project):List<PModule>{
        val modules = mutableListOf<PModule>()
        val settingsGradleFile = File("${project.basePath}/settings.gradle")
        val lines = settingsGradleFile.readLines()
        lines.forEach { line ->
            if(line.startsWith("include ':")){
                // TODO 异常判断
                val moduleName = line.split('\'')[1].removePrefix(":");
                modules.add(PModule(moduleName,"${project.basePath}/${moduleName}"))
            }
        }
        return modules;
    }

    fun getFileModule(filePath:String):PModule?{
        pModules.forEach {
            if(filePath.startsWith(it.path)){
                return it
            }
        }
        return null
    }

}