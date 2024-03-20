package com.example.expressease.videos

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.MediaController
import android.widget.VideoView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.expressease.R

class video_presentacion : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_presentacion)

        val videoView = findViewById<VideoView>(R.id.videoViewpresentacion)
        // Ruta del video en tu almacenamiento local o URL del video en línea
        val videoPath = "android.resource://" + packageName + "/" + R.raw.presen_verb_flow
        val uri = Uri.parse(videoPath)

        // Configurar MediaController para controlar la reproducción del video
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        // Establecer la URI del video en el VideoView
        videoView.setVideoURI(uri)

        // Iniciar la reproducción del video
        videoView.start()

        // Ajustar el tamaño del VideoView para que ocupe toda la pantalla
        videoView.setOnPreparedListener { mp ->
            val videoWidth = mp.videoWidth
            val videoHeight = mp.videoHeight
            val videoProportion = videoWidth.toFloat() / videoHeight.toFloat()

            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            val screenWidth = displayMetrics.widthPixels
            val screenHeight = displayMetrics.heightPixels

            val lp = videoView.layoutParams as ConstraintLayout.LayoutParams

            // Calcula el ancho del VideoView como el 90% del ancho de la pantalla
            val videoWidth90Percent = (screenWidth * 0.9).toInt()

            lp.width = videoWidth90Percent
            lp.height = (videoWidth90Percent / videoProportion).toInt()

            // Calcula el margen del 10% de la pantalla en ambos lados
            val horizontalMargin = (screenWidth * 0.1).toInt()

            // Calcula el margen del 15% de la pantalla en la parte superior e inferior
            val verticalMargin = (screenHeight * 0.15).toInt()

            // Establece las restricciones y los márgenes
            lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            lp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            lp.marginStart = horizontalMargin
            lp.marginEnd = horizontalMargin
            lp.topMargin = verticalMargin
            lp.bottomMargin = verticalMargin

            videoView.layoutParams = lp
        }
    }
    // Función para obtener la altura de la barra de estado
    fun getStatusBarHeight(): Int {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
}