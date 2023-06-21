package com.example.dami.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dami.entity.Producto
import com.example.dami.entity.Resultado
import com.example.dami.entity.Error
import com.example.dami.repository.ProductoPorCateRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch

class ProductoViewModel(private val repository: ProductoPorCateRepository): ViewModel() {

    private var _listaProducto = MutableLiveData<Resultado<List<Producto>>>()
    val listaProducto: LiveData<Resultado<List<Producto>>> = _listaProducto

    private val _agregarProductoResultado = MutableLiveData<Resultado<Int>>()
    val agregarProductoResultado: LiveData<Resultado<Int>> get() = _agregarProductoResultado

    fun listarProductos(id: Int){
        viewModelScope.launch {
            val response = repository.listaProductoPorCate(id)
            _listaProducto.value = response
        }
    }
    fun agregarProducto(producto: Producto) {
        viewModelScope.launch {
            try {
                /*
                val gson: Gson = GsonBuilder().create()
                val jsonProducto: String = gson.toJson(producto)*/
                val resultado = repository.agregarProducto(producto)
                _agregarProductoResultado.value = resultado
            } catch (ex: Exception) {
                _agregarProductoResultado.value = Resultado.Problema(Error("001", ex.message ?: "Unknown Error"))
            }
        }
    }

}