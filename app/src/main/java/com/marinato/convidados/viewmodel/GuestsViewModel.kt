package com.marinato.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.marinato.convidados.repository.GuestRepostory

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GuestRepostory =
        GuestRepostory.getInstance(application.applicationContext)

    /*private val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests*/

    fun getAll(){
        listAllGuests.value = repository.getAll()
    }

    fun delete (id: Int){
        repository.delete(id)
    }

}