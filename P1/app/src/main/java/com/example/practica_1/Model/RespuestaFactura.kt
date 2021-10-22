package com.example.practica_1.Model

class RespuestaFactura( facturas: List<Factura>, numFacturas:String) {
    var facturas :List<Factura>
    var numFacturas :String

    init{
        this.facturas=facturas
        this.numFacturas=numFacturas
    }
}