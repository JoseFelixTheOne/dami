package com.example.dami.repository

import com.example.dami.data.CategoriaDataSource
import com.example.dami.data.PersonaDataSource
import com.example.dami.entity.Categoria
import com.example.dami.entity.Persona
import com.example.dami.entity.Resultado

class PersonaRepository(private val dataSource: PersonaDataSource) {

    suspend fun listaPersona(): Resultado<List<Persona>> {
        val response = dataSource.servicioPersona()
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

    suspend fun agregarPersona(persona: Persona): Resultado<Int> {
        val response = dataSource.agregarPersona(persona)
        return when (response) {
            is Resultado.Exito -> Resultado.Exito(1)
            is Resultado.Problema -> response
        }
    }

    suspend fun eliminarPersona(id: Int): Resultado<Int> {
        val response = dataSource.eliminarPersona(id)
        return when (response) {
            is Resultado.Exito -> Resultado.Exito(1)
            is Resultado.Problema -> response
        }
    }
}