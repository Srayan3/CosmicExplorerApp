package com.srayan.cosmicexplorer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create API service instance
        val apiService = RetrofitClient.retrofit.create(ExoplanetApiService::class.java)
        val apiKey = "swsiqahhhdRkzs0ebOgr2Lvn3wszdElCuWyubTgt"

        apiService.getExoplanets(apiKey).enqueue(object : Callback<List<Exoplanet>> {
            override fun onResponse(call: Call<List<Exoplanet>>, response: Response<List<Exoplanet>>) {
                if (response.isSuccessful) {
                    val exoplanets = response.body() ?: emptyList()
                    val adapter = ExoplanetAdapter(exoplanets)
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<List<Exoplanet>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
