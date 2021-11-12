package com.example.practica_1.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.practica_1.R
import com.example.practica_1.model.Factura

class FacturaAdapter(
    private val facturaClickListener: OnFacturaListener
) : ListAdapter<Factura,FacturaViewHolder>(MyDiffUtil()) {

    //Interfaz de métodos a implementar en el listener
    interface OnFacturaListener {
        fun onFacturaClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FacturaViewHolder(layoutInflater.inflate(R.layout.item_factura, parent, false), this.facturaClickListener)
    }

    override fun onBindViewHolder(holder: FacturaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
