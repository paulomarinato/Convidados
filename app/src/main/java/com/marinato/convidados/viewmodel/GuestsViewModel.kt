package com.marinato.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marinato.convidados.model.GuestModel
import com.marinato.convidados.repository.GuestRepostory

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =  GuestRepostory.getInstance(application.applicationContext)

    private val _guestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = _guestList

    fun getAll() {
        _guestList.value = repository.getAll()
    }
    fun getAbsent() {
        _guestList.value = repository.getAbsent()
    }
    fun getPresent() {
        _guestList.value = repository.getPresent()
    }
    fun delete (id: Int){
        repository.delete(id)
    }

}
