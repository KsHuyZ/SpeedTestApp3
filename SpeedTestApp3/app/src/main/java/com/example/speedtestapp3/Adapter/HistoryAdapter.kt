package com.example.speedtestapp3.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.speedtestapp3.Models.Models
import com.example.speedtestapp3.R

class HistoryAdapter (historyList: List<Models>, internal var context: Context) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    internal var historyList : List<Models> = ArrayList()
    init {
        this.historyList = historyList
    }
    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var date : TextView = view.findViewById(R.id.date)
        var download : TextView = view.findViewById(R.id.download_speed)
        var upload : TextView = view.findViewById(R.id.upload_speed)
        var ping : TextView = view.findViewById(R.id.ping)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
     val view = LayoutInflater.from(context).inflate(R.layout.fragment_firstt,parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
    val history = historyList[position]

        holder.date.text = history.Date
        holder.download.text = history.Download.toString()
        holder.upload.text = history.Upload.toString()
        holder.ping.text = history.Ping.toString()
    }

    override fun getItemCount(): Int {
     return historyList.size
    }
}