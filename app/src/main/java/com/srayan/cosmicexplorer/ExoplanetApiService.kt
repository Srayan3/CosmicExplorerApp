package com.srayan.cosmicexplorer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExoplanetApiService {
    @GET("TAP/sync")
    fun getExoplanets(
        @Query("query") query: String = "SELECT TOP 20 * FROM ps",
        @Query("format") format: String = "json",
        @Query("apiKey") apiKey: String // Corrected this line
    ): Call<List<Exoplanet>>
}