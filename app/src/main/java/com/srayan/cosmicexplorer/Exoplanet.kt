package com.srayan.cosmicexplorer

data class Exoplanet(
    val planetName: String,       // Maps to "pl_name"
    val planetDiscovery: String,  // Maps to "disc_pubdate" or "disc_year"
    val planetImage: String       // Not provided in the JSON; might need another source
)


// You should remove ExoplanetResponse if it's not used
// Keep only Exoplanet model or adjust based on API response
