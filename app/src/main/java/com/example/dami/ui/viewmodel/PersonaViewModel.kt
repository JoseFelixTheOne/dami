package com.example.dami.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dami.entity.Categoria
import com.example.dami.entity.Error
import com.example.dami.entity.Persona
import com.example.dami.entity.Resultado
import com.example.dami.repository.PersonaRepository
import kotlinx.coroutines.launch

class PersonaViewModel(private val repository: PersonaRepository): ViewModel() {
    private var _listaPersona = MutableLiveData<Resultado<List<Persona>>>()
    val listaPersona: LiveData<Resultado<List<Persona>>> = _listaPersona

    private val _cambiarPersonaResultado = MutableLiveData<Resultado<Int>>()
    val cambiarPersonaResultado: LiveData<Resultado<Int>> get() = _cambiarPersonaResultado


    fun listarPersonas(){
        viewModelScope.launch {
            val response = repository.listaPersona()
            _listaPersona.value = response
        }
    }

    fun agregarPesona(persona: Persona) {
        viewModelScope.launch {
            try {
                val resultado = repository.agregarPersona(persona)
                _cambiarPersonaResultado.value = resultado
            } catch (ex: Exception) {
                _cambiarPersonaResultado.value =
                    Resultado.Problema(Error("001", ex.message ?: "Unknown Error"))
            }
        }
    }

    fun eliminarPersona(id: Int) {
        viewModelScope.launch {
            try {
                val resultado = repository.eliminarPersona(id)
                _cambiarPersonaResultado.value = resultado
            } catch (ex: Exception) {
                _cambiarPersonaResultado.value =
                    Resultado.Problema(Error("001", ex.message ?: "Unknown Error"))
            }
        }
    }
}