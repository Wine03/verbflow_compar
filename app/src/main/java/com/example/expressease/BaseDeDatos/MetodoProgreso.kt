package com.example.expressease.BaseDeDatos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.SeekBar
import android.widget.TextView

class MetodoProgreso {

        fun actualizarProgreso(
            context: Context,
            db: SQLiteDatabase,
            nivelTableName: String,
            nivelIdColumn: String,
            totalPreguntas: Int,
            seekBar: SeekBar,
            textPorcentaje: TextView
        ) {
            var respuestasCorrectas = 0
            var respuestasTotales = 0

            for (i in 1..totalPreguntas) {
                val cursor = db.rawQuery(
                    "SELECT ${DatabaseHelperNiveles.COLUMN_COMPLETADO}, ${DatabaseHelperNiveles.COLUMN_RESPUESTA_CORRECTA} " +
                            "FROM $nivelTableName " +
                            "WHERE $nivelIdColumn = $i",
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

            val porcentaje = if (respuestasTotales > 0) {
                (respuestasCorrectas * 100) / respuestasTotales
            } else {
                0
            }
            seekBar.progress = porcentaje
            textPorcentaje.text = "Progreso: $porcentaje%"
        }
}