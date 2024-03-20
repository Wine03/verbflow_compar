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
import com.example.expressease.BaseDeDatos.DatabaseHelperNiveles
import com.example.expressease.BaseDeDatos.SeleccionRespuesta
import com.example.expressease.NivelGeneral
import com.example.expressease.R

class Pregunta4Nivel3 : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta4_nivel3)

        mediaPlayer = MediaPlayer.create(this, R.raw.cual_es_la_materia_que_mas_te_gusta_matematicas_o_espanol)
        resp1 = MediaPlayer.create(this, R.raw.me_gusta_la_materia_de_matematicas)
        resp2 = MediaPlayer.create(this, R.raw.a_mi_me_gusta_la_materia_de_matematicas)

        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.btnNivel3P4opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btnNivel3P4opcion1_1 -> resp1.start()
                R.id.btnNivel3P4opcion1_2 -> resp2.start()
            }
        }

        val respuestacorrecta= SeleccionRespuesta()

        val btnNivel3P4verificar1 = findViewById<Button>(R.id.btnNivel3P4verificar1)
        btnNivel3P4verificar1.setOnClickListener {
            val opciones1 = findViewById<RadioGroup>(R.id.btnNivel3P4opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta = "Me gusta la materia de matemática" // Cambia esto según la respuesta correcta
            val nivelId = 4 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVELES3

            val dbHelper = DatabaseHelperNiveles(this)

            respuestacorrecta.handleAnswerSelection(respuestaSeleccionada, respuestaCorrecta, nivelId, dbHelper,tableName,this,"Me gusta la materia de matemática")

        }

        val btnRegresarNicelprincipal = findViewById<Button>(R.id.btnRegresarNicelprincipal)
        btnRegresarNicelprincipal.setOnClickListener {

            val intent = Intent(this, Pregunta5Nivel3::class.java)

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