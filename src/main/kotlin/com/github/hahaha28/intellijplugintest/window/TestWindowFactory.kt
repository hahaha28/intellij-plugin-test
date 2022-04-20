package com.github.hahaha28.intellijplugintest.window

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class TestWindowFactory:ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val window = TestWindow(project)
        val contentFactory = ContentFactory.SERVICE.getInstance();
        val content = contentFactory.createContent(window.panel1,"displayName",false);
        toolWindow.contentManager.addContent(content);
    }
}