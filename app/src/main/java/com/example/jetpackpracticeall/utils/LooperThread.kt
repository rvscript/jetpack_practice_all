package com.example.jetpackpracticeall.utils

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import java.lang.ref.WeakReference

class LooperThread: Thread() {
    companion object {
        const val TAG = "LooperThread"
    }

    private val outerClass = WeakReference<LooperThread>(this)
    val myHandler = MyHandler(outerClass)

    override fun run() {
        super.run()
        Looper.prepare()
        // do something here
        myHandler
        // do something ends here
        Looper.loop()
    }

    class MyHandler(private val outerClass: WeakReference<LooperThread>): Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.i(TAG, "handleMessage: ${Thread.currentThread().id}, count = ${msg.obj}")
        }
    }
}