package com.marinato.convidados.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.marinato.convidados.databinding.FragmentPresentBinding
import com.marinato.convidados.viewmodel.PresentViewModel

class PresentFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ViewModel =
            ViewModelProvider(this)[PresentViewModel::class.java]

        _binding = FragmentPresentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGalery
        ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}