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
import com.example.expressease.R

class Pregunta4Nivel2 : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta4_nivel2)

        mediaPlayer = MediaPlayer.create(this, R.raw.cual_es_la_capital_del_estado_de_yucatan)
        resp1 = MediaPlayer.create(this, R.raw.la_capital_es_merida)
        resp2 = MediaPlayer.create(this, R.raw.merida_es_la_capital_del_estado)

        val res= SeleccionRespuesta()
        val Nivel2P5verificar1 = findViewById<Button>(R.id.Nivel2P5verificar1)

        Nivel2P5verificar1.setOnClickListener {
            val opciones1 = findViewById<RadioGroup>(R.id.Pregunta3Nivel2opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta = "Mérida es la capital del estado" // Cambia esto según la respuesta correcta
            val nivelId = 4 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVELES
            val dbHelper = DatabaseHelperNiveles(this)
            res.handleAnswerSelection(respuestaSeleccionada, respuestaCorrecta, nivelId, dbHelper,tableName,this,"Mérida es la capital del estado")

        }
        val Nivel2SiguienteP5N2=findViewById<Button>(R.id.Nivel2SiguienteP5N2)
        Nivel2SiguienteP5N2.setOnClickListener {

            val intent = Intent(this, Pregunta5Nivel2::class.java)
            startActivity(intent)
            finish()
        }
        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.Pregunta3Nivel2opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.Nivel2P4opcion1_1 -> resp1.start()
                R.id.Nivel2P4opcion1_2 -> resp2.start()
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