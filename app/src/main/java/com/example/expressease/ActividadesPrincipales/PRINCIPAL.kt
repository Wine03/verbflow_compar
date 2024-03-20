package com.example.expressease.ActividadesPrincipales

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.example.expressease.BaseDeDatos.CacheLimpiar
import com.example.expressease.BaseDeDatos.DatabaseHelper
import com.example.expressease.Nivel1.Nivel1
import com.example.expressease.R
import android.util.Log


class PRINCIPAL : AppCompatActivity() {
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginBoton: Button
    private lateinit var regisBoton: Button
    private lateinit var db: DatabaseHelper

    private lateinit var swGuardarSesion: Switch

    private lateinit var cacheManager: CacheLimpiar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_principal)
        initcomponent()
        cacheManager = CacheLimpiar()
        db= DatabaseHelper(this)
        if (!db.isUsernameExists("admin")) {
            // Si no existe, insertarlo
            val hast = db.hashPassword("1234")
            db.insertUser("admin", hast)
        }
        password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD


        loginBoton.setOnClickListener {

            val loginUsername= username.text.toString()
            val loginUserPass= password.text.toString()
            loginDatabase(loginUsername,loginUserPass)
            username.text.clear()
            password.text.clear()
        }

        regisBoton.setOnClickListener {
            val intent = Intent(this, Registar::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initcomponent(){
        username=findViewById(R.id.Edit_USER)
        password=findViewById(R.id.Edit_PASS)
        loginBoton=findViewById(R.id.button2)
        regisBoton=findViewById(R.id.btnRegistrar)
   //     swGuardarSesion=findViewById(R.id.swGuardarSesion)

    }

    private fun loginDatabase(username: String, password: String) {
        // Obtener el hash de la contraseña ingresada por el usuario
        val hashedPassword = db.hashPassword(password)

        // Buscar en la base de datos el usuario con el nombre de usuario proporcionado
        val userExists = db.readUser(username, hashedPassword) // Compara con la contraseña hasheada
        Log.d("PRINCIPAL", "Iniciando verificación de inicio de sesión para usuario: $username")

        Log.d("PRINCIPAL", "loginDatabase - userExists: $userExists")

        if (userExists) {
            Log.d("PRINCIPAL", "Inicio de sesión exitoso para usuario: $username")

            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
            cacheManager.onUserChange()
            val intent = Intent(this, Navegacion::class.java)
            startActivity(intent)
            finish()
        } else {
            Log.d("PRINCIPAL", "Inicio de sesión fallido para usuario: $username")
            Toast.makeText(this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show()
        }
    }



}
