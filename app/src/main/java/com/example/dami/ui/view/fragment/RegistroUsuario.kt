package com.example.dami.ui.view.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dami.databinding.FragmentRegistroUsuarioBinding
import com.google.firebase.auth.FirebaseAuth


class RegistroUsuario : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentRegistroUsuarioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistroUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding.registrar.setOnClickListener {
            val user = binding.regUsu.text.toString().trim()
            val pass = binding.regPass.text.toString().trim()
            if (user.isEmpty()) {
                binding.regUsu.error = "Email cannot be empty"
            }
            if (pass.isEmpty()) {
                binding.regPass.error = "Password cannot be empty"
            } else {
                auth.createUserWithEmailAndPassword(user, pass)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                requireContext(),
                                "SignUp Successful",
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {
                            Toast.makeText(
                                requireContext(),
                                "SignUp Failed" + task.exception?.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }

    }
}
