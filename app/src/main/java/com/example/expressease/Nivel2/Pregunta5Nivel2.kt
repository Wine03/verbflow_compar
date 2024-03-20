package com.example.expressease.Nivel2

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

class Pregunta5Nivel2 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta5_nivel2)

        mediaPlayer = MediaPlayer.create(this, R.raw.copiaste_lo_que_puso_la_maestra_en_la_pizarra)
        resp1 = MediaPlayer.create(this, R.raw.si_lo_copie)
        resp2 = MediaPlayer.create(this, R.raw.yo_si_lo_copie)

        val res= SeleccionRespuesta()
        val Nivel2P6verificar1 = findViewById<Button>(R.id.Nivel2P6verificar1)

        Nivel2P6verificar1.setOnClickListener {
            val opciones1 = findViewById<RadioGroup>(R.id.Pregunta5Nivel2opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta = "Si lo copie" // Cambia esto según la respuesta correcta
            val nivelId = 5 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVELES
            val dbHelper = DatabaseHelperNiveles(this)
            res.handleAnswerSelection(respuestaSeleccionada, respuestaCorrecta, nivelId, dbHelper,tableName,this,"Si lo copie")

        }
        val Nivel2SiguienteP6N2=findViewById<Button>(R.id.Nivel2SiguienteP6N2)
        Nivel2SiguienteP6N2.setOnClickListener {

            val intent = Intent(this, NivelGeneral::class.java)
            startActivity(intent)
            finish()
        }

        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.Pregunta5Nivel2opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.Nivel2P5opcion1_1 -> resp1.start()
                R.id.Nivel2P5opcion1_2 -> resp2.start()
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