package com.example.expressease

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.expressease.ActividadesPrincipales.Navegacion
import com.example.expressease.ActividadesPrincipales.PRINCIPAL
import com.example.expressease.BaseDeDatos.DatabaseHelperNiveles
import com.example.expressease.Crucigrama.SopadeLetras
import com.example.expressease.Nivel1.Nivel1
import com.example.expressease.Nivel1.Pregunta1Nivel1
import com.example.expressease.Nivel2.Nivel2_Principal
import com.example.expressease.Nivel2.Pregunta1
import com.example.expressease.Nivel3.Nivel3General
import com.example.expressease.Nivel3.Pregunta1Nivel3
import com.example.expressease.Nivel3.Pregunta3Nivel3
import com.example.expressease.Progreso.Progreso
import com.example.expressease.Progreso.ProgresoNivel1
import com.example.expressease.Progreso.ProgresoNivel3
import com.example.expressease.videos.Videos
import com.google.android.material.navigation.NavigationView

class NivelGeneral : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var dbHelper: DatabaseHelperNiveles
    private lateinit var drawer: DrawerLayout
    private lateinit var toogle: ActionBarDrawerToggle
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nivel_general)

        dbHelper = DatabaseHelperNiveles(this)

        val btnNivel1 = findViewById<ImageButton>(R.id.btnNivel_1)
        btnNivel1.setOnClickListener {
            if (!nivelCompletado(1)) {
                val intent = Intent(this, Pregunta1Nivel1::class.java)
                startActivity(intent)
            } else
            {
                val intent = Intent(this, Pregunta1Nivel1::class.java)
                startActivity(intent)
            }
            finish()
        }

        val btnNivel2 = findViewById<ImageButton>(R.id.btnNivel2)
        btnNivel2.setOnClickListener {
            if (!nivelCompletado(1)) {
                val intent = Intent(this, Pregunta1::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this, Pregunta1::class.java)
                startActivity(intent)
            }
            finish()
        }

        val btnNivel3 = findViewById<ImageButton>(R.id.btnNivel3)
        btnNivel3.setOnClickListener {
            if (!nivelCompletado(1)) {
                val intent = Intent(this, Pregunta1Nivel3::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, Pregunta1Nivel3::class.java)
                startActivity(intent)
            }
            finish()
        }

        val btnCrucigrama=findViewById<ImageButton>(R.id.btnNivel_4)
        btnCrucigrama.setOnClickListener{
            val intent = Intent(this, SopadeLetras::class.java)
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
    private fun nivelCompletado(nivelId: Int): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT ${DatabaseHelperNiveles.COLUMN_COMPLETADO} FROM ${DatabaseHelperNiveles.TABLE_NIVELES} WHERE ${DatabaseHelperNiveles.COLUMN_ID} = $nivelId", null)
        val nivelCompletado = cursor.moveToFirst() && cursor.getInt(0) == 1
        cursor.close()
        return nivelCompletado
    }
}