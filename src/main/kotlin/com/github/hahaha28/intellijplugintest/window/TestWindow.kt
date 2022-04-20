package com.github.hahaha28.intellijplugintest.window

import com.github.hahaha28.intellijplugintest.file.ModifiedFiles
import com.github.hahaha28.intellijplugintest.script.CmdTool
import com.github.hahaha28.intellijplugintest.script.DxTool
import com.github.hahaha28.intellijplugintest.script.GradleCmd
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.psi.PsiManager
import com.intellij.psi.PsiTreeChangeAdapter
import com.intellij.psi.PsiTreeChangeEvent
import com.intellij.psi.PsiTreeChangeListener
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.search.SearchScope
import java.util.*
import java.util.concurrent.CountDownLatch
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.JTextPane

class TestWindow(private val project: Project) {
    private lateinit var button1: JButton
    lateinit var panel1: JPanel
    private lateinit var textPane1: JTextPane

    init {

        button1.addActionListener {
            addLog("click button")
            addLog("modifiedModule size = ${ModifiedFiles.modifiedFiles.size}")
            val gradleCmd = GradleCmd("${project.basePath}/gradlew.bat")
            val dxTool = DxTool("D:\\Users\\junjie.lin\\AppData\\Local\\Android\\Sdk\\build-tools\\30.0.3\\dx.bat")

            val countDown = CountDownLatch(ModifiedFiles.modifiedFiles.size)
            ModifiedFiles.modifiedFiles.keys.forEach {
                addLog("start compile java ${it.name}")
                addLog(gradleCmd.compileDebugJavaCmd(it))
                CmdTool(gradleCmd.compileDebugJavaCmd(it))
                    .onRunning {
                        addLog(it)
                    }
                    .onError {
                        addLog("error!")
                        addLog(it)
                    }
                    .onFinish {
                        addLog("compile java ${it.name} finish")
                        countDown.countDown()
                    }
                    .execute();
            }
            countDown.await()
            val scope:Search`
            // dex
            addLog("start dex")
            val dexCmd = dxTool.dexCmd(ModifiedFiles.modifiedFiles.keys.toList(),
                "D:/repo/${System.currentTimeMillis()}.dex")
            addLog("dex cmd:$dexCmd");
            CmdTool(dexCmd)
                .onRunning {
                    addLog(it)
                }
                .onError {
                    addLog("error!")
                    addLog(it)
                }
                .onFinish {
                    addLog("dx finish")
                }
                .execute()

        }
    }

    private fun addLog(text: String) {
        textPane1.text = textPane1.text + '\n' + text;
    }
}