package com.example.dami.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dami.R


import com.example.dami.databinding.FragmentListPersoBinding
import com.example.dami.entity.Resultado
import com.example.dami.ui.view.adapter.PersoAdapter


import com.example.dami.ui.viewmodel.PersonaViewModel
import com.example.dami.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ListPersoFragment : Fragment() {

    private var _binding: FragmentListPersoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PersonaViewModel by activityViewModels {
        ViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListPersoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.listarPersonas()
        viewModel.listaPersona.observe(viewLifecycleOwner){ resultado ->
            when(resultado){
                is Resultado.Exito ->{
                    val lista = resultado.data
                    binding.rvPersona.layoutManager = LinearLayoutManager(requireContext())
                    val metric = resources.displayMetrics
                    val margin = (8f * metric.density).toInt()
                    val layoutParams = binding.rvPersona.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.setMargins(margin, margin, margin, margin)
                    binding.rvPersona.layoutParams = layoutParams
                    binding.rvPersona.adapter = PersoAdapter(lista) { persona ->

                        val accion = ListPersoFragmentDirections.actionListPersoFragmentToDetPersoFragment(persona)
                        findNavController().navigate(accion)


                    }
                }
                is Resultado.Problema ->{
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Error")
                        .setMessage(resultado.error.mensaje)
                        .setPositiveButton("Aceptar", null)
                        .show()
                }
            }
        }
        binding.btnNuevo.setOnClickListener {
            val accion= ListPersoFragmentDirections.actionListPersoFragmentToDetPersoFragment(null)
            findNavController().navigate(accion)
        }

    }

}