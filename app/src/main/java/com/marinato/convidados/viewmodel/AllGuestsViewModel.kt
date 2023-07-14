package com.marinato.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marinato.convidados.model.GuestModel
import com.marinato.convidados.repository.GuestRepostory

class AllGuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GuestRepostory =
        GuestRepostory.getInstance(application.applicationContext)

    private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll(){
        listAllGuests.value = repository.getAll()
    }

    fun delete (id: Int){
        repository.delete(id)
    }

}