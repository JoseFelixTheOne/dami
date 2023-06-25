package com.example.dami.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dami.R
import com.example.dami.databinding.FragmentDetCatBinding
import com.example.dami.entity.Categoria
import com.example.dami.entity.Producto
import com.example.dami.entity.Resultado
import com.example.dami.ui.viewmodel.CategoriaViewModel
import com.example.dami.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DetCatFragment : Fragment() {

    private var _binding: FragmentDetCatBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoriaViewModel by activityViewModels {
        ViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetCatBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetCatFragmentArgs by navArgs()
        var _categoriaR: Categoria? = args.categoria
        var categoriaR: Categoria = Categoria(0, "", "",0, "")

        if (_categoriaR == null){
            binding.btnAgregar.visibility = View.VISIBLE
            binding.btnActualizar.visibility = View.GONE
            binding.btnEliminar.visibility = View.GONE

        }else{
            categoriaR = _categoriaR!!
            binding.btnAgregar.visibility = View.GONE
            binding.btnActualizar.visibility = View.VISIBLE
            binding.btnEliminar.visibility = View.VISIBLE
            binding.txtNombre.editText?.setText(categoriaR.nom_cat)
            binding.txtDescripcion.editText?.setText(categoriaR.desc_cat)
        }

        binding.btnAgregar.setOnClickListener {
            val nombre = binding.txtNombre.editText?.text.toString()
            val descripcion = binding.txtDescripcion.editText?.text.toString()

            if (nombre.isEmpty()) {
                binding.txtNombre.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }
            if (descripcion.isEmpty()) {
                binding.txtDescripcion.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            val categoria = Categoria(
                id_cat = 0,
                nom_cat = nombre,
                desc_cat = descripcion,
                activo_Cat = 1,
                imagen_cat = "https://firebasestorage.googleapis.com/v0/b/myproject-eb824.appspot.com/o/Televisor.jpg?alt=media&token=0e1caece-6fd7-42ad-83fa-1f7e453e2fff&_gl=1*898uyb*_ga*MTIxNjU1ODE4Ni4xNjg1ODk1Mzk1*_ga_CW55HF8NVT*MTY4NTkxOTI0OS40LjEuMTY4NTkxOTM2NC4wLjAuMA.."
            )

            viewModel.agregarCategoria(categoria)
            viewModel.cambiarCategoriaResultado.observe(viewLifecycleOwner){resultado ->
                when (resultado) {
                    is Resultado.Exito -> {
                        findNavController().popBackStack()
                        mensaje_exito(" Ingreso $resultado")
                    }
                    is Resultado.Problema -> {
                        mensaje_error(resultado.error.mensaje)
                    }
                }
            }
        }
        binding.btnActualizar.setOnClickListener {
            val nombre = binding.txtNombre.editText?.text.toString()
            val descripcion = binding.txtDescripcion.editText?.text.toString()

            if (nombre.isEmpty()) {
                binding.txtNombre.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }
            if (descripcion.isEmpty()) {
                binding.txtDescripcion.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }
            val categoria = Categoria(
                id_cat = categoriaR.id_cat,
                nom_cat = nombre,
                desc_cat = descripcion,
                activo_Cat = categoriaR.activo_Cat,
                imagen_cat = categoriaR.imagen_cat
            )
            viewModel.agregarCategoria(categoria)
            viewModel.cambiarCategoriaResultado.observe(viewLifecycleOwner){resultado ->
                when (resultado) {
                    is Resultado.Exito -> {
                        findNavController().popBackStack()
                        mensaje_exito(" Actualizo $resultado")
                    }
                    is Resultado.Problema -> {
                        mensaje_error(resultado.error.mensaje)
                    }
                }
            }
        }
        binding.btnEliminar.setOnClickListener {
            viewModel.eliminarCategoria(categoriaR.id_cat)
            var dialogShown = false
            viewModel.cambiarCategoriaResultado.observe(viewLifecycleOwner){resultado ->
                if (!dialogShown) {
                    dialogShown = true
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(resources.getString(R.string.confirmacion))
                        .setMessage("¿Está seguro de eliminar la categoria: ${categoriaR.nom_cat} ?")
                        .setPositiveButton(resources.getString(R.string.aceptar)) { _, _ ->
                            when (resultado) {
                                is Resultado.Exito -> {
                                    findNavController().popBackStack()
                                }
                                is Resultado.Problema -> {
                                    mensaje_error(resultado.error.mensaje)
                                }
                            }
                        }
                        .setNegativeButton(resources.getString(R.string.cancelar), null)
                        .show()
                }
            }
        }
    }
    fun mensaje_exito(mensaje: String){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.confirmacion))
            .setMessage("Resultado exitoso: ${mensaje}")
            .setPositiveButton(resources.getString(R.string.aceptar), null)
            //.setNegativeButton(resources.getString(R.string.cancelar), null)
            .show()
    }
    fun mensaje_error(mensaje: String){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Error")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}