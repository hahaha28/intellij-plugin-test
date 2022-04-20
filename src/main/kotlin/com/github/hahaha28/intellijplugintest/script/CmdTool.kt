package com.github.hahaha28.intellijplugintest.script

import com.github.hahaha28.intellijplugintest.file.FileUtil
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

class CmdTool(val cmd:String) {

    private lateinit var process:Process;

    private var onFinishCallback:()->Unit = {}
    private var onRunningCallback:(String)->Unit = {}
    private var onErrorCallback:(String)->Unit = {}

    fun execute():CmdTool{
        process = Runtime.getRuntime().exec(cmd,null, File(FileUtil.projectBasePath));
        thread {
            val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))
            var line:String?
            do {
                line = bufferedReader.readLine();
                line?.let{
                    onRunningCallback(it)
                }
            }while(line != null)
        }
        thread {
            val bufferedReader = BufferedReader(InputStreamReader(process.errorStream))
            var line:String?
            do {
                line = bufferedReader.readLine();
                line?.let{
                    onRunningCallback(it)
                }
            }while(line != null)
        }
        process.waitFor();
        onFinishCallback();
        return this
    }

    fun onRunning(output:(String)->Unit):CmdTool{
        this.onRunningCallback = output
        return this;
    }

    fun onError(output:(String)->Unit):CmdTool{
        this.onErrorCallback = output
        return this
    }


    fun onFinish(onFinish:()->Unit):CmdTool{
        this.onFinishCallback = onFinish
        return this
    }

}