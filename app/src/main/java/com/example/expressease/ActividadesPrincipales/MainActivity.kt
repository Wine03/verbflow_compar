package com.example.expressease.ActividadesPrincipales

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.example.expressease.R
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var imagenView = findViewById<ImageView>(R.id.imageView7)
        Glide.with(this)
            .load(R.drawable.verbflowgif)
            .override(resources.displayMetrics.widthPixels, resources.displayMetrics.heightPixels)
            .centerCrop()
            .into(imagenView);

        Handler(Looper.getMainLooper()).postDelayed({
                val intent: Intent = Intent( this, Nombre_de_app:: class.java )
                startActivity(intent)
            finish()
        }, 3500)
    }
}