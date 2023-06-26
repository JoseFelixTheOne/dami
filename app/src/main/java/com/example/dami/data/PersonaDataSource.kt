package com.example.dami.data

import com.example.dami.entity.Categoria
import com.example.dami.entity.Error
import com.example.dami.entity.Persona
import com.example.dami.entity.Resultado
import com.example.dami.network.ConexionApi
import java.net.SocketTimeoutException

class PersonaDataSource {

    suspend fun servicioPersona(): Resultado<List<Persona>> {
        return try {
            val result = ConexionApi.retrofitService.obtenerPersona()
            Resultado.Exito(result)
        } catch (ex: Exception){
            Resultado.Problema(Error("001","Error $ex"))
        } catch (ex: SocketTimeoutException){
            Resultado.Problema(Error("002","Error $ex"))
        }
    }

    suspend fun agregarPersona(persona: Persona): Resultado<Int> {
        return try {
            val response = ConexionApi.retrofitService.agregarPersona(persona)
            if (response > 0) {
                Resultado.Exito(response)
            } else {
                Resultado.Problema(Error("003", "Error en la solicitud"))
            }
        } catch (ex: Exception) {
            Resultado.Problema(Error("001", "Error $ex"))
        } catch (ex: SocketTimeoutException) {
            Resultado.Problema(Error("002", "Error $ex"))
        }

    }

    suspend fun eliminarPersona(id: Int): Resultado<Int> {
        return try {
            val response = ConexionApi.retrofitService.eliminarPersona(id)
            if (response > 0) {
                Resultado.Exito(response)
            } else {
                Resultado.Problema(Error("003", "Error en la solicitud"))
            }
        } catch (ex: Exception) {
            Resultado.Problema(Error("001", "Error $ex"))
        } catch (ex: SocketTimeoutException) {
            Resultado.Problema(Error("002", "Error $ex"))
        }
    }

}