package com.marinato.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marinato.convidados.model.GuestModel
import com.marinato.convidados.repository.GuestRepostory

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private var repository = GuestRepostory.getInstance(application)

    private val guestModel = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = guestModel

    fun save(guest: GuestModel) {
        if (guest.id == 0){
            repository.insert(guest)
        } else{
            repository.update(guest)
        }
    }

    fun get(id: Int){
        guestModel.value = repository.get(id)
    }
}