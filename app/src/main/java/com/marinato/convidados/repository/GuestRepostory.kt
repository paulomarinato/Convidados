package com.marinato.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.marinato.convidados.DataBase.Constants
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

    fun insert(guest: GuestModel): Boolean {
        return try {
            val db =  guestDataBase.writableDatabase
            val presence = if (guest.presence) 1  else 0

            val values = ContentValues()
            values.put(Constants.GUEST.COLUMNS.NAME, guest.name)
            values.put(Constants.GUEST.COLUMNS.PRESENCE, presence)

            db.insert(Constants.GUEST.TABLE_NAME , null, values)
            true
        }catch (e: Exception){
            false
        }
    }
    fun update(guest: GuestModel): Boolean {
        return try {
            val db = guestDataBase.writableDatabase
            val presence = if (guest.presence) 1  else 0

            val values = ContentValues()
            values.put(Constants.GUEST.COLUMNS.NAME, guest.name)
            values.put(Constants.GUEST.COLUMNS.PRESENCE, presence)


            val selections = Constants.GUEST.COLUMNS.ID + "= ?"
            val args = arrayOf(guest.id.toString())

            db.update(Constants.GUEST.TABLE_NAME, values, selections, args)
            true
        }catch (e: Exception){
            false
        }

    }
}