package com.example.practica_1.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.practica_1.Constantes
import com.example.practica_1.concatenar
import com.example.practica_1.concatenarSinEspacios
import com.example.practica_1.databinding.ItemFacturaBinding
import com.example.practica_1.model.Factura
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import java.util.*

class FacturaViewHolder(view: View, private val facturaClickListener: FacturaAdapter.OnFacturaListener) : RecyclerView.ViewHolder(view) {
    private val binding = ItemFacturaBinding.bind(view)


//Asignamos a cada correspondiende campo de nuestra vista (view) su valor a partir del objeto factura que llegue
    fun bind(factura: Factura) {
        //Importe
        binding.importeFactura.text=concatenar(factura.importeOrdenacion.toString(), Constantes.divisa)

        //Estado
        if(factura.descEstado!=Constantes.opcion1) {
            binding.estadoFactura.visibility=View.VISIBLE
            binding.estadoFactura.text=factura.descEstado}
        else{
            binding.estadoFactura.visibility=View.GONE
        }

        //Fecha
        binding.fecha.text=obtenerFecha(factura.fecha)

        //Evento onClick
        binding.factura.setOnClickListener{facturaClickListener.onFacturaClick()}

    }

    //Función auxiliar para separar la fecha recibida en formato String DD/MM/YYYY para formatearla
    //en el formato que se pide
    private fun obtenerFecha(fecha : String): String{

        val local  = Locale(Constantes.idioma, Constantes.pais)
        val fechaNueva = DateTimeFormat.forPattern(Constantes.defaultPatternFecha).parseDateTime(fecha)
        var format : DateTimeFormatter = DateTimeFormat.forPattern(Constantes.tresPrimerasLetrasMesPattern).withLocale(local)
        var mes = format.print(fechaNueva)
        mes= concatenarSinEspacios(mes[0].uppercase(),mes.substring(1,3))

        format = DateTimeFormat.forPattern(Constantes.diaPattern)
        val dia =format.print(fechaNueva)

        format = DateTimeFormat.forPattern(Constantes.anyoPattern)
        val anyo = format.print(fechaNueva)

        return(concatenar(dia,mes,anyo))
    }
}
