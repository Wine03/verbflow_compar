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
import com.example.expressease.NivelGeneral
import com.example.expressease.R

class Pregunta5Nivel1 : AppCompatActivity() {


    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var resp1: MediaPlayer
    private lateinit var resp2: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pregunta5_nivel1)


        mediaPlayer = MediaPlayer.create(this, R.raw.va_ir_tu_mama_la_escuela)
        resp1 = MediaPlayer.create(this, R.raw.si_va_ir_ella)
        resp2 = MediaPlayer.create(this, R.raw.si_ira_ella_a_la_escuela)



        val btnaudio = findViewById<ImageButton>(R.id.btnaudio1)
        btnaudio.setOnClickListener {
            mediaPlayer.start()
        }

        val radioGroup = findViewById<RadioGroup>(R.id.btnNivel1P5opciones1)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.btnNivel1P5opcion1_1 -> resp1.start()
                R.id.btnNivel1P5opcion1_2 -> resp2.start()
            }
        }

        val respuestacorrecta= SeleccionRespuesta()

        val Nivel1P5verificar1= findViewById<Button>(R.id.Nivel1P5verificar1)
        Nivel1P5verificar1.setOnClickListener {

            val opciones1 = findViewById<RadioGroup>(R.id.btnNivel1P5opciones1)
            val respuestaSeleccionada = findViewById<RadioButton>(opciones1.checkedRadioButtonId)

            val respuestaCorrecta ="Si va ir ella" // Cambia esto según la respuesta correcta
            val nivelId = 5 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVEL1
            val textenviado="Si va ir ella"

            val dbHelper = DatabaseHelperNiveles(this)

            respuestacorrecta.handleAnswerSelection(respuestaSeleccionada, respuestaCorrecta, nivelId, dbHelper,tableName,this,textenviado)

        }

        val Siguientenivel1P6=findViewById<Button>(R.id.Siguientenivel1P6)
        Siguientenivel1P6.setOnClickListener {
            val intent = Intent(this, NivelGeneral::class.java)
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