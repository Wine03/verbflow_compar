package com.example.expressease.Nivel1

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.expressease.BaseDeDatos.DatabaseHelperNiveles
import com.example.expressease.BaseDeDatos.SeleccionRespuesta
import com.example.expressease.R

class Pregunta4Nivel1 : AppCompatActivity() {


    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta4_nivel1)

        mediaPlayer = MediaPlayer.create(this, R.raw.donde_esta_mi_masco)
        resp1 = MediaPlayer.create(this, R.raw.tu_mascota_esta_en_mi_casa)
        resp2 = MediaPlayer.create(this, R.raw.la_mascota_esta_en_la_casa)



        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.btnNivel1P4opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btnNivel1P4opcion1_1 -> resp1.start()
                R.id.btnNivel1P4opcion1_2 -> resp2.start()
            }
        }

        val respuestacorrecta= SeleccionRespuesta()

        val Nivel1P4verificar1= findViewById<Button>(R.id.Nivel1P4verificar1)
        Nivel1P4verificar1.setOnClickListener {

            val opciones1 = findViewById<RadioGroup>(R.id.btnNivel1P4opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta ="La mascota está en la casa" // Cambia esto según la respuesta correcta
            val nivelId = 4 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVEL1
            val textenviado="La mascota está en la casa"

            val dbHelper = DatabaseHelperNiveles(this)

            respuestacorrecta.handleAnswerSelection(respuestaSeleccionada, respuestaCorrecta, nivelId, dbHelper,tableName,this,textenviado)

        }

        val Siguientenivel1P5=findViewById<Button>(R.id.Siguientenivel1P5)
        Siguientenivel1P5.setOnClickListener {
            val intent = Intent(this, Pregunta5Nivel1::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        resp1.release()
        resp2.release()
    }
}