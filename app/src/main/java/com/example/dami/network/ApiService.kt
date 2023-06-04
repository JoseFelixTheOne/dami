package com.example.dami.network

import com.example.dami.entity.Categoria
import com.example.dami.entity.Producto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    /**Servicios**/

    @GET("api/Categoria")
    suspend fun obtenerCategoria(): List<Categoria>

    @GET("api/Producto/{id}")
    suspend fun obtenerProductosPorCategoria(@Path("id") id: Int): List<Producto>

}