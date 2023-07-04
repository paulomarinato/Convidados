package com.marinato.convidados.DataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class GuestDataBase( context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object{
        private const val NAME = "guestDB"
        private const val VERSION = 1
    }
 //criando banco de dados
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table Guest (id integer primary key autoincrement, " +
                "name text, presence integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}