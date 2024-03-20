package com.example.expressease.Nivel1

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.expressease.BaseDeDatos.DatabaseHelperNiveles
import java.util.Locale


class Pregunta2Nivel1 : AppCompatActivity() {


    private var REO_CODE_SPEECH_INPUT : Int = 100
    private lateinit var textoEntrada: TextView
    private lateinit var btnHablar: ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.expressease.R.layout.activity_pregunta2_nivel1)


        Initcomponent()

        btnHablar.setOnClickListener {
            iniciarEntradaVoz()
            llenar()
        }


        val btnsiguienteP3N1=findViewById<Button>(com.example.expressease.R.id.btnsiguienteP3N1)
        btnsiguienteP3N1.setOnClickListener {
            val intent = Intent(this, Pregunta3Nivel1::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun llenar() {
        val btnGuardar = findViewById<Button>(com.example.expressease.R.id.btnGuardar)
        btnGuardar.setOnClickListener {

            val nivelId = 2 // Cambia esto según el nivel actual
            val tableName = DatabaseHelperNiveles.TABLE_NIVEL1

            val dbHelper = DatabaseHelperNiveles(this)

            dbHelper.insertOrUpdateNivelResult(tableName, nivelId, 1, 1, textoEntrada.text.toString())
            Toast.makeText(this,"¡Respuesta Guardada!", Toast.LENGTH_SHORT).show()

        }
    }

    fun Initcomponent(){
        textoEntrada=findViewById(com.example.expressease.R.id.textoEntrada)
        btnHablar=findViewById(com.example.expressease.R.id.btnHablar)

    }

    fun iniciarEntradaVoz() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault().toString())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "")

        try {
            startActivityForResult(intent, REO_CODE_SPEECH_INPUT)
        } catch (e: ActivityNotFoundException) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REO_CODE_SPEECH_INPUT -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    textoEntrada.text = result?.get(0)

                }
            }
        }
    }
}