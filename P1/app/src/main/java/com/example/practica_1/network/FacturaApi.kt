package com.example.practica_1.network

import com.example.practica_1.model.RespuestaFactura
import retrofit2.Response
import retrofit2.http.GET

interface FacturaApi {

    @GET("/facturas")
    suspend fun getFacturas(): Response<RespuestaFactura>

}