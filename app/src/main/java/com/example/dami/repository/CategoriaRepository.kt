package com.example.dami.repository

import com.example.dami.data.CategoriaDataSource
import com.example.dami.entity.Categoria
import com.example.dami.entity.Resultado

class CategoriaRepository(private val dataSource: CategoriaDataSource) {
    suspend fun listaCategoria(): Resultado<List<Categoria>>{
        val response = dataSource.servicioCategoria()
        return when(response){
            is Resultado.Exito -> {
                val lista = response.data
                Resultado.Exito(lista)
            }
            is Resultado.Problema -> {
                response
            }
        }
    }
    suspend fun agregarCategoria(categoria: Categoria): Resultado<Int> {
        val response = dataSource.agregarCategoria(categoria)
        return when (response) {
            is Resultado.Exito -> Resultado.Exito(1)
            is Resultado.Problema -> response
        }
    }

    suspend fun eliminarCategoria(id: Int): Resultado<Int> {
        val response = dataSource.eliminarCategoria(id)
        return when (response) {
            is Resultado.Exito -> Resultado.Exito(1)
            is Resultado.Problema -> response
        }
    }
}