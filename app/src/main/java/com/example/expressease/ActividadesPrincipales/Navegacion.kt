package com.example.expressease.ActividadesPrincipales

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ShapeDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.MenuItem
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.expressease.Nivel1.Nivel1
import com.example.expressease.Nivel1.Pregunta2Nivel1
import com.example.expressease.NivelGeneral
import com.example.expressease.Progreso.Progreso
import com.example.expressease.Progreso.ProgresoNivel1
import com.example.expressease.Progreso.ProgresoNivel3
import com.example.expressease.R
import com.example.expressease.videos.Videos
import com.google.android.material.navigation.NavigationView


class Navegacion : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var textView: TextView
    private lateinit var toogle: ActionBarDrawerToggle
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navegacion)

        if (isFirstTimeUser()) {
            showTutorial()
            setTutorialCompleted()
        }
        textView= findViewById(R.id.textView)
        val textoCursiva = SpannableString("VerbFlow:Herramienta interactiva para la enseñanza" +
                "de la expresión creativa y comunicativa.")
        // Aplicar diferentes colores a diferentes partes del texto
        textoCursiva.setSpan(ForegroundColorSpan(Color.parseColor("#FFA500")), 0, 9, 0) // Primer color(Naranja) para "VerbFlow:"
        textoCursiva.setSpan(ForegroundColorSpan(Color.parseColor("#318CE7")), 9, 38, 0) // Segundo color(Azul) para "Herramienta interactiva para "
        textoCursiva.setSpan(ForegroundColorSpan(Color.parseColor("#FF97D9")),38, 56, 0) // Tercer color(Rosado) para "la enseñanza de la"
        textoCursiva.setSpan(ForegroundColorSpan(Color.parseColor("#C263F9")), 56, textoCursiva.length, 0)//Cuarto color(Morado) para "expresión creativa y comunicativa"
        textView.text = textoCursiva
        textoCursiva.setSpan(StyleSpan(Typeface.ITALIC), 0, textoCursiva.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = textoCursiva
        textoCursiva.setSpan(StyleSpan(Typeface.BOLD), 0, textoCursiva.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = textoCursiva

        val duration = 1000L // Duración de la animación en milisegundos

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toogle = ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toogle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener (this)

        val btnNiveles=findViewById<Button>(R.id.btnNiveles)
        btnNiveles.setOnClickListener {
            val intent = Intent(this, NivelGeneral::class.java)
            startActivity(intent)
            finish()
        }
        val btnVideos=findViewById<Button>(R.id.btnVideos)
        btnVideos.setOnClickListener {
            val intent = Intent(this, Videos::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun isFirstTimeUser(): Boolean {
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFirstTimeUser", true)
    }
    private fun setTutorialCompleted() {
        val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isFirstTimeUser", false)
        editor.apply()
    }

    private fun showTutorial() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Tutorial")
        alertDialogBuilder.setMessage("Mensaje de tutorial inicial.")

        alertDialogBuilder.setPositiveButton("Entendido") { dialog, which ->
            // Aquí puedes manejar la lógica después de que el usuario haya leído el tutorial inicial
            showNivelGeneralMessage()
        }

        alertDialog = alertDialogBuilder.create() // Almacena el diálogo en una variable global

        alertDialog.setOnShowListener {
            val button = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val params = button.layoutParams as ViewGroup.MarginLayoutParams
            params.marginEnd = 20 // Establece el margen derecho según sea necesario
            button.layoutParams = params
            button.setBackgroundResource(R.drawable.bton_cuadrado) // Personaliza el fondo del botón
        }

        alertDialog.show() // Muestra el diálogo
    }

    private fun showNextMessage() {
        val nextMessageBuilder = AlertDialog.Builder(this)
        nextMessageBuilder.setMessage("Mensaje siguiente del tutorial.")

        nextMessageBuilder.setPositiveButton("Ok") { dialog, which ->
            // Aquí puedes manejar la lógica después de que el usuario haya leído el mensaje siguiente
            showNivelGeneralMessage()
        }

        val nextMessageDialog = nextMessageBuilder.create()
        nextMessageDialog.show() // Muestra el siguiente mensaje del tutorial
    }


    private fun showNivelGeneralMessage() {
        val nivelGeneralMessageBuilder = AlertDialog.Builder(this)
        nivelGeneralMessageBuilder.setMessage("Este es el mensaje donde se encuentra el botón 'NivelGeneral'.")

        nivelGeneralMessageBuilder.setPositiveButton("Siguiente") { dialog, which ->
            // Aquí puedes manejar la lógica después de que el usuario haya leído el mensaje para el botón "NivelGeneral"
            showVideosMessage()
        }

        val nivelGeneralMessageDialog = nivelGeneralMessageBuilder.create()

        nivelGeneralMessageDialog.setOnShowListener {
            // Agregar animación alrededor del botón "NivelGeneral"
            val btnNivelGeneral = nivelGeneralMessageDialog.getButton(AlertDialog.BUTTON_POSITIVE)

            // Crear un ShapeDrawable con un borde cuadrado
          //  val shapeDrawable = ShapeDrawable(RED)
           // shapeDrawable.cornerRadius = 10f // Ajustar el radio de las esquinas
           // shapeDrawable.padding = 10f // Asignar padding para el borde (alternativa a setPadding)

            // Establecer el ShapeDrawable como fondo del botón
            //   btnNivelGeneral.background

            // Agregar una animación al borde
            val scaleAnimation = ScaleAnimation(
                1f, 1.1f, 1f, 1.1f, // Escala de 1 a 1.1
                //Animation.RELATIVE_TO_SELF, 0.5f, Animation ELF, 0.5f
            )
            scaleAnimation.duration = 500 // Duración de la animación en milisegundos
            scaleAnimation.repeatCount = Animation.INFINITE // Repetir la animación indefinidamente
            scaleAnimation.repeatMode = Animation.REVERSE // Revertir la animación al final

            btnNivelGeneral.startAnimation(scaleAnimation)

            // Agregar un fondo al botón
         //   btnNivelGeneral.backgroundTint = Color.parseColor("#00FF00") // Color verde
        }

        nivelGeneralMessageDialog.show() // Muestra el mensaje para el botón "NivelGeneral"
    }


    private fun showVideosMessage() {
        val videosMessageBuilder = AlertDialog.Builder(this)
        videosMessageBuilder.setMessage("Este es el mensaje donde se encuentra el botón 'Videos'.")

        videosMessageBuilder.setPositiveButton("Siguiente") { dialog, which ->
            // Aquí puedes manejar la lógica después de que el usuario haya leído el mensaje para el botón "Videos"
            showFinishMessage()
        }

        val videosMessageDialog = videosMessageBuilder.create()
        videosMessageDialog.show() // Muestra el mensaje para el botón "Videos"
    }

    private fun showFinishMessage() {
        val finishMessageBuilder = AlertDialog.Builder(this)
        finishMessageBuilder.setMessage("Tutorial completado.")

        finishMessageBuilder.setPositiveButton("Ok") { dialog, which ->
            // Aquí puedes manejar la lógica después de que el usuario haya leído el mensaje final
            dialog.dismiss() // Cierra el diálogo
        }

        val finishMessageDialog = finishMessageBuilder.create()
        finishMessageDialog.show() // Muestra el mensaje final del tutorial
    }


    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toogle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toogle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)){
            return  true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            //principal
            R.id.nav_item_zero-> {  val intent: Intent = Intent( this, Navegacion :: class.java )
                startActivity(intent)
                finish()}
            //videos
            R.id.nav_item_one-> {  val intent: Intent = Intent( this, Videos :: class.java )
                startActivity(intent)
                finish()}

            //Niveles
            R.id.nav_item_two->   {val intent: Intent = Intent( this, NivelGeneral:: class.java )
            startActivity(intent)
                }

            //Progreso
            R.id.nav_item_Nivel1->   {val intent: Intent = Intent( this, ProgresoNivel1:: class.java )
                startActivity(intent)
                }

            R.id.nav_item_Nivel2->   {val intent: Intent = Intent( this, Progreso:: class.java )
                startActivity(intent)
                }

            R.id.nav_item_Nivel3->   {val intent: Intent = Intent( this, ProgresoNivel3:: class.java )
                startActivity(intent)
                }

            R.id.nav_item_for-> { val intent = Intent(this, PRINCIPAL::class.java)
                startActivity(intent)
                finish()}
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

}