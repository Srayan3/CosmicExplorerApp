package com.srayan.cosmicexplorer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExoplanetApiService {
    @GET("TAP/sync")
    fun getExoplanets(
        @Query("query") query: String = "SELECT TOP 10 * FROM ps",
        @Query("format") format: String = "json",
        @Query("api_key") apiKey: String
    ): Call<List<ExoplanetResponse>>
}
