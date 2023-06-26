package com.example.dami.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns

import android.widget.Toast
import com.example.dami.databinding.ActivityLoguinBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class loguin : AppCompatActivity() {
    private lateinit var binding: ActivityLoguinBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoguinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //FirebaseApp.initializeApp(this) <-- Comenta esta línea

        auth = FirebaseAuth.getInstance()
        binding.ingresar.setOnClickListener {
            val email = binding.email.text.toString()
            val pass = binding.password.text.toString()

            if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (pass.isNotEmpty()) {
                    auth.signInWithEmailAndPassword(email, pass)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    binding.password.setError("Empty fields are not allowed")
                }
            } else if (email.isEmpty()) {
                binding.email.setError("Empty fields are not allowed")
            } else {
                binding.email.setError("Please enter correct email")
            }
        }
    }

}