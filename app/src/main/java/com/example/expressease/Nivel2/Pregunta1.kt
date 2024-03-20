package com.example.expressease.Nivel2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.expressease.BaseDeDatos.DatabaseHelperNiveles
import com.example.expressease.BaseDeDatos.SeleccionRespuesta
import com.example.expressease.Nivel1.Pregunta1Nivel1
import com.example.expressease.NivelGeneral
import com.example.expressease.R

class Pregunta1 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta1)


        mediaPlayer = MediaPlayer.create(this, R.raw.fuiste_tu_el_que_se_cayo_de_la_bicicleta)
        resp1 = MediaPlayer.create(this, R.raw.si_era_yo_el_que_se_cayo)
        resp2 = MediaPlayer.create(this, R.raw.si_era_el_que_se_cayo)


        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.Pregunta2Nivel2opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.Nivel2P2opcion1_1 -> resp1.start()
                R.id.Nivel2P2opcion1_2 -> resp2.start()
            }
        }

        val res= SeleccionRespuesta()
        val Nivel2verificar1 = findViewById<Button>(R.id.Nivel2verificar1)

        Nivel2verificar1.setOnClickListener {
            val opciones1 = findViewById<RadioGroup>(R.id.Pregunta2Nivel2opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta = "Si, era yo el que se cayó" // Cambia esto según la respuesta correcta
            val nivelId = 1 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVELES
            val dbHelper = DatabaseHelperNiveles(this)
            res.handleAnswerSelection(respuestaSeleccionada, respuestaCorrecta, nivelId, dbHelper,tableName,this,"Si, era yo el que se cayó")

        }
        val Nivel2SiguienteP3N2=findViewById<Button>(R.id.Nivel2SiguienteP3N2)
        Nivel2SiguienteP3N2.setOnClickListener {

            val intent = Intent(this, Pregunta2Nivel2::class.java)
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