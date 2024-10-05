package com.srayan.cosmicexplorer

import android.content.ClipData.Item
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.webkit.WebView
import android.webkit.WebViewClient


class News : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Force Dark Mode regardless of system settings
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.BLACK
        }
        setContentView(R.layout.news)


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationBar)

        bottomNavigationView.selectedItemId  = R.id.navNews


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


        val webView: WebView = findViewById(R.id.webView)

        // Enable JavaScript (if needed)
        webView.settings.javaScriptEnabled = true

        // Load the webpage
        webView.loadUrl("https://www.nasa.gov/news/")

        // Set WebViewClient to stay within the app
        webView.webViewClient = WebViewClient()
    }
}
