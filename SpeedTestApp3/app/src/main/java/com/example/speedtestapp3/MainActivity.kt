package com.example.speedtestapp3

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.speedtestapp3.Adapter.HistoryAdapter
import com.example.speedtestapp3.Models.Models
import com.example.speedtestapp3.database.DatabaseHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.layout_speed.*
import java.math.BigDecimal
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log
import android.util.Log.v as v1

class MainActivity : AppCompatActivity() {

    lateinit var barImage: ImageView
    lateinit var downloadSpeed: TextView
    lateinit var uploadSpeed: TextView
    lateinit var totalSpeed: TextView

    var position = 0
    var lastPosition = 0

    internal var selectedFragment: Fragment ?=null
//    lateinit var recyclehistory: RecyclerView
    lateinit var historyAdapter : HistoryAdapter
    lateinit var dbHandler : DatabaseHelper
    var historyList : List<Models> = ArrayList<Models>()
    var linearLayoutManager : LinearLayoutManager ?= null










    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_speed)

        barImage = findViewById(R.id.barImageView)
        downloadSpeed = findViewById(R.id.download)
        uploadSpeed = findViewById(R.id.upload)
        totalSpeed = findViewById(R.id.total_speed)

        fun fetchList(){
            historyList= dbHandler!!.getAllHistory()
            Log.e("Hi","${historyList.size}")
            historyAdapter = HistoryAdapter(historyList,applicationContext)
            linearLayoutManager = LinearLayoutManager(applicationContext)
//            recyclehistory.layoutManager = linearLayoutManager
//            recyclehistory.adapter = historyAdapter
            historyAdapter?.notifyDataSetChanged()
        }


        val builder = InternetSpeedBuilder(this)
        builder.setOnEventInternetSpeedListener(object:
            InternetSpeedBuilder.OnEventInternetSpeedListener{
            @SuppressLint("SetTextI18n")
            override fun onDownloadProgress(count: Int, progressModel: ProgressionModel) {
                val bigDecimal = BigDecimal("" + progressModel.downloadSpeed)
                val finalDownload = (bigDecimal.toLong() / 1000000).toFloat()

                val bd: BigDecimal = progressModel.downloadSpeed
                val d = bd.toDouble()

                position = getPositionByRate(finalDownload)

                runOnUiThread{
                    val rotateAnimation = RotateAnimation(
                        lastPosition.toFloat(),
                        position.toFloat(),
                        Animation.RELATIVE_TO_SELF,
                        0.5F,
                        Animation.RELATIVE_TO_SELF,
                        0.5F
                    )
                    rotateAnimation.interpolator = LinearInterpolator()
                    rotateAnimation.duration = 2000
                    barImage.startAnimation(rotateAnimation)
                    downloadSpeed.text = "" + formatFileSize(d)
                }
                lastPosition = position
            }

            @SuppressLint("SetTextI18n")
            override fun onUploadProgress(count: Int, progressModel: ProgressionModel) {
                val bigDecimal = BigDecimal("" + progressModel.uploadSpeed)
                val finalDownload = (bigDecimal.toLong() / 1000000).toFloat()

                val bd: BigDecimal = progressModel.uploadSpeed
                val d = bd.toDouble()

                position = getPositionByRate(finalDownload)

                runOnUiThread {
                    val rotateAnimation = RotateAnimation(
                        lastPosition.toFloat(),
                        position.toFloat(),
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f
                    )
                    rotateAnimation.interpolator = LinearInterpolator()
                    rotateAnimation.duration = 2000
                    barImage.startAnimation(rotateAnimation)
                    uploadSpeed.text = "" + formatFileSize(d)
                }
                lastPosition = position
            }

            @SuppressLint("SetTextI18n")
            override fun onTotalProgress(count: Int, progressModel: ProgressionModel) {
                val downloadDecimal: BigDecimal = progressModel.downloadSpeed
                val downloadFinal = downloadDecimal.toDouble()

                val uploadDecimal: BigDecimal = progressModel.uploadSpeed
                val uploadFinal = uploadDecimal.toDouble()
                val totalSpeedCount = (downloadFinal + uploadFinal) / 2

                val finalDownload = (downloadDecimal.toLong() / 1000000).toFloat()
                val finalUpload = (uploadDecimal.toLong() / 1000000).toFloat()
                val totalSpeedTest = (finalDownload + finalUpload) / 2

                position = getPositionByRate(totalSpeedTest)

                runOnUiThread {
                    barImage.rotation = position.toFloat()
                    totalSpeed.text = "" + formatFileSize(totalSpeedCount)
                    val histories : Models = Models()
                    histories.Date = Calendar.getInstance().getTime().toString();
                    histories.Ping = totalSpeed.text.toString()
                    histories.Download = downloadSpeed.text.toString()
                    histories.Upload = uploadSpeed.text.toString()
                    dbHandler?.saveHistory(histories)
                }
                lastPosition = position
            }
        })
//        recycle_history = findViewById(R.id.history)

        dbHandler = DatabaseHelper(this)
        val btn: Button = findViewById(R.id.rotate_button)
        btn.setOnClickListener {
            builder.start(" http://www.speedtest.net/mini.php", 1)

        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.fragment_layout)
        bottomNavigationView.setupWithNavController(navController)
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//        val navController = findNavController(R.id.fragment)

//        bottomNavigationView.setupWithNavController(navController)











        fetchList()
    }




    fun formatFileSize(size:Double): String {
        val hrSize:String
        val b:Double = size
        val k:Double = size/1024.0
        val m:Double = (size/1024.0)/1024.0
        val g:Double = ((size/1024.0)/1024.0)/1024.0
        val t:Double = (((size/1024.0)/1024.0)/1024.0)/1024.0

        val dec = DecimalFormat("0.00")

        when {
            t>1 -> {
                hrSize = dec.format(t)
            }
            g>1 -> {
                hrSize = dec.format(g).plus(" Gbps")
            }
            m>1 -> {
                hrSize = dec.format(m).plus(" Mbps")
            }
            k>1 -> {
                hrSize = dec.format(k).plus(" Kbps")
            }
            else -> {
                hrSize = dec.format(b).plus(" Bps")
            }
        }
        return hrSize
    }

    fun getPositionByRate(rate: Float): Int{
        when {
            rate <= 1 -> {
                return (rate*30).toInt()
            }
            rate <= 10 -> {
                return (rate*6+30).toInt()
            }
            rate <= 30 -> {
                return (((rate - 10) * 3) + 90).toInt()
            }
            rate <= 50 -> {
                return (((rate - 30) * 1.5) + 150).toInt()
            }
            rate <= 100 -> {
                return (((rate - 50) * 1.2) + 180).toInt()
            }
            else -> return 0
        }
    }
}


