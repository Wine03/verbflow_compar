package com.example.expressease.BaseDeDatos

import android.content.Context
import android.widget.RadioButton
import android.widget.Toast

class SeleccionRespuesta {
        fun handleAnswerSelection(
            respuestaSeleccionada: RadioButton?,
            respuestaCorrecta: String,
            nivelId: Int,
            dbHelper: DatabaseHelperNiveles,
            tableName: String,
            context: Context,
            textoenviado: String
        ) {

            val respuestaCorrectaInt = if (respuestaSeleccionada != null && respuestaSeleccionada.text == respuestaCorrecta) 1 else 0
            val completado = if (respuestaSeleccionada != null) 1 else 0

            dbHelper.insertOrUpdateNivelResult(tableName, nivelId, completado, respuestaCorrectaInt,textoenviado)

            if (respuestaSeleccionada != null) {
                if (respuestaCorrectaInt == 1) {
                    Toast.makeText(context, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Respuesta incorrecta. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "No seleccionaste una respuesta.", Toast.LENGTH_SHORT).show()
            }
        }
}