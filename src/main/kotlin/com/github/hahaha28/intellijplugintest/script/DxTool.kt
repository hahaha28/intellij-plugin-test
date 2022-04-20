package com.github.hahaha28.intellijplugintest.script

import com.github.hahaha28.intellijplugintest.file.PModule


class DxTool(var dxToolPath:String) {

    fun dexCmd(srcPath:String,outputPath:String):String{
        return "${dxToolPath} --dex --min-sdk-version=26 --output=${outputPath} ${srcPath}"
    }

    fun dexCmd(modules:List<PModule>,outputPath:String):String{
        val modulesJavac = StringBuilder()
        modules.forEach {
            modulesJavac.append(it.getDebugClassesDirPath())
            modulesJavac.append(" ")
        }
        return dexCmd(modulesJavac.toString(),outputPath)
    }
}