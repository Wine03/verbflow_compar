package com.example.expressease.ActividadesPrincipales

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.expressease.BaseDeDatos.DatabaseHelper
import com.example.expressease.R
import org.mindrot.jbcrypt.BCrypt

class Registar : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var loginBoton: Button
    private lateinit var regisBoton: Button
    private lateinit var db: DatabaseHelper


    private fun hashPassword(password: String): String {
        val salt = BCrypt.gensalt() // Genera un salt aleatorio
        return BCrypt.hashpw(password, salt)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registar2)
        initcomponent()

        db = DatabaseHelper(this)

        regisBoton.setOnClickListener {
            val name1=username.text.toString()
            val pass1=password.text.toString()
            signupDatabase(name1,pass1)
        }

        loginBoton.setOnClickListener {
            val intent = Intent(this, PRINCIPAL::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initcomponent(){
        username=findViewById(R.id.Edit_USER1)
        password=findViewById(R.id.Edit_PASS1)
        loginBoton=findViewById(R.id.btnRegresarRegis)
        regisBoton=findViewById(R.id.BTNREGISTRAR)
    }

    private fun signupDatabase(name: String, pass: String) {
        val dbHelper = DatabaseHelper(this)

        // Verificar si el nombre de usuario ya existe en la base de datos
        if (dbHelper.isUsernameExists(name)) {
            Toast.makeText(this, "El nombre de usuario ya existe", Toast.LENGTH_SHORT).show()
            return
        }

        // Hashear la contraseña antes de insertarla en la base de datos
        val hashedPassword = dbHelper.hashPassword(pass)

        // Si el nombre de usuario no existe, proceder con el registro
        val insertedRowId = dbHelper.insertUser(name, hashedPassword)
        if (insertedRowId != -1L) {
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, PRINCIPAL::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Registro Fallido", Toast.LENGTH_SHORT).show()
        }
    }


}