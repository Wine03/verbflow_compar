package com.example.expressease.videos

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.expressease.ActividadesPrincipales.Navegacion
import com.example.expressease.ActividadesPrincipales.PRINCIPAL
import com.example.expressease.Nivel1.Pregunta2Nivel1
import com.example.expressease.NivelGeneral
import com.example.expressease.Progreso.Progreso
import com.example.expressease.Progreso.ProgresoNivel1
import com.example.expressease.Progreso.ProgresoNivel3
import com.example.expressease.R
import com.google.android.material.navigation.NavigationView

class Videos : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout

    private lateinit var toogle: ActionBarDrawerToggle
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)

        val btnpresentacion= findViewById<Button>(R.id.btnPresentacion)
        btnpresentacion.setOnClickListener {
            val intent = Intent(this, video_presentacion::class.java)
            startActivity(intent)

        }
        val button= findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, video_objetivos::class.java)
            startActivity(intent)

        }

        val btnaudiencia= findViewById<Button>(R.id.btnaudiencia)
        btnaudiencia.setOnClickListener {
            val intent = Intent(this, video_audiencia::class.java)
            startActivity(intent)

        }

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toogle = ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toogle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener (this)
        toogle.syncState()
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toogle.syncState()
        finish()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toogle.onConfigurationChanged(newConfig)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)){
            return  true
        }
        return super.onOptionsItemSelected(item)
        finish()
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


