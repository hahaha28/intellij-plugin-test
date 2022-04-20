package com.github.hahaha28.intellijplugintest.script

import com.github.hahaha28.intellijplugintest.file.PModule

class GradleCmd(var gradlewPath:String) {

    fun compileDebugJavaCmd(module:PModule):String{
        return "${gradlewPath} ${module.name}:compileDebugJavaWithJavac"
    }
}