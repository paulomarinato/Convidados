package com.marinato.convidados.dataBase

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
        db.execSQL("create table" +
                Constants.GUEST.TABLE_NAME + "(" +
                Constants.GUEST.COLUMNS.ID + "integer primary key autoincrement, " +
                Constants.GUEST.COLUMNS.NAME + "text, " +
                Constants.GUEST.COLUMNS.PRESENCE + "integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}