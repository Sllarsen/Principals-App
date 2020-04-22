package com.example.secureapp

import com.google.gson.GsonBuilder
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface  RetrofitService {



    @POST(value = "scanner/a")
    fun getClickedApp(@Body json: JSONObject): Call<ResponseBody>



    companion object {
        fun create(baseUrl: String): RetrofitService {

            val retrofit = Retrofit.Builder() .addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .baseUrl(baseUrl)
                .build()


            return retrofit.create(RetrofitService::class.java)
        }
    }
}