package com.example.dami.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.dami.R
import com.example.dami.databinding.FragmentDetProductoBinding
import com.example.dami.entity.Producto
import com.example.dami.entity.Resultado
import com.example.dami.ui.viewmodel.ProductoViewModel
import com.example.dami.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class DetProductoFragment : Fragment() {

    private var _binding : FragmentDetProductoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductoViewModel by activityViewModels {
        ViewModelFactory()
    }

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
        val productoR: Producto? = args.producto
        val idCategoria: Int = args.idCategoria
        println(productoR)
        if (productoR == null){


        }else{
            /*
            binding.lblNomProd.text = producto.nom_prod
            binding.lblPrecioProd.text = producto.prec_prod.toString()
            Glide.with(requireContext())
                .load(producto.imagen_prod)
                .centerCrop()
                .into(binding.imagenProd)

             */
        }

        binding.btnAgregar.setOnClickListener {
            val nombre = binding.txtNombre.editText?.text.toString()
            val precio = binding.txtPrecio.editText?.text.toString().toDouble()
            val stock = binding.txtStock.editText?.text.toString().toInt()

            if (nombre.isEmpty()) {
                binding.txtNombre.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            if (precio == null) {
                binding.txtPrecio.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            if (stock == null) {
                binding.txtStock.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            val producto = Producto(
                id_prod = 0,
                nom_prod = nombre,
                id_cat = idCategoria,
                imagen_prod = "https://firebasestorage.googleapis.com/v0/b/myproject-eb824.appspot.com/o/2.jpg?alt=media&token=996d52a9-cf98-48f4-959d-0c9f3f2ac78a&_gl=1*1w5iun9*_ga*MTIxNjU1ODE4Ni4xNjg1ODk1Mzk1*_ga_CW55HF8NVT*MTY4NTkxNTIyOC4zLjEuMTY4NTkxNTI3Mi4wLjAuMA..",
                prec_prod = precio,
                stock_prod = stock,
                activo_prod = 1 // Valor predeterminado
            )

            viewModel.agregarProducto(producto)
            println(producto)
            viewModel.agregarProductoResultado.observe(viewLifecycleOwner) { resultado ->
                when (resultado) {
                    is Resultado.Exito -> {
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle(resources.getString(R.string.confirmacion))
                            .setMessage("Resultado exitoso: ${resultado}")
                            .setPositiveButton(resources.getString(R.string.aceptar)) { _, _ ->
                                findNavController().popBackStack()
                            }
                            .setNegativeButton(resources.getString(R.string.cancelar), null)
                            .show()
                    }
                    is Resultado.Problema -> {
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Error")
                            .setMessage(resultado.error.mensaje)
                            .setPositiveButton("Aceptar", null)
                            .show()
                    }
                }
            }
        }
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}