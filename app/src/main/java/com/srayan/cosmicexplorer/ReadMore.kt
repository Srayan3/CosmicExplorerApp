package com.srayan.cosmicexplorer

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.graphics.Color
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.bottomnavigation.BottomNavigationView

class ReadMore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.BLACK
        }
        setContentView(R.layout.activity_read_more)

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

        // Retrieve the data from the Intent
        val plName = intent.getStringExtra("pl_name")
        val plNameURLMaker = plName.toString()
        val plNameURLMakerTwo = plNameURLMaker.replace(" ", "%20")
        val imageViewBoxSrn: ImageView = findViewById(R.id.exoplanet_image_imported)
        Glide.with(this)
            .load("https://srayan3.github.io/res/$plNameURLMakerTwo.webp")
            .into(imageViewBoxSrn)

        val apiServiceDetailsSrn = RetrofitClient.retrofit.create(ExoplanetApiServiceDetails::class.java)

        apiServiceDetailsSrn.getExoplanetsDetails(
            apiKey = "swsiqahhhdRkzs0ebOgr2Lvn3wszdElCuWyubTgt",
            query = "SELECT * FROM ps WHERE pl_name='$plNameURLMaker'"
        ).enqueue(object : Callback<List<ExoplanetDetails>> {
            override fun onResponse(call: Call<List<ExoplanetDetails>>, response: Response<List<ExoplanetDetails>>) {
                if (response.isSuccessful) {
                    val progrrssBar = findViewById<ProgressBar>(R.id.progressBar3)
                    progrrssBar.visibility = View.GONE
                    // Get the list of ExoplanetDetails from the response
                    val exoList: List<ExoplanetDetails>? = response.body()

                    // Make sure the list is not null or empty
                    if (!exoList.isNullOrEmpty()) {
                        // Access the first item in the list
                        val exo = exoList[0] // Example: getting the first item
                        val discDate = findViewById<TextView>(R.id.discDate)
                        val planetDetails = findViewById<TextView>(R.id.PlanetDetails)
                        val diskYearAPI = exo.disc_year
                        discDate.text = "Discovery: $diskYearAPI"
                        planetDetails.text = "The exoplanet,\n\n${exo.pl_name} was first discovered by the Astrologist in ${exo.disc_year}, later is was released publicly on ${exo.disc_pubdate}\n\n${exo.pl_name} was discovered through ${exo.discoverymethod} method.\n\n${exo.pl_name} revolves around her host start, ${exo.hostname}\n\nCordinates:\nX: ${exo.x}\nY: ${exo.y}\nZ: ${exo.z}"
                    }
                }
            }

            override fun onFailure(call: Call<List<ExoplanetDetails>>, t: Throwable) {
                // Handle failure
            }
        })




        // Find the TextView and set the data

        val textView = findViewById<TextView>(R.id.exoplanet_name_text_view)
        textView.text = plName

        val webView = findViewById<WebView>(R.id.myWebView)

        // Enable JavaScript if needed
        webView.settings.javaScriptEnabled = true

        // Ensure links and redirects open inside the WebView, not in a browser
        webView.webViewClient = WebViewClient()

        val eoxpLink = plName?.replace(" ", "_") ?: "OLGE-TR-10_b"

        val finalLink = "https://eyes.nasa.gov/apps/exo/#/planet/" + eoxpLink
        // Load a specific URL
        webView.loadUrl(finalLink)

    }
}
