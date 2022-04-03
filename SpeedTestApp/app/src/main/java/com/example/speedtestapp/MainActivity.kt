package com.example.speedtestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = Adapter()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

        val builder = InternetSpeedBuilder(this)
        builder.setOnEventInternetSpeedListener(object :
            InternetSpeedBuilder.OnEventInternetSpeedListener {
            override fun onDownloadProgress(count: Int, progressModel: ProgressionModel) {}
            override fun onUploadProgress(count: Int, progressModel: ProgressionModel) {}
            override fun onTotalProgress(count: Int, progressModel: ProgressionModel) {
                adapter.setDataList(count, progressModel)
            }
        })
        builder.start("http://www.speedtest.net/mini.php", 20)
    }
}