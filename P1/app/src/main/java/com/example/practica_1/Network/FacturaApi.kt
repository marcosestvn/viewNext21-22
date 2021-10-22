package com.example.practica_1.network

import com.example.practica_1.Model.Factura
import retrofit2.http.GET

interface api_Interface {

    @GET("/facturas")
    suspend fun getFacturas(): List<Factura>

}