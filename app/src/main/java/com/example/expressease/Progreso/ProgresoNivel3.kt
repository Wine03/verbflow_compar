package com.example.expressease.Progreso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import com.example.expressease.BaseDeDatos.DatabaseHelperNiveles
import com.example.expressease.Nivel3.Pregunta1Nivel3
import com.example.expressease.Nivel3.Pregunta2Nivel3
import com.example.expressease.Nivel3.Pregunta3Nivel3
import com.example.expressease.R

class ProgresoNivel3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progreso_nivel3)

        val dbHelper = DatabaseHelperNiveles(this)
        val db = dbHelper.readableDatabase

        var respuestasCorrectas = 0
        var respuestasTotales = 0

        for (i in 1..5) { // Suponemos 3 preguntas
            val cursor = db.rawQuery(
                "SELECT ${DatabaseHelperNiveles.COLUMN_COMPLETADO}, ${DatabaseHelperNiveles.COLUMN_RESPUESTA_CORRECTA} " +
                        "FROM ${DatabaseHelperNiveles.TABLE_NIVELES3} " +
                        "WHERE ${DatabaseHelperNiveles.ID_TABLA3} = $i",
                null
            )

            val completadoIndex = cursor.getColumnIndex(DatabaseHelperNiveles.COLUMN_COMPLETADO)
            val respuestaCorrectaIndex = cursor.getColumnIndex(DatabaseHelperNiveles.COLUMN_RESPUESTA_CORRECTA)

            if (cursor.moveToFirst() && completadoIndex >= 0 && respuestaCorrectaIndex >= 0) {
                do {
                    val completado = cursor.getInt(completadoIndex)
                    val respuestaCorrecta = cursor.getInt(respuestaCorrectaIndex)

                    if (completado == 1) {
                        respuestasTotales++
                        if (respuestaCorrecta == 1) {
                            respuestasCorrectas++
                        }
                    }
                } while (cursor.moveToNext())

                cursor.close()
            }
        }

        // Calcular el porcentaje de respuestas correctas
        val porcentaje = if (respuestasTotales > 0) {
            (respuestasCorrectas * 100) / respuestasTotales
        } else {
            0
        }

        // Actualizar la barra de progreso y el TextView
        val seekBar = findViewById<SeekBar>(R.id.progressBar)
        seekBar.progress = porcentaje

        val textPorcentaje = findViewById<TextView>(R.id.textPorcentaje)
        textPorcentaje.text =
            "Felicidades por tu avance en Nivel 3 con: $porcentaje%"

        // Escuchar los cambios en la barra de progreso
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Actualizar el porcentaje
                val porcentaje = progress.toDouble()

                // Actualizar el TextView
                val textPorcentaje = findViewById<TextView>(R.id.textPorcentaje)
                textPorcentaje.text =
                    "Felicidades por tu avance en Nivel 3 con: $porcentaje%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

        db.close()

    }
}