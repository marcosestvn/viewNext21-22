package com.example.practica_1.RecyclerView

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_1.Model.Factura
import com.example.practica_1.databinding.ItemFacturaBinding
import java.util.*

class FacturaViewHolder(view: View, private val facturaClickListener: FacturaAdapter.onFacturaListener) : RecyclerView.ViewHolder(view) {
    private val binding = ItemFacturaBinding.bind(view)


    fun bind(factura: Factura) {
        binding.fecha.text=factura.fecha
        binding.importeFactura.text=factura.importeOrdenacion.toString() + " €"
        if(factura.descEstado!="Pagada") {
            binding.estadoFactura.text=factura.descEstado}
        else{
            binding.estadoFactura.text=""
        }
        val datos_fecha=obtenerFecha(factura.fecha)
        binding.fecha.text=datos_fecha[0]+" "+datos_fecha[1]+" "+datos_fecha[2]
        binding.botonPopUp.setOnClickListener{facturaClickListener.onIconoClick(factura)}

    }

    fun obtenerFecha(fecha : String): MutableList<String>{
        val separador : String ="/"
        var datos_fecha_separados : MutableList<String> = fecha.split(separador).toMutableList()
        datos_fecha_separados[1] = when(datos_fecha_separados[1]){
            "01" -> "Ene"
            "02" -> "Feb"
            "03" -> "Mar"
            "04" -> "Abr"
            "05" -> "May"
            "06" -> "Jun"
            "07" -> "Jul"
            "08" -> "Ago"
            "09" -> "Sep"
            "10" -> "Oct"
            "11" -> "Nov"
            "12" -> "Dic"

            else-> "Error"
        }
        return(datos_fecha_separados)
    }
}
