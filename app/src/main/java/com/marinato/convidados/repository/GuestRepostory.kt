package com.marinato.convidados.repository

import com.marinato.convidados.DataBase.GuestDataBase

class GuestRepostory private constructor() {


    companion object{
        private lateinit var repostory: GuestRepostory

        fun getInstance(): GuestRepostory {
            if (!Companion::repostory.isInitialized){
                repostory = GuestRepostory()
            }
            return repostory
        }
    }

    fun save() {
        GuestDataBase(null)
    }
}