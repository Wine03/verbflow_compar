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

class Pregunta2Nivel3 : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer
    private lateinit var resp3: MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta2_nivel3)

        mediaPlayer = MediaPlayer.create(this, R.raw.cual_es_la_manera_correcta_de_pedir_permiso_para_ir_a_jugar_futbol)
        resp1 = MediaPlayer.create(this, R.raw.puedo_ir_a_jugar_futbol)
        resp2 = MediaPlayer.create(this, R.raw.me_da_permiso_para_ir_a_jugar_futbol)
        resp3 = MediaPlayer.create(this, R.raw.ahora_vengo_voy_a_jugar_futbol)


        val Nivel3P2verificar1 = findViewById<Button>(R.id.Nivel3P2verificar1)
        Nivel3P2verificar1.setOnClickListener {
            val respuestacorrecta = SeleccionRespuesta()
            val opciones1 = findViewById<RadioGroup>(R.id.btnNivel3P2opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta = "¿Me da permiso para ir a jugar futbol?" // Cambia esto según la respuesta correcta
            val nivelId = 2 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVELES3

            val dbHelper = DatabaseHelperNiveles(this)

            respuestacorrecta.handleAnswerSelection(
                respuestaSeleccionada,
                respuestaCorrecta,
                nivelId,
                dbHelper,
                tableName,
                this,
                "¿Me da permiso para ir a jugar futbol?"
            )

        }

        val Siguientenivel3P3 = findViewById<Button>(R.id.Siguientenivel3P3)
        Siguientenivel3P3.setOnClickListener {
            val intent = Intent(this, Pregunta3Nivel3::class.java)

            startActivity(intent)
            finish()
        }

        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.btnNivel3P2opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btnNivel3P2opcion1_1 -> resp1.start()
                R.id.btnNivel3P2opcion1_2 -> resp2.start()
                R.id.btnNivel3P2opcion1_3 -> resp3.start()
            }
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