package com.example.practica_1.RecyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_1.Model.Factura
import com.example.practica_1.R

class FacturaAdapter(
    val facturas: List<Factura>,
    private val facturaClickListener: onFacturaListener
) : RecyclerView.Adapter<FacturaViewHolder>() {

    //Interfaz de métodos a implementar en el listener
    interface onFacturaListener {
        fun onIconoClick(facturaMostrar: Factura)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FacturaViewHolder(layoutInflater.inflate(R.layout.item_factura, parent, false), this.facturaClickListener)
    }

    override fun onBindViewHolder(holder: FacturaViewHolder, position: Int) {
        val item: Factura = facturas[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return facturas.size
    }


}

interface onItemClickListener {
    fun onItemClick(position: Int)
}

class FacturaDiffCallBack : DiffUtil.ItemCallback<Factura>() {
    override fun areItemsTheSame(oldItem: Factura, newItem: Factura): Boolean {
        return oldItem.fecha == newItem.fecha
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Factura, newItem: Factura): Boolean {
        return oldItem == newItem
    }
}