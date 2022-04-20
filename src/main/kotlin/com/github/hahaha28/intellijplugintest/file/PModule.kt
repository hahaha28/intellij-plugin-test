package com.github.hahaha28.intellijplugintest.file

data class PModule(val name:String,val path:String) {

    fun getDebugClassesDirPath():String{
        return "${path}/build/intermediates/javac/debug/classes"
    }
}