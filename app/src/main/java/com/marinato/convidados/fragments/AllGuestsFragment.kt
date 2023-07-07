package com.marinato.convidados.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.marinato.convidados.databinding.FragmentPresentBinding
import com.marinato.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AllGuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AllGuestsViewModel::class.java]

        _binding = FragmentPresentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        observe()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe(){
        viewModel.guests.observe(viewLifecycleOwner){
        }
    }
}