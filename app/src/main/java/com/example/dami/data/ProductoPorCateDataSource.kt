package com.example.dami.data

import com.example.dami.entity.Resultado
import com.example.dami.network.ConexionApi
import com.example.dami.entity.Error
import com.example.dami.entity.Producto
import java.net.SocketTimeoutException

class ProductoPorCateDataSource {
    suspend fun servicioProductoPorCate(id: Int): Resultado<List<Producto>>{
        return try {
            val result = ConexionApi.retrofitService.obtenerProductosPorCategoria(id) //pasar el valor del id
            Resultado.Exito(result)
        } catch (ex: Exception){
            Resultado.Problema(Error("001","Error $ex"))
        } catch (ex: SocketTimeoutException){
            Resultado.Problema(Error("002","Error $ex"))
        }
    }
}