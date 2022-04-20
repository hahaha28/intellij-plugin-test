package com.github.hahaha28.intellijplugintest.file

class PFile(val filePath:String) {

    val type:FileType
    val module:PModule?

    init {
        type = getFileType()
        module = FileUtil.getFileModule(filePath)
    }

    private fun getFileType():FileType{
        if(filePath.endsWith(".java")){
            return FileType.Java
        }else if(filePath.endsWith(".kt")){
            return FileType.Kotlin
        }

        return FileType.Unknown
    }

}