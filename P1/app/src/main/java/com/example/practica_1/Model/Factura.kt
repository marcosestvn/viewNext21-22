package com.example.practica_1.Model

import java.util.*

 class Factura constructor(decEstado:String, importeOrdenacion :Double, fecha : String){
    var decEstad : String
    var importeOrdenacion: Double
    var fecha :String

    init{
        this.decEstad=decEstado
        this.importeOrdenacion=importeOrdenacion
        this.fecha=fecha

    }
     override fun toString(): String {
         return super.toString()
     }

 }
