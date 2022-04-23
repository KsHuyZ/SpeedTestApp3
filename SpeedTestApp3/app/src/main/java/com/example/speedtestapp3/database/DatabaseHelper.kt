package com.example.speedtestapp3.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.icu.number.IntegerWidth
import com.example.speedtestapp3.History
import com.example.speedtestapp3.Models.Models

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "History.db"
        private  val DATABASE_VERSION = 1

        val HISTORY_TABLE = "History"
        val ID = "ID"

        val DATE = "Date"
        val downloadSpeed = "Download"
        val uploadSpeed = "Upload"
        val PING = "Ping"


    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE ${HISTORY_TABLE} ("+
                "${ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DATE} TEXT,"+
                "${downloadSpeed} DOUBLE DEFAULT 0," +
                "${uploadSpeed} DOUBLE DEFAULT 0," +
                "${PING} DOUBLE DEFAULT 0)")
        p0?.execSQL(createTable)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
     val dropTable = "DROP TABLE IF EXISTS $HISTORY_TABLE"
        p0?.execSQL(dropTable)
        onCreate(p0)
    }
    @SuppressLint("Range")
    fun getAllHistory(): List<Models>{
        val historyList   = ArrayList<Models>()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $HISTORY_TABLE"
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    val history = Models()
                    history.ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))

                    history.Date = cursor.getString(cursor.getColumnIndex(DATE))
                    history.Download = cursor.getString(cursor.getColumnIndex(downloadSpeed))
                    history.Upload = cursor.getString(cursor.getColumnIndex(uploadSpeed))
                    history.Ping = cursor.getString(cursor.getColumnIndex(PING))
                    historyList.add(history)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return historyList
    }
     fun saveHistory(history: Models):Boolean{
         val db = this.writableDatabase
         val values = ContentValues()

         values.put(DATE,history.Date)
         values.put(downloadSpeed,history.Download)
         values.put(uploadSpeed,history.Upload)
         values.put(PING, history.Ping)
         val success = db.insert(HISTORY_TABLE,null,values)
         db.close()
         return (Integer.parseInt("$success") !=-1)
     }
    @SuppressLint("Range")
    fun getHistorydetail(id: Int): Models{
        val history = Models()
        val db = writableDatabase
        val selectQuery = "SELECT * FROM $HISTORY_TABLE WHERE $ID = $id"
        val cursor = db.rawQuery(selectQuery, null)
        cursor?.moveToFirst()
        history.ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))

        history.Date = cursor.getString(cursor.getColumnIndex(DATE))
        history.Download = cursor.getString(cursor.getColumnIndex(downloadSpeed))
        history.Upload = cursor.getString(cursor.getColumnIndex(uploadSpeed))
        history.Ping = cursor.getString(cursor.getColumnIndex(PING))
return history
    }
    fun delHistory(id:Int):Boolean{
        val db = writableDatabase
        val success = db.delete(HISTORY_TABLE, ID +"=?", arrayOf(id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }
}