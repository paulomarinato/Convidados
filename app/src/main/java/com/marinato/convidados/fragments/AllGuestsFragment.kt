package com.marinato.convidados.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.marinato.convidados.activity.GuestFormActivity
import com.marinato.convidados.databinding.FragmentPresentBinding
import com.marinato.convidados.adapatadores.adapter.GuestsAdapter
import com.marinato.convidados.adapatadores.listener.OnGuestListener
import com.marinato.convidados.dataBase.Constants
import com.marinato.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestsViewModel
    private val adapter = GuestsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        viewModel = ViewModelProvider(this)[AllGuestsViewModel::class.java]
        _binding = FragmentPresentBinding.inflate(inflater, container, false)

        //Layout
        binding.recyclerPresent.layoutManager = LinearLayoutManager(context)

        //adapter
        binding.recyclerPresent.adapter = GuestsAdapter()

        val listener = object : OnGuestListener{
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(Constants.GUEST.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }

        }

        adapter.attchlistener(listener)
        
        observe()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updatedGuests(it)
        }
    }
}