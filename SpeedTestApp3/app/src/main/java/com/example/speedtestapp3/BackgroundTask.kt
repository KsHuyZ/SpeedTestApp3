package com.example.speedtestapp3

import android.app.Activity
import androidx.fragment.app.Fragment


abstract class BackgroundTask(private val activity: Activity) {
    private fun startBackground() {
        Thread {
            doInBackground()
//            activity.runOnUiThread { onPostExecute() }
        }.start()
    }

    fun execute() {
        startBackground()
    }

    abstract fun doInBackground()
    abstract fun onPostExecute()
}