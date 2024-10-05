package com.srayan.cosmicexplorer

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AppCompatDelegate
import android.content.Intent
import android.widget.Button



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Force Dark Mode regardless of system settings
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val guestLoginButton: Button = findViewById(R.id.guestLogin)

        guestLoginButton.setOnClickListener(){
            val homepage = Intent(this, HomePage::class.java)
            startActivity(homepage)
        }

        val guestLoginButton2: Button = findViewById(R.id.loginButton)

        guestLoginButton2.setOnClickListener(){
            val homepage = Intent(this, HomePage::class.java)
            startActivity(homepage)
        }

        val guestLoginButton3: Button = findViewById(R.id.signUpButton)

        guestLoginButton3.setOnClickListener(){
            val homepage = Intent(this, HomePage::class.java)
            startActivity(homepage)
        }
    }
}