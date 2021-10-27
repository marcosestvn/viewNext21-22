package com.example.practica_1.RecyclerView

import androidx.recyclerview.widget.DiffUtil
import com.example.practica_1.Model.Factura

class MyDiffUtil():DiffUtil.ItemCallback<Factura>() {

    //Contenido de instancias facturas iguales
    override fun areContentsTheSame(oldItem: Factura, newItem: Factura): Boolean {
        return (oldItem==newItem)
    }

    //Mismo ID (fecha)
    override fun areItemsTheSame(oldItem: Factura, newItem: Factura): Boolean {
            return (oldItem.fecha==newItem.fecha)
    }
}