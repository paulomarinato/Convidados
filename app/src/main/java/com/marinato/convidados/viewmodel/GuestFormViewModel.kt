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

    private val _saveGuest = MutableLiveData<String>()
    val saveGuest: LiveData<String> = _saveGuest

    fun save(guest: GuestModel) {
        if (guest.id == 0) {
            if (repository.insert(guest)) {
                _saveGuest.value = "Adicionado com Sucesso"
            } else {
                _saveGuest.value = ""
            }
        } else {
            if (repository.update(guest)) {
                _saveGuest.value = "Atualizado com Sucesso"
            } else {
                _saveGuest.value = ""
            }
        }
    }

    fun get(id: Int) {
        guestModel.value = repository.get(id)
    }
}