package com.example.expressease.Nivel3

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.expressease.BaseDeDatos.DatabaseHelperNiveles
import com.example.expressease.BaseDeDatos.SeleccionRespuesta
import com.example.expressease.R

class Pregunta1Nivel3 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer
    private lateinit var resp3: MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta1_nivel3)

        mediaPlayer = MediaPlayer.create(this, R.raw.pregunta1)
        resp1 = MediaPlayer.create(this, R.raw.respuesta1)
        resp2 = MediaPlayer.create(this, R.raw.respuesta2)
        resp3 = MediaPlayer.create(this, R.raw.respuesta3)


        val respuestacorrecta= SeleccionRespuesta()

        val btnNivel3P1verificar1 = findViewById<Button>(R.id.btnNivel3P1verificar1)
        btnNivel3P1verificar1.setOnClickListener {
            val opciones1 = findViewById<RadioGroup>(R.id.btnNivel3P1opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta ="Si la terminé " // Cambia esto según la respuesta correcta
            val nivelId = 1 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVELES3
            val textenviado="Si la termine"

            val dbHelper = DatabaseHelperNiveles(this)

            respuestacorrecta.handleAnswerSelection(respuestaSeleccionada, respuestaCorrecta, nivelId, dbHelper,tableName,this,textenviado)
        }
        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.btnNivel3P1opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btnNivel3P1opcion1_1 -> resp1.start()
                R.id.btnNivel3P1opcion1_2 -> resp2.start()
                R.id.btnNivel3P1opcion1_3 -> resp3.start()
            }
        }

        val btnNivel3pregunta2= findViewById<Button>(R.id.btnNivel3pregunta2)
        btnNivel3pregunta2.setOnClickListener {
            val intent = Intent(this, Pregunta2Nivel3::class.java)
            startActivity(intent)
            finish()
        }


    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        resp1.release()
        resp2.release()
        resp3.release()
    }

}