package com.example.silverlabs.rest

import com.example.silverlabs.datamodel.Celebrity
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("/bins/6e60g")
    fun getClelebrities() : Call<List<Pair<String,Celebrity>>>

    companion object {

        var BASE_URL = "https://api.myjson.com"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}