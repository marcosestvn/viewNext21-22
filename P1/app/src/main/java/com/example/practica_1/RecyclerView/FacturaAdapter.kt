package com.example.practica_1.RecyclerView

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_1.Model.Factura
import com.example.practica_1.R

class FacturaAdapter(
    private val facturaClickListener: onFacturaListener
) : ListAdapter<Factura,FacturaViewHolder>(MyDiffUtil()) {

    //Interfaz de métodos a implementar en el listener
    interface onFacturaListener {
        fun onIconoClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FacturaViewHolder(layoutInflater.inflate(R.layout.item_factura, parent, false), this.facturaClickListener)
    }

    override fun onBindViewHolder(holder: FacturaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
