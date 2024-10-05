package com.srayan.cosmicexplorer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExoplanetApiServiceDetails {
    @GET("TAP/sync")
    fun getExoplanetsDetails(
        @Query("query") query: String,
        @Query("format") format: String = "json",
        @Query("apiKey") apiKey: String // Corrected this line
    ): Call<List<ExoplanetDetails>>
}