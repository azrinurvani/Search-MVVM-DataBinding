package com.mobile.azrinurvani.databindingdemo

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//TODO 6 - Create Retro Service for retrieve data from API
interface RetroService {

    @GET("repositories")
    fun getDataFromApi(
        @Query("q") query : String
    ): Call<RecyclerList>

}