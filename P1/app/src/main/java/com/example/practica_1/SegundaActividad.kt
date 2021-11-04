package com.example.practica_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.Toast
import com.example.practica_1.databinding.ActivitySegundaActividadBinding
import java.io.Serializable
import java.text.SimpleDateFormat

class SegundaActividad : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaActividadBinding
    var valorSeekBar: Int = 0
    var fecha_Hasta: String = ""
    var fecha_Desde: String = ""
    var flag_fecha_Hasta: Boolean = false
    var flag_fecha_Desde: Boolean = false
    val parser = SimpleDateFormat("dd/MM/yyyy")
    val formatter = SimpleDateFormat("yyyy/MM/dd")
    val listaCheckBoxChecked: MutableList<String> = ArrayList()

    /* Campos checkbox */
    lateinit var opcion1: CheckBox
    lateinit var opcion2: CheckBox
    lateinit var opcion3: CheckBox
    lateinit var opcion4: CheckBox
    lateinit var opcion5: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }


    private fun bind() {
        //Bindeos Slider Importe
        binding.sliderImporte.max = intent.getDoubleExtra("importeMaximo", 0.00).toInt() + 1

        binding.maxSeekbar.text = "${binding.sliderImporte.max} €"

        binding.sliderImporte.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                valorSeekBar = progress
                binding.valorSlider.text = "$valorSeekBar €"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        //Bindeos Dialog selector de fechas
        binding.fechaDesde.setOnClickListener {
            showDateDialog(binding.fechaDesde.id)
        }

        binding.fechaHasta.setOnClickListener {
            showDateDialog(binding.fechaHasta.id)
        }


        //Bindeo botón Cerrar Actividad
        binding.botonCerrarFiltro.setOnClickListener {
            finish()
        }

        //Bindeo boton Eliminar Filtros
        binding.botonEliminarFiltros.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //Bindeo botón filtrar y lógica de validación de campos
        binding.botonFiltrar.setOnClickListener {
            recogerYValidarCheckBox()
            //Validamos que el importe se encuentre en rango y que se haya introducido una fecha en sus correspondientes campos
            if ((valorSeekBar >= 0 && valorSeekBar <= binding.sliderImporte.max) &&
                (flag_fecha_Desde) && (flag_fecha_Hasta)
            ) {


                fecha_Desde = binding.fechaDesde.text.toString()
                fecha_Hasta = binding.fechaHasta.text.toString()

                val formattedDate1 = formatter.format(parser.parse(fecha_Desde))
                val formattedDate2 = formatter.format(parser.parse(fecha_Hasta))

                if (formattedDate1.compareTo(formattedDate2) <= 0) {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("importeFiltro", valorSeekBar)
                    intent.putExtra("fechaDesde", formattedDate1)
                    intent.putExtra("fechaHasta", formattedDate2)
                    intent.putExtra("estadosSeleccionados", listaCheckBoxChecked as Serializable)

                    finish()
                    startActivity(intent)

                } else {
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

    private fun recogerYValidarCheckBox() {
            listaCheckBoxChecked.clear()
        if (binding.idOpcion1.isChecked) {
            listaCheckBoxChecked.add("Pagada")
        }

        if (binding.idOpcion2.isChecked) {
            listaCheckBoxChecked.add("Anulada")
        }
        if (binding.idOpcion3.isChecked) {
            listaCheckBoxChecked.add("Cuota fija")
        }
        if (binding.idOpcion4.isChecked) {
            listaCheckBoxChecked.add("Pendiente de pago")
        }
        if (binding.idOpcion5.isChecked) {
            listaCheckBoxChecked.add("Plan de cuota")
        }
        println(listaCheckBoxChecked.toString())
    }
}