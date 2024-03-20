package com.example.expressease.Nivel1

import android.annotation.SuppressLint
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

class Pregunta3Nivel1 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta3_nivel1)

        mediaPlayer = MediaPlayer.create(this, R.raw.pregunta_que_haras_el_domingo)
        resp1 = MediaPlayer.create(this, R.raw.voy_a_jugar_futbol)
        resp2 = MediaPlayer.create(this, R.raw.ire_a_jugar_futbol)



        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.btnNivel1P3opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btnNivel1P3opcion1_1 -> resp1.start()
                R.id.btnNivel1P3opcion1_2 -> resp2.start()
            }
        }

        val respuestacorrecta= SeleccionRespuesta()
        val Nivel1P3verificar1= findViewById<Button>(R.id.Nivel1P3verificar1)
        Nivel1P3verificar1.setOnClickListener {

            val opciones1 = findViewById<RadioGroup>(R.id.btnNivel1P3opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta ="Voy a jugar futbol" // Cambia esto según la respuesta correcta
            val nivelId = 3 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVEL1
            val textenviado="Voy a jugar futbol"

            val dbHelper = DatabaseHelperNiveles(this)

            respuestacorrecta.handleAnswerSelection(respuestaSeleccionada, respuestaCorrecta, nivelId, dbHelper,tableName,this,textenviado)

        }

        val Siguientenivel1P4=findViewById<Button>(R.id.Siguientenivel1P4)
        Siguientenivel1P4.setOnClickListener {
            val intent = Intent(this, Pregunta4Nivel1::class.java)
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