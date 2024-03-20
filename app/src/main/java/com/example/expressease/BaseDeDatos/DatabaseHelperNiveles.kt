package com.example.expressease.BaseDeDatos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelperNiveles (private val context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION ) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "NivelesDB.db"

        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_COMPLETADO = "completado"
        const val COLUMN_RESPUESTA_CORRECTA = "respuesta_correcta"

        //Tabla Nivel 1
        const val TABLE_NIVEL1 = "nivel_1"
        const val COLUMN_IDNIVEL1 = "_id"
        //Tabla Nivel 2
        const val TABLE_NIVELES = "niveles"
        const val COLUMN_ID = "_id"
        //Tabla Nivel 3
        const val TABLE_NIVELES3 = "nivel_3"
        const val ID_TABLA3 = "_id"
        //Tabla Nivel 4
        const val TABLE_NIVEL4 = "nivel_4"
        const val ID_TABLA4 = "_id"
        //Tabla Nivel 5
        const val TABLE_NIVEL5 = "nivel_5"
        const val ID_TABLA5 = "_id"


    }
    override fun onCreate(db: SQLiteDatabase?) {
        createNivelesTable(db, TABLE_NIVEL1, COLUMN_IDNIVEL1)
        createNivelesTable(db, TABLE_NIVELES, COLUMN_ID)
        createNivelesTable(db, TABLE_NIVELES3, ID_TABLA3)
        createNivelesTable(db, TABLE_NIVEL4, ID_TABLA4)
        createNivelesTable(db, TABLE_NIVEL5, ID_TABLA5)
    }

    private fun createNivelesTable(db: SQLiteDatabase?, tableName: String, idColumn: String) {
        val createTableSQL = "CREATE TABLE $tableName (" +
                "$idColumn INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_COMPLETADO INTEGER DEFAULT 0, " +
                "$COLUMN_RESPUESTA_CORRECTA INTEGER DEFAULT 0 )"

        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgradeExitisTablas(db,TABLE_NIVEL1)
        onUpgradeExitisTablas(db, TABLE_NIVELES)
        onUpgradeExitisTablas(db, TABLE_NIVELES3)
        onUpgradeExitisTablas(db,TABLE_NIVEL4)
        onUpgradeExitisTablas(db,TABLE_NIVEL5)
    }

    private fun onUpgradeExitisTablas(db: SQLiteDatabase?,tableName: String ){
        db?.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }

    //Metodo para insertar Datos en las tablas
    fun insertOrUpdateNivelResult(tableName: String, nivelId: Int, completado: Int, respuestaCorrecta:Int, textenviado: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_COMPLETADO, completado)
        values.put(COLUMN_RESPUESTA_CORRECTA, respuestaCorrecta)
        values.put(COLUMN_NOMBRE, textenviado)

        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(nivelId.toString())

        // Intenta actualizar el registro, si no existe, lo inserta.
        val rowsAffected = db.update(tableName, values, selection, selectionArgs)
        if (rowsAffected == 0) {
            db.insert(tableName, null, values)
        }

        db.close()
    }


}
