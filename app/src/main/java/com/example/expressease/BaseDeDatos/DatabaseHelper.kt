package com.example.expressease.BaseDeDatos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File
import java.security.MessageDigest

class DatabaseHelper (private val context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_version ) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"
        private const val DATABASE_version = 1
        private const val TABLE_NAME = "data"
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME(" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "$COLUMN_USERNAME TEXT, " +
                "$COLUMN_PASSWORD TEXT)" )
        db?.execSQL(createTableQuery)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTablaQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTablaQuery)
        onCreate(db)
    }

    fun insertUser(username: String, password: String, db: SQLiteDatabase? = null): Long{
        val values = ContentValues().apply{
            put(COLUMN_USERNAME, username)
            put(COLUMN_PASSWORD,password)
        }
        val writableDb = db ?: this.writableDatabase
        return writableDb.insert(TABLE_NAME, null, values)
    }


    fun readUser(username: String, password: String): Boolean{
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username,password)
        val cursor = db.query(TABLE_NAME, null, selection,selectionArgs,null,null,null)

        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }

    fun isUsernameExists(username: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_NAME,
            arrayOf(COLUMN_ID),
            "$COLUMN_USERNAME = ?",
            arrayOf(username),
            null,
            null,
            null
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }


    fun logoutUser() {
        val db = this.writableDatabase

        // Supongamos que tu tabla de usuarios se llama "usuarios"
        db.delete(TABLE_NAME, null, null)

        db.close()
    }

    fun hashPassword(password: String): String {
        // Get the hash function
        val sha256 = MessageDigest.getInstance("SHA-256")

        // Hash the password
        val hashedPassword = sha256.digest(password.toByteArray())

        // Convert the hash to a string
        val hashedPasswordString = hashedPassword.joinToString("") { it.toString() }

        // Return the hash
        return hashedPasswordString
    }

    fun backupDatabase(context: Context) {
        val currentDBPath = context.getDatabasePath(DATABASE_NAME).path
        val backupDBPath = File(context.getExternalFilesDir(null), DATABASE_NAME).path

        try {
            File(currentDBPath).copyTo(File(backupDBPath), true)
            // Tu base de datos se ha copiado con éxito.
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle error.
        }
    }

    fun restoreDatabase(context: Context) {
        val currentDBPath = context.getDatabasePath(DATABASE_NAME).path
        val backupDBPath = File(context.getExternalFilesDir(null), DATABASE_NAME).path

        try {
            File(backupDBPath).copyTo(File(currentDBPath), true)
            // Tu base de datos se ha restaurado con éxito.
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle error.
        }
    }
}