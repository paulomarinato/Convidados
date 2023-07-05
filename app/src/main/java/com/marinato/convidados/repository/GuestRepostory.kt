package com.marinato.convidados.repository

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

    fun insert() {

    }
    fun update() {

    }
}