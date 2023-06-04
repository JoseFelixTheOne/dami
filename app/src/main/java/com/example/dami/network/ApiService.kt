package com.example.dami.network

import com.example.dami.entity.CateResult
import com.example.dami.entity.Categoria
import retrofit2.http.GET

interface ApiService {
    /**Servicios**/

    @GET("api/Categoria")
    suspend fun obtenerCategoria(): List<Categoria>
}