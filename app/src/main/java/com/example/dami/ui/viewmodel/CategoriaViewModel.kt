package com.example.dami.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dami.entity.Categoria
import com.example.dami.entity.Resultado
import com.example.dami.entity.Error
import com.example.dami.repository.CategoriaRepository
import kotlinx.coroutines.launch

class CategoriaViewModel(private val repository: CategoriaRepository): ViewModel() {

    private var _listaCategoria = MutableLiveData<Resultado<List<Categoria>>>()
    val listaCategoria: LiveData<Resultado<List<Categoria>>> = _listaCategoria

    private val _cambiarCategoriaResultado = MutableLiveData<Resultado<Int>>()
    val cambiarCategoriaResultado: LiveData<Resultado<Int>> get() = _cambiarCategoriaResultado

    fun listarCategorias(){
        viewModelScope.launch {
            val response = repository.listaCategoria()
            _listaCategoria.value = response
        }
    }
    fun agregarCategoria(categoria: Categoria) {
        viewModelScope.launch {
            try {
                val resultado = repository.agregarCategoria(categoria)
                _cambiarCategoriaResultado.value = resultado
            } catch (ex: Exception) {
                _cambiarCategoriaResultado.value =
                    Resultado.Problema(Error("001", ex.message ?: "Unknown Error"))
            }
        }
    }

    fun eliminarCategoria(id: Int) {
        viewModelScope.launch {
            try {
                val resultado = repository.eliminarCategoria(id)
                _cambiarCategoriaResultado.value = resultado
            } catch (ex: Exception) {
                _cambiarCategoriaResultado.value =
                    Resultado.Problema(Error("001", ex.message ?: "Unknown Error"))
            }
        }
    }
}

