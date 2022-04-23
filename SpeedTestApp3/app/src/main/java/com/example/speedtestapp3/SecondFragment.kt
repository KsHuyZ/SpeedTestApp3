package com.example.speedtestapp3

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SecondFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SecondFragment : Fragment(R.layout.fragment_second) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var barImage: ImageView
    lateinit var downloadSpeed: TextView
    lateinit var uploadSpeed: TextView
    lateinit var totalSpeed: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        barImage = view!!.findViewById(R.id.barImageView)
//
//        downloadSpeed = view!!.findViewById(R.id.download)
//        uploadSpeed = view!!.findViewById(R.id.upload)
//       totalSpeed = view!!.findViewById(R.id.total_speed)
//
//        val builder = InternetSpeedBuilder(this)
//        builder.setOnEventInternetSpeedListener(object:
//            InternetSpeedBuilder.OnEventInternetSpeedListener{
//            @SuppressLint("SetTextI18n")
//            override fun onDownloadProgress(count: Int, progressModel: ProgressionModel) {
////                val bigDecimal = BigDecimal("" + progressModel.downloadSpeed)
////                val finalDownload = (bigDecimal.toLong() / 1000000).toFloat()
////
////                val bd: BigDecimal = progressModel.downloadSpeed
////                val d = bd.toDouble()
////
////                position = getPositionByRate(finalDownload)
////
////                runOnUiThread{
////                    val rotateAnimation = RotateAnimation(
////                        lastPosition.toFloat(),
////                        position.toFloat(),
////                        Animation.RELATIVE_TO_SELF,
////                        0.5F,
////                        Animation.RELATIVE_TO_SELF,
////                        0.5F
////                    )
////                    rotateAnimation.interpolator = LinearInterpolator()
////                    rotateAnimation.duration = 2000
////                    barImage.startAnimation(rotateAnimation)
////                    downloadSpeed.text = "" + formatFileSize(d)
////                }
////                lastPosition = position
//            }
//
//            @SuppressLint("SetTextI18n")
//            override fun onUploadProgress(count: Int, progressModel: ProgressionModel) {
////                val bigDecimal = BigDecimal("" + progressModel.uploadSpeed)
////                val finalDownload = (bigDecimal.toLong() / 1000000).toFloat()
////
////                val bd: BigDecimal = progressModel.uploadSpeed
////                val d = bd.toDouble()
////
////                position = getPositionByRate(finalDownload)
////
////                runOnUiThread {
////                    val rotateAnimation = RotateAnimation(
////                        lastPosition.toFloat(),
////                        position.toFloat(),
////                        Animation.RELATIVE_TO_SELF,
////                        0.5f,
////                        Animation.RELATIVE_TO_SELF,
////                        0.5f
////                    )
////                    rotateAnimation.interpolator = LinearInterpolator()
////                    rotateAnimation.duration = 2000
////                    barImage.startAnimation(rotateAnimation)
////                    uploadSpeed.text = "" + formatFileSize(d)
////                }
////                lastPosition = position
//            }
//
//            @SuppressLint("SetTextI18n")
//            override fun onTotalProgress(count: Int, progressModel: ProgressionModel) {
////                val downloadDecimal: BigDecimal = progressModel.downloadSpeed
////                val downloadFinal = downloadDecimal.toDouble()
////
////                val uploadDecimal: BigDecimal = progressModel.uploadSpeed
////                val uploadFinal = uploadDecimal.toDouble()
////                val totalSpeedCount = (downloadFinal + uploadFinal) / 2
////
////                val finalDownload = (downloadDecimal.toLong() / 1000000).toFloat()
////                val finalUpload = (uploadDecimal.toLong() / 1000000).toFloat()
////                val totalSpeedTest = (finalDownload + finalUpload) / 2
////
////                position = getPositionByRate(totalSpeedTest)
////
////                runOnUiThread {
////                    barImage.rotation = position.toFloat()
////                    totalSpeed.text = "" + formatFileSize(totalSpeedCount)
////                }
////                lastPosition = position
//            }
//        })
//
//        val btn: Button = view!!.findViewById(R.id.rotate_button)
//        btn.setOnClickListener {
//            builder.start(" http://www.speedtest.net/mini.php", 1)
//        }
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SecondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SecondFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}