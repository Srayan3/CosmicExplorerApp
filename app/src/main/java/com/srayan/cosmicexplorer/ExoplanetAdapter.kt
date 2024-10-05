package com.srayan.cosmicexplorer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.content.Intent

class ExoplanetAdapter(private val exoplanets: List<Exoplanet>) :
    RecyclerView.Adapter<ExoplanetAdapter.ExoplanetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExoplanetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return ExoplanetViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExoplanetViewHolder, position: Int) {
        val exoplanet = exoplanets[position]
        holder.name.text = exoplanet.pl_name
        holder.discoveryDate.text = "Discovery Year: ${exoplanet.disc_year}"

        // Replace spaces in planet names for image URLs
        val planetNameModSrn = exoplanet.pl_name.replace(" ", "%20")

        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load("https://srayan3.github.io/res/$planetNameModSrn.webp")
            .into(holder.image)

        // Corrected: Set up button click listener
        holder.readMoreButtonSrn.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, ReadMore::class.java)
            intent.putExtra("pl_name", exoplanet.pl_name)
            context.startActivity(intent)  // Start the ReadMore activity
        }
    }

    override fun getItemCount(): Int = exoplanets.size

    class ExoplanetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.exoplanet_name)
        val discoveryDate: TextView = view.findViewById(R.id.discovery_date)
        val image: ImageView = view.findViewById(R.id.exoplanet_image)
        val readMoreButtonSrn: androidx.appcompat.widget.AppCompatButton = view.findViewById(R.id.read_more_button)
    }
}
