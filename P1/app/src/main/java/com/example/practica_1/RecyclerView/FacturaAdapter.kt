package com.example.practica_1.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_1.Model.Factura
import com.example.practica_1.R

class FacturaAdapter(val facturas : List<Factura>) : RecyclerView.Adapter<FacturaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
        return FacturaViewHolder(layoutInflater.inflate(R.layout.item_factura,parent,false))
    }

    override fun onBindViewHolder(holder: FacturaViewHolder, position: Int) {
        val item :Factura= facturas[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return facturas.size
    }
}