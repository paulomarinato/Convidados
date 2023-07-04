package com.marinato.convidados.viewmodel

import androidx.lifecycle.ViewModel
import com.marinato.convidados.repository.GuestRepostory

class GuestFormViewModel : ViewModel () {

    private var repository = GuestRepostory.getInstance()

    fun rep(){
    }
}