package com.marinato.convidados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marinato.convidados.model.GuestModel

class AllGuestsViewModel : ViewModel() {

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests
}