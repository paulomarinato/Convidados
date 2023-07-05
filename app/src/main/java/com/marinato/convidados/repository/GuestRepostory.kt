package com.marinato.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.marinato.convidados.DataBase.GuestDataBase
import com.marinato.convidados.model.GuestModel

class GuestRepostory private constructor(context: Context) {

    private val guestDataBase = GuestDataBase(context)


    companion object{
        private lateinit var repostory: GuestRepostory

        fun getInstance(context: Context): GuestRepostory {
            if (!Companion::repostory.isInitialized){
                repostory = GuestRepostory(context)
            }
            return repostory
        }
    }

    fun insert(guest: GuestModel) {
        val db =  guestDataBase.writableDatabase

        val presence = if (guest.presence) 1  else 0

        val values = ContentValues()
        values.put("name", guest.name)
        values.put("presence", presence)

        db.insert("Guest", null, values)

    }
    fun update() {

    }
}