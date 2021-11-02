package com.example.practica_1.Model

import android.os.Parcel
import android.os.Parcelable

data class Factura constructor(val descEstado:String, val importeOrdenacion :Double, val fecha : String):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(descEstado)
        parcel.writeDouble(importeOrdenacion)
        parcel.writeString(fecha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Factura> {
        override fun createFromParcel(parcel: Parcel): Factura {
            return Factura(parcel)
        }

        override fun newArray(size: Int): Array<Factura?> {
            return arrayOfNulls(size)
        }
    }

}
