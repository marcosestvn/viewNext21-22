package com.example.practica1.network

import com.example.practica1.Model.Factura
import retrofit2.Response
import retrofit2.http.GET

interface FacturaApiClient {
    @GET("facturas")
    suspend fun getFacturas(): Response<List<Factura>>
}