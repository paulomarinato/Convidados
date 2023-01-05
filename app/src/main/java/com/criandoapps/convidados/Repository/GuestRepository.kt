package com.criandoapps.convidados.Repository

class GuestRepository private constructor(){

    companion object{

        private lateinit var repository: GuestRepository

        fun getInstance(): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository()
            }
            return repository
        }
    }
    fun save(){
        
    }
}