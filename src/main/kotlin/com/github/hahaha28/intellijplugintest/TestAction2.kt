package com.github.hahaha28.intellijplugintest

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class TestAction2 :AnAction(){
    override fun actionPerformed(e: AnActionEvent) {
        Messages.showMessageDialog("message", "my title", null)
    }
}