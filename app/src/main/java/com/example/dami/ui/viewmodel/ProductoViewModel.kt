package com.example.dami.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dami.entity.Producto
import com.example.dami.entity.Resultado
import com.example.dami.repository.ProductoPorCateRepository
import kotlinx.coroutines.launch

class ProductoViewModel(private val repository: ProductoPorCateRepository): ViewModel() {

    private var _listaProducto = MutableLiveData<Resultado<List<Producto>>>()
    val listaProducto: LiveData<Resultado<List<Producto>>> = _listaProducto

    fun listarProductos(id: Int){
        viewModelScope.launch {
            val response = repository.listaProductoPorCate(id)
            _listaProducto.value = response
        }
    }
}