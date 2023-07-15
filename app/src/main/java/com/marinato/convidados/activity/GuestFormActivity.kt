package com.marinato.convidados.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.marinato.convidados.viewmodel.GuestFormViewModel
import com.marinato.convidados.R
import com.marinato.convidados.dataBase.Constants
import com.marinato.convidados.databinding.ActivityGuestFormBinding
import com.marinato.convidados.model.GuestModel
import java.sql.DatabaseMetaData

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener (this)
        binding.radioPresent.isChecked = true

        /*
        if (binding.radioAbsent.isChecked) {
            binding.radioPresent.isChecked = false
        }
        */

        loadData()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save){
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(0, name, presence)
            viewModel.insert(model)

        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            val guestId = bundle.getInt(Constants.GUEST.ID)
            viewModel.get(guestId )
        }
    }
}