package com.criandoapps.convidados.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.criandoapps.convidados.Model.GuestModel
import com.criandoapps.convidados.Repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = GuestRepository.getInstance(application)

    fun insert (guest: GuestModel){
        repository.insert(guest)
    }

}