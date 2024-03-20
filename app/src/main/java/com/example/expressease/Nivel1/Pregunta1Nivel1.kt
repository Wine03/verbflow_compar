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
import com.example.expressease.Nivel3.Pregunta2Nivel3
import com.example.expressease.R

class Pregunta1Nivel1 : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta1_nivel1)

        mediaPlayer = MediaPlayer.create(this, R.raw.te_gusta_salir_de_viaje_con_tu_familia)
        resp1 = MediaPlayer.create(this, R.raw.si_me_gusta_salir_de_viaje_con_ellos)
        resp2 = MediaPlayer.create(this, R.raw.a_mi_si_me_gusta_ir_de_viaje_con_ellos)

        val respuestacorrecta= SeleccionRespuesta()
        val btnNivel1P1verificar1=findViewById<Button>(R.id.btnNivel1P1verificar1)
        btnNivel1P1verificar1.setOnClickListener {

            val opciones1 = findViewById<RadioGroup>(R.id.btnNivel1P1opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta ="Si me gusta salir de viaje con ellos" // Cambia esto según la respuesta correcta
            val nivelId = 1 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVEL1
            val textenviado="Si me gusta salir de viaje con ellos"

            val dbHelper = DatabaseHelperNiveles(this)

            respuestacorrecta.handleAnswerSelection(respuestaSeleccionada, respuestaCorrecta, nivelId, dbHelper,tableName,this,textenviado)

        }

        val btnNivel1pregunta2=findViewById<Button>(R.id.btnNivel1pregunta2)
        btnNivel1pregunta2.setOnClickListener {
            val intent = Intent(this, Pregunta2Nivel1::class.java)
            startActivity(intent)
            finish()
        }

        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.btnNivel1P1opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btnNivel1P1opcion1_1 -> resp1.start()
                R.id.btnNivel1P1opcion1_2 -> resp2.start()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        resp1.release()
        resp2.release()
    }
}