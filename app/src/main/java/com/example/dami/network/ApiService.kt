package com.example.dami.network

import com.example.dami.entity.Categoria
import com.example.dami.entity.Persona
import com.example.dami.entity.Producto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    /**Servicios**/

    @GET("api/Categoria")
    suspend fun obtenerCategoria(): List<Categoria>

    @POST("api/Categoria")
    suspend fun agregarCategoria(@Body categoria: Categoria): Int

    @DELETE("api/Categoria/{id}")
    suspend fun eliminarCategoria(@Path("id") id: Int): Int

    //Producto
    @GET("api/Producto/{id}")
    suspend fun obtenerProductosPorCategoria(@Path("id") id: Int): List<Producto>

    @POST("api/Producto")
    suspend fun agregarProducto(@Body producto: Producto): Int

    @DELETE("api/Producto/{id}")
    suspend fun eliminarProducto(@Path("id") id: Int): Int

    /**Persona***********************************************************/
    @GET("api/Persona")
    suspend fun obtenerPersona(): List<Persona>

    @POST("api/Persona")
    suspend fun agregarPersona(@Body persona: Persona): Int

    @DELETE("api/Persona/{id}")
    suspend fun eliminarPersona(@Path("id") id: Int): Int
}