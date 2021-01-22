package com.mobile.azrinurvani.databindingdemo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO 5 - Create RetroInstance for Retrofit Builder
class RetroInstance {

    companion object{
        val BASE_URL = "https://api.github.com/search/"

        fun getRetroInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        var service = getRetroInstance().create(RetroService::class.java)
    }

}