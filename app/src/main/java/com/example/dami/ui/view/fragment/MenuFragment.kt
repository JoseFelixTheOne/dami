package com.example.dami.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dami.R
import com.example.dami.databinding.FragmentMainBinding
import com.example.dami.databinding.FragmentMenuBinding
import com.example.dami.entity.Resultado
import com.example.dami.ui.view.adapter.CateAdapter
import com.example.dami.ui.viewmodel.CategoriaViewModel
import com.example.dami.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMenuBinding.inflate(inflater, container, false)



        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.btnProductos.setOnClickListener {

            val accion= MenuFragmentDirections.actionMenuFragmentToMainFragment()
            findNavController().navigate(accion)
        }

        binding.imgProducto.setOnClickListener {
            val accion= MenuFragmentDirections.actionMenuFragmentToMainFragment()
            findNavController().navigate(accion)
        }

        binding.layoutProducto.setOnClickListener {
            val accion= MenuFragmentDirections.actionMenuFragmentToMainFragment()
            findNavController().navigate(accion)
        }
        binding.btnCategoria.setOnClickListener {

            val accion= MenuFragmentDirections.actionMenuFragmentToListCatFragment2()
            findNavController().navigate(accion)
        }

        binding.imgCates.setOnClickListener {
            val accion= MenuFragmentDirections.actionMenuFragmentToListCatFragment2()
            findNavController().navigate(accion)
        }

        binding.layoutCategorias.setOnClickListener {
            val accion= MenuFragmentDirections.actionMenuFragmentToListCatFragment2()
            findNavController().navigate(accion)
        }

        // PERSONA -------------------------------
        binding.btnPersona.setOnClickListener {

            val accion= MenuFragmentDirections.actionMenuFragmentToListPersoFragment()
            findNavController().navigate(accion)
        }

        binding.imgPerso.setOnClickListener {
            val accion= MenuFragmentDirections.actionMenuFragmentToListPersoFragment()
            findNavController().navigate(accion)
        }

        binding.layoutPersonas.setOnClickListener {
            val accion= MenuFragmentDirections.actionMenuFragmentToListPersoFragment()
            findNavController().navigate(accion)
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}