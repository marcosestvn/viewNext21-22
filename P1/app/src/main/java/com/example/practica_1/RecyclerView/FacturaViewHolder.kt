package com.example.practica_1.RecyclerView

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_1.Model.Factura
import com.example.practica_1.databinding.ItemFacturaBinding

class FacturaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemFacturaBinding.bind(view)
    fun bind(factura: Factura) {
        binding.fecha.text=factura.fecha
        binding.importeFactura.text=factura.importeOrdenacion.toString() + " €"
        if(factura.descEstado!="Pagada") binding.estadoFactura.text=factura.descEstado
        Log.i("bubi", factura.descEstado)
    }
}
