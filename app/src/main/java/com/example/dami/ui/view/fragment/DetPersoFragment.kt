package com.example.dami.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.dami.R
import com.example.dami.databinding.FragmentDetCatBinding
import com.example.dami.databinding.FragmentDetPersoBinding
import com.example.dami.ui.viewmodel.CategoriaViewModel
import com.example.dami.ui.viewmodel.PersonaViewModel
import com.example.dami.ui.viewmodel.ViewModelFactory


class DetPersoFragment : Fragment() {

    private var _binding: FragmentDetPersoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PersonaViewModel by activityViewModels {
        ViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetPersoBinding.inflate(inflater, container, false)
        return binding.root
    }



}