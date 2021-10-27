package com.example.practica_1.RecyclerView

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_1.Model.Factura
import com.example.practica_1.databinding.ItemFacturaBinding
import java.util.*

class FacturaViewHolder(view: View, private val facturaClickListener: FacturaAdapter.onFacturaListener) : RecyclerView.ViewHolder(view) {
    private val binding = ItemFacturaBinding.bind(view)


    //Asignamos a cada correspondiende campo de nuestra vista (view) su valor a partir del objeto factura que llegue
    fun bind(factura: Factura) {
        //Importe
        binding.importeFactura.text=factura.importeOrdenacion.toString() + " €"

        //Estado
        if(factura.descEstado!="Pagada") {
            binding.estadoFactura.text=factura.descEstado}
        else{
            binding.estadoFactura.text=""
        }

        //Fecha
        val datos_fecha=obtenerFecha(factura.fecha)
        binding.fecha.text=datos_fecha[0]+" "+datos_fecha[1]+" "+datos_fecha[2]


        binding.botonPopUp.setOnClickListener{facturaClickListener.onIconoClick()}

    }

    //Función auxiliar para separar la fecha recibida en formato String DD/MM/YYYY para formatearla
    //en el formato que se pide
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
