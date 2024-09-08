package com.srayan.cosmicexplorer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ExoplanetAdapter(private val exoplanets: List<Exoplanet>) :
    RecyclerView.Adapter<ExoplanetAdapter.ExoplanetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExoplanetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return ExoplanetViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExoplanetViewHolder, position: Int) {
        val exoplanet = exoplanets[position]
        holder.name.text = exoplanet.planetName
        holder.discoveryDate.text = exoplanet.planetDiscovery
        Glide.with(holder.itemView.context).load(exoplanet.planetImage).into(holder.image)
    }

    override fun getItemCount(): Int = exoplanets.size

    class ExoplanetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.exoplanet_name)
        val discoveryDate: TextView = view.findViewById(R.id.discovery_date)
        val image: ImageView = view.findViewById(R.id.exoplanet_image)
    }
}
