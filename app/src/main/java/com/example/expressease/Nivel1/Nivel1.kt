package com.example.expressease.Nivel1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.expressease.ActividadesPrincipales.PRINCIPAL
import com.example.expressease.BaseDeDatos.DatabaseHelper
import com.example.expressease.R

class Nivel1 : AppCompatActivity() {
    private lateinit var circularButton: ImageButton
    private lateinit var cbNivel1_2: ImageButton
    private lateinit var cbNivel1_3: ImageButton

    private lateinit var tvUsuario: TextView

    private lateinit var cbNivel1_4: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivel1)
        initComponent()

       circularButton.setOnClickListener{
            val intent = Intent(this,Pregunta1Nivel1::class.java)
            startActivity(intent)
        }

        /*   cbNivel1_2.setOnClickListener{
              val intent = Intent(this,Ejemplo2::class.java)
              startActivity(intent)

          }
          cbNivel1_3.setOnClickListener{
              val intent = Intent(this, Ejemplo3::class.java)
              startActivity(intent)
          }*/

        cbNivel1_4.setOnClickListener {
            val intent = Intent(this, Pregunta2Nivel1::class.java)
            startActivity(intent)
        }
    }

    private fun initComponent(){
        circularButton= findViewById(R.id.circularButton)
        cbNivel1_2= findViewById(R.id.cbNivel1_2)
        cbNivel1_3= findViewById(R.id.cbNivel1_3)
      /*  tvUsuario= findViewById(R.id.tvUsuario)*/
        cbNivel1_4=findViewById(R.id.cbNivel1_4)
    }
}