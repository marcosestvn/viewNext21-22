package com.example.practica_1.Network

import com.example.practica_1.Model.Factura
import com.example.practica_1.Model.RespuestaFactura
import retrofit2.Response
import retrofit2.http.GET

interface FacturaApi {

    @GET("/facturas")
    suspend fun getFacturas(): Response<RespuestaFactura>

}