package com.example.expressease.Nivel2
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.expressease.BaseDeDatos.DatabaseHelperNiveles
import com.example.expressease.R

class Nivel2_Principal : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelperNiveles
    private lateinit var btnCuestionar1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivel2_principal)

        dbHelper = DatabaseHelperNiveles(this)

        btnCuestionar1=findViewById(R.id.btnCuestionar1)

        btnCuestionar1.setOnClickListener {
            val intent = Intent(this, Pregunta1::class.java)
            startActivity(intent)
        }

    }

}