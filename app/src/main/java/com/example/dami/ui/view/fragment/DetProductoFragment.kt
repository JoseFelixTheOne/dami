package com.example.dami.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.dami.R
import com.example.dami.databinding.FragmentDetProductoBinding
import com.example.dami.entity.Producto


class DetProductoFragment : Fragment() {

    private var _binding : FragmentDetProductoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetProductoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetProductoFragmentArgs by navArgs()
        val producto: Producto = args.producto
        println(producto)
        if (producto == null){
            findNavController().popBackStack()
            println("ERROR con el producto seleccionado")
        }else{
            binding.lblNomProd.text = producto.nom_prod
            binding.lblPrecioProd.text = producto.prec_prod.toString()
            Glide.with(requireContext())
                .load(producto.imagen_prod)
                .centerCrop()
                .into(binding.imagenProd)
        }
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}