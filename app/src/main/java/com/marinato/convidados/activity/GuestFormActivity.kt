package com.marinato.convidados.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
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

    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.buttonSave.setOnClickListener (this)
        binding.radioPresent.isChecked = true

        observe()
        loadData()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_save){
            val name = binding.editName.text.toString()
            val presence = binding.radioPresent.isChecked

            val model = GuestModel(guestId, name, presence)
            viewModel.save(model)

            finish()
        }
    }

    private fun observe(){
        viewModel.guest.observe(this, Observer{
            binding.editName.setText(it.name)
            if (it.presence){
                binding.radioPresent.isChecked = true
            } else {
                binding.radioAbsent.isChecked = true
            }
        })

        viewModel.saveGuest.observe(this, Observer {
            if (it != ""){
                Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            guestId = bundle.getInt(Constants.GUEST.ID)
            viewModel.get(guestId )
        }
    }
}