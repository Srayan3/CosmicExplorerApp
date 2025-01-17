package com.srayan.cosmicexplorer

import android.content.ClipData.Item
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Force Dark Mode regardless of system settings
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.BLACK
        }
        setContentView(R.layout.home_page)


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationBar)

        bottomNavigationView.selectedItemId  = R.id.navHome


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHome -> {
                    startActivity(Intent(this, HomePage::class.java))
                    true
                }

                R.id.navWebsite -> {
                    // Handle search click
                    startActivity(Intent(this, AboutUs::class.java))
                    true
                }

                R.id.navNews -> {
                    startActivity(Intent(this, News::class.java))
                    true
                }

                else -> false
            }
        }


        // Set up RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create API service instance
        val apiService = RetrofitClient.retrofit.create(ExoplanetApiService::class.java)

        apiService.getExoplanets(apiKey = "swsiqahhhdRkzs0ebOgr2Lvn3wszdElCuWyubTgt").enqueue(object : Callback<List<Exoplanet>> {
            override fun onResponse(call: Call<List<Exoplanet>>, response: Response<List<Exoplanet>>) {
                if (response.isSuccessful) {
                    val progessBar = findViewById<ProgressBar>(R.id.progressBar)
                    progessBar.visibility = View.GONE
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
