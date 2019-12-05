package com.example.flightmanager.data.network

import com.example.flightmanager.data.model.AirPortModel
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface AirPortApiService {
    @Headers("x-rapidapi-host: cometari-airportsfinder-v1.p.rapidapi.com",
                      "x-rapidapi-key: edb36eff99msh6d65e49b6767a7fp181c95jsn9c173b4279f9")
    @GET("/api/airports/by-code")
    fun getAirPort(@Query("code")code:String):Deferred<List<AirPortModel>>

    companion object{
        fun create():AirPortApiService{
            val retrofit=Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://cometari-airportsfinder-v1.p.rapidapi.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
            return retrofit.create(AirPortApiService::class.java)
        }
    }
}