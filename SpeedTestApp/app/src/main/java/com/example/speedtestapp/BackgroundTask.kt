package com.example.speedtestapp

import android.app.Activity


abstract class BackgroundTask(private val activity: Activity) {
    private fun startBackground() {
        Thread {
            doInBackground()
            activity.runOnUiThread { onPostExecute() }
        }.start()
    }

    fun execute() {
        startBackground()
    }

    abstract fun doInBackground()
    abstract fun onPostExecute()
}