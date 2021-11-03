package com.example.practica_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.SeekBar
import android.widget.Toast
import com.example.practica_1.databinding.ActivitySegundaActividadBinding
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class SegundaActividad : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaActividadBinding
    private lateinit var calendarView: CalendarView
    var valorSeekBar: Int = 0
    var fecha_Hasta: String = ""
    var fecha_Desde: String = ""
    var flag_fecha_Hasta: Boolean = false
    var flag_fecha_Desde: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.sliderImporte.max = intent.getDoubleExtra("importeMaximo", 0.00).toInt() + 1

        binding.sliderImporte.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                valorSeekBar = progress
                binding.valorSlider.text = "$valorSeekBar €"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.maxSeekbar.text = "${binding.sliderImporte.max} €"

        binding.botonCerrarFiltro.setOnClickListener {
            finish()
        }

        binding.botonEliminarFiltros.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.fechaDesde.setOnClickListener {
            showDateDialog(binding.fechaDesde.id)
        }

        binding.fechaHasta.setOnClickListener {
            showDateDialog(binding.fechaHasta.id)
        }

        binding.botonFiltrar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            fecha_Desde = binding.fechaDesde.text.toString()
            fecha_Hasta = binding.fechaHasta.text.toString()
            println(fecha_Hasta)
            val parser =  SimpleDateFormat("dd/MM/yyyy")
            val formatter = SimpleDateFormat("yyyy/MM/dd")
            val formattedDate1 = formatter.format(parser.parse(fecha_Desde))
            val formattedDate2 = formatter.format(parser.parse(fecha_Hasta))
            println(formattedDate1)
            println(formattedDate2)
            println(formattedDate1.compareTo(formattedDate2))
            //Validamos que el importe se encuentre en rango y que se haya introducido una fecha en sus correspondientes campos
            if ((valorSeekBar >= 0 && valorSeekBar <= binding.sliderImporte.max) &&
                (flag_fecha_Desde) && (flag_fecha_Hasta)
            ) {
                /*
                 if (validarFechas(fecha_Desde, fecha_Hasta)) {
                    intent.putExtra("importeFiltro", valorSeekBar)
                    intent.putExtra("fechaDesde", fecha_Desde)
                    intent.putExtra("fechaHasta", fecha_Hasta)

                        finish()
                        startActivity(intent)
                }
                 */
                     if(formattedDate1.compareTo(formattedDate2) <=0){
                         intent.putExtra("importeFiltro", valorSeekBar)
                         intent.putExtra("fechaDesde", formattedDate1)
                         intent.putExtra("fechaHasta", formattedDate2)

                         finish()
                         startActivity(intent)
                     }
               else {
                    Toast.makeText(this, "Las fechas son erroneas", Toast.LENGTH_LONG).show()

                }


            } else {
                Toast.makeText(this, "Los filtros son erroneos", Toast.LENGTH_LONG).show()

            }


        }


    }

    private fun showDateDialog(id: Int) {
        val datePicker =
            DatePickerFragment { day, month, year -> onDateSelected(day, month, year, id) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int, id: Int) {

        if (id == R.id.fechaDesde) {

            flag_fecha_Desde = true
            binding.fechaDesde.text = "$day/$month/$year"
        } else {
            flag_fecha_Hasta = true

            binding.fechaHasta.text = "$day/$month/$year"

        }
    }

    private fun bind(valorSeekBar: Int, fechaDesde: String, fechaHasta: String) {


    }

    //Función para validar que los filtros hayan sido definidos
    private fun validarFechas(fecha_Desde: String, fecha_Hasta: String): Boolean {

            return false
        /*
        val separador: String = "/"
        var desde_separados: List<String> = fecha_Desde.split(separador)
        var hasta_separados: List<String> = fecha_Hasta.split(separador)
        println(desde_separados[2])
        println(hasta_separados[2])

        //Comparamos los años
        if (desde_separados[2] < hasta_separados[2]) {
            return true
        }else if(desde_separados[2]==hasta_separados[2]){
            println("Años iguales")
            //Si es el mismo año comparamos los meses
            if (desde_separados[1] < hasta_separados[1]) {
                return true

            }else if ( desde_separados[1] == hasta_separados[1]){
                println("Meses iguales")

                //Si es el mimso mes comparamos el día, nos da igual si es dia desde es menor o igual
                // que el dia hasta, ambas son válidas
                if(desde_separados[0] <= hasta_separados[0])return true

                    return false
            }
        }
        return false

         */
    }
}