package com.marinato.convidados.repository

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
}