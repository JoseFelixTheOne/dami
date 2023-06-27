package com.example.dami.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.dami.R
import com.example.dami.databinding.FragmentDetCatBinding
import com.example.dami.databinding.FragmentDetPersoBinding
import com.example.dami.entity.Categoria
import com.example.dami.entity.Persona
import com.example.dami.entity.Resultado
import com.example.dami.ui.viewmodel.CategoriaViewModel
import com.example.dami.ui.viewmodel.PersonaViewModel
import com.example.dami.ui.viewmodel.ViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder


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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        val args: DetPersoFragmentArgs by navArgs()
        var _personaR: Persona? = args.persona
        var personaR: Persona = Persona(0, "", "","", "","","")

        if (_personaR == null){
            binding.btnAgregar.visibility = View.VISIBLE
            binding.btnActualizar.visibility = View.GONE
            binding.btnEliminar.visibility = View.GONE

        }else{
            personaR = _personaR!!
            binding.btnAgregar.visibility = View.GONE
            binding.btnActualizar.visibility = View.VISIBLE
            binding.btnEliminar.visibility = View.VISIBLE
            binding.txtNombre.editText?.setText(personaR.nom_per)
            binding.txtApelldioPaterno.editText?.setText(personaR.apePat_per)
            binding.txtApelldioMaterno.editText?.setText(personaR.apeMat_per)
            binding.txtCorreo.editText?.setText(personaR.correo_per)
            binding.txtDirecPersona.editText?.setText(personaR.dir_per)
            binding.txtflagClientePer.editText?.setText(personaR.flagCliente_per)
        }

        binding.btnAgregar.setOnClickListener {
            val nombre = binding.txtNombre.editText?.text.toString()
            val apepaterno = binding.txtApelldioPaterno.editText?.text.toString()
            val apematerno = binding.txtApelldioMaterno.editText?.text.toString()
            val correo = binding.txtCorreo.editText?.text.toString()
            val dirrecion = binding.txtDirecPersona.editText?.text.toString()
            val flag = binding.txtflagClientePer.editText?.text.toString()

            if (nombre.isEmpty()) {
                binding.txtNombre.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }
            if (apepaterno.isEmpty()) {
                binding.txtApelldioPaterno.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            if (apematerno.isEmpty()) {
                binding.txtApelldioMaterno.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            if (correo.isEmpty()) {
                binding.txtCorreo.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            if (dirrecion.isEmpty()) {
                binding.txtDirecPersona.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }
            if (flag.isEmpty()) {
                binding.txtflagClientePer.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            val persona = Persona(
                id_per = 0,
                nom_per = nombre,
                apePat_per = apepaterno,
                apeMat_per = apematerno,
                correo_per = correo,
                dir_per =dirrecion,
                flagCliente_per = flag
            )

            viewModel.agregarPesona(persona)
            viewModel.cambiarPersonaResultado.observe(viewLifecycleOwner){resultado ->
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
            val apepaterno = binding.txtApelldioPaterno.editText?.text.toString()
            val apematerno = binding.txtApelldioMaterno.editText?.text.toString()
            val correo = binding.txtCorreo.editText?.text.toString()
            val dirrecion = binding.txtDirecPersona.editText?.text.toString()
            val flag = binding.txtflagClientePer.editText?.text.toString()

            if (nombre.isEmpty()) {
                binding.txtNombre.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }
            if (apepaterno.isEmpty()) {
                binding.txtApelldioPaterno.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            if (apematerno.isEmpty()) {
                binding.txtApelldioMaterno.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            if (correo.isEmpty()) {
                binding.txtCorreo.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            if (dirrecion.isEmpty()) {
                binding.txtDirecPersona.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }
            if (flag.isEmpty()) {
                binding.txtflagClientePer.error = resources.getString(R.string.campo_requerido)
                return@setOnClickListener
            }

            val persona = Persona(
                id_per = 0,
                nom_per = nombre,
                apePat_per = apepaterno,
                apeMat_per = apematerno,
                correo_per = correo,
                dir_per =dirrecion,
                flagCliente_per = flag
            )
            viewModel.agregarPesona(persona)
            viewModel.cambiarPersonaResultado.observe(viewLifecycleOwner){resultado ->
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
            viewModel.eliminarPersona(personaR.id_per)
            var dialogShown = false
            viewModel.cambiarPersonaResultado.observe(viewLifecycleOwner){resultado ->
                if (!dialogShown) {
                    dialogShown = true
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(resources.getString(R.string.confirmacion))
                        .setMessage("¿Está seguro de eliminar la persona: ${personaR.nom_per} ?")
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