package com.srayan.cosmicexplorer

data class ExoplanetDetails(
    val pl_name: String,       // Maps to "pl_name"
    val disc_year: String,  // Maps to "disc_pubdate" or "disc_year"
    val hostname: String,
    val disc_pubdate: String,
    val discoverymethod: String,
    val x: String,
    val y: String,
    val z: String
)