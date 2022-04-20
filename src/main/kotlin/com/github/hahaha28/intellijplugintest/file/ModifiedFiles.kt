package com.github.hahaha28.intellijplugintest.file

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiTreeChangeAdapter
import com.intellij.psi.PsiTreeChangeEvent

object ModifiedFiles {

    val modifiedFiles : MutableMap<PModule,MutableSet<PFile>> = mutableMapOf()

    fun startListen(project: Project) {
        println("ModifiedFiles start listen")
        PsiManager.getInstance(project)
            .addPsiTreeChangeListener(object : PsiTreeChangeAdapter() {

                override fun childrenChanged(event: PsiTreeChangeEvent) {
                    super.childrenChanged(event)
                    event.file?.virtualFile?.let {
                        val file = PFile(it.path)
                        if(modifiedFiles.containsKey(file.module)){
                            modifiedFiles.get(file.module)?.add(file)
                        }else{
                            file.module?.let { module ->
                                modifiedFiles.put(module, mutableSetOf(file))
                            }
                        }
                        println("file changed:${file.filePath},${file.module}")
                    }
                }
            })
    }
}