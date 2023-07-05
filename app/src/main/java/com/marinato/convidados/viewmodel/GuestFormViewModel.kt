package com.marinato.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.marinato.convidados.model.GuestModel
import com.marinato.convidados.repository.GuestRepostory

class GuestFormViewModel(application: Application) : AndroidViewModel (application) {

    private var repository = GuestRepostory.getInstance(application)

    fun insert(guest: GuestModel){
        repository.insert(guest)

    }
}