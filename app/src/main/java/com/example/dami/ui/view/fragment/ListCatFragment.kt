package com.example.dami.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dami.R
import com.example.dami.databinding.FragmentListCatBinding
import com.example.dami.entity.Resultado
import com.example.dami.ui.view.adapter.CateAdapter
import com.example.dami.ui.viewmodel.CategoriaViewModel
import com.example.dami.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ListCatFragment : Fragment() {
    private var _binding: FragmentListCatBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoriaViewModel by activityViewModels {
        ViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListCatBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.listarCategorias()
        viewModel.listaCategoria.observe(viewLifecycleOwner){ resultado ->
            when(resultado){
                is Resultado.Exito ->{
                    val lista = resultado.data
                    binding.rvCategoria.layoutManager = LinearLayoutManager(requireContext())
                    val metric = resources.displayMetrics
                    val margin = (8f * metric.density).toInt()
                    val layoutParams = binding.rvCategoria.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.setMargins(margin, margin, margin, margin)
                    binding.rvCategoria.layoutParams = layoutParams
                    binding.rvCategoria.adapter = CateAdapter(lista){categoria ->
                        val accion= ListCatFragmentDirections.actionListCatFragmentToDetCatFragment(categoria)
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
            val accion= ListCatFragmentDirections.actionListCatFragmentToDetCatFragment(null)
            findNavController().navigate(accion)
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}