package com.example.expressease.Nivel2

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

class Pregunta2Nivel2 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta2_nivel2)


        mediaPlayer = MediaPlayer.create(this, R.raw.fuiste_a_la_playa_el_lunes)
        resp1 = MediaPlayer.create(this, R.raw.claro_que_si_fui)
        resp2 = MediaPlayer.create(this, R.raw.si_fui_a_la_playa_yo)



        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.Pregunta3Nivel2opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.Nivel2P3opcion1_1 -> resp1.start()
                R.id.Nivel2P3opcion1_2 -> resp2.start()
            }
        }

        val res= SeleccionRespuesta()
        val Nivel2P3verificar1 = findViewById<Button>(R.id.Nivel2P3verificar1)

        Nivel2P3verificar1.setOnClickListener {
            val opciones1 = findViewById<RadioGroup>(R.id.Pregunta3Nivel2opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta = "Claro que si fui" // Cambia esto según la respuesta correcta
            val nivelId = 2 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVELES
            val dbHelper = DatabaseHelperNiveles(this)
            res.handleAnswerSelection(respuestaSeleccionada, respuestaCorrecta, nivelId, dbHelper,tableName,this,"Claro que si fui")

        }
        val Nivel2SiguienteP4N2=findViewById<Button>(R.id.Nivel2SiguienteP4N2)
        Nivel2SiguienteP4N2.setOnClickListener {

            val intent = Intent(this, Pregunta3Nivel2::class.java)
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