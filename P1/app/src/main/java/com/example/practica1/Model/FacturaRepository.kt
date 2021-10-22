package com.example.practica1.Model

import com.example.practica1.UI.viewModel.FacturaViewModel
import com.example.practica1.network.FacturasService

class FacturaRepository {

    private val api = FacturasService()

    suspend fun getFacturas():List<Factura>{
        val response: List<Factura> = api.getFacturas()

        return response
    }
}