package com.example.secureapp

import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {


    companion object {
        const val TAG = "SecureApp"
        const val USERNAME = "CS4264"
        const val URL = "https://securemobileapp.herokuapp.com/"
       // const val ROUTE ="/a"
        @RequiresApi(Build.VERSION_CODES.N)
        val DATE = Calendar.getInstance().time.toString()

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
