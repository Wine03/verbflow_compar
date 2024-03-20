package com.example.expressease.Nivel3

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.expressease.R

class Nivel3General : AppCompatActivity() {

    private lateinit var btnNivel3_1: Button
    private lateinit var btnNivel3_2: Button
    private lateinit var btnNivel3_3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivel3_general)

        btnNivel3_1= findViewById(R.id.btnNivel3_1)

        btnNivel3_1.setOnClickListener {
            val intent = Intent(this, Pregunta1Nivel3::class.java)
            startActivity(intent)
        }

        btnNivel3_2= findViewById(R.id.btnNivel3_2)

        btnNivel3_2.setOnClickListener {
            val intent = Intent(this, Pregunta2Nivel3::class.java)
            startActivity(intent)
        }

        btnNivel3_3= findViewById(R.id.btnNivel3_3)

        btnNivel3_3.setOnClickListener {
            val intent = Intent(this, Pregunta3Nivel3::class.java)
            startActivity(intent)
        }
    }
}