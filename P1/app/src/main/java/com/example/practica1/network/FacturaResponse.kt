package com.example.practica1.network

import com.example.practica1.Model.Factura
import com.google.gson.annotations.SerializedName

class FacturaResponse(
    @SerializedName("numFacturas") var numFacturas : Int,
    @SerializedName("facturas")  var facturas : List<Factura>) {

}