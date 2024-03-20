package com.example.expressease.ActividadesPrincipales

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.widget.TextView
import com.example.expressease.R
import android.view.animation.AnimationUtils
import android.text.style.ForegroundColorSpan
import android.graphics.Color
import android.widget.ImageView
import com.bumptech.glide.Glide

class Nombre_de_app : AppCompatActivity() {

    private lateinit var textView: TypewriterAnimation
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nombre_de_app)


        textView= findViewById(R.id.textView2)
        val textoCursiva = SpannableString("VerbFlow:Herramienta interactiva para la enseñanza" +
                " de la expresión creativa y comunicativa.")
        // Aplicar diferentes colores a diferentes partes del texto
        textoCursiva.setSpan(ForegroundColorSpan(Color.parseColor("#FFA500")), 0, 9, 0) // Primer color(Naranja) para "VerbFlow:"
            textoCursiva.setSpan(ForegroundColorSpan(Color.parseColor("#318CE7")), 9, 38, 0) // Segundo color(Azul) para "Herramienta interactiva para "
        textoCursiva.setSpan(ForegroundColorSpan(Color.parseColor("#FF97D9")),38, 56, 0) // Tercer color(Rosado) para "la enseñanza de la"
        textoCursiva.setSpan(ForegroundColorSpan(Color.parseColor("#C263F9")), 56, textoCursiva.length, 0)//Cuarto color(Morado) para "expresión creativa y comunicativa"
        textView.text = textoCursiva

        textView.setCharacterDelay(50) // Puedes ajustar la velocidad de la animación
        textView.animateText(textoCursiva)

        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        textView.startAnimation(fadeInAnimation)

        textoCursiva.setSpan(StyleSpan(Typeface.ITALIC), 0, textoCursiva.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = textoCursiva
        textoCursiva.setSpan(StyleSpan(Typeface.BOLD), 0, textoCursiva.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = textoCursiva

        textView.setTextColor(resources.getColor(android.R.color.black, null))
        val duration = 750L // Duración de la animación en milisegundos

        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent = Intent( this, PRINCIPAL:: class.java )
            startActivity(intent)
            finish()
        }, 7550)
    }
}