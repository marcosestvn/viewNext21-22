package com.example.practica_1

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.practica_1.databinding.ActivitySegundaActividadBinding
import com.example.practica_1.model.WrapperFiltro
import com.google.gson.Gson
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat


class FiltroActividad : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaActividadBinding
    var valorSeekBar: Int =0
    private lateinit var fecha1: DateTime
    private lateinit var fecha2: DateTime



    lateinit var wrapperFiltro: WrapperFiltro


    override fun onCreate(savedInstanceState: Bundle?) {
        wrapperFiltro =
            Gson().fromJson(intent.getStringExtra(Constantes.wrapper).toString(), WrapperFiltro::class.java)

        super.onCreate(savedInstanceState)
        binding = ActivitySegundaActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bind()
    }


    private fun bind() {

        bindeoConDatosWrapper()

        binding.sliderImporte.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                valorSeekBar = progress
                binding.valorSlider.text =concatenar(valorSeekBar.toString(),"€")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                wrapperFiltro.setImporteFiltro(valorSeekBar)
            }
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
            recogerYValidarCheckBox()
            finish()
        }

        //Bindeo boton Eliminar Filtros
        binding.botonEliminarFiltros.setOnClickListener {

            wrapperFiltro.setImporteFiltro(0)
            wrapperFiltro.setEstadosFiltro(ArrayList<String>())
            wrapperFiltro.setFecha_desde("")
            wrapperFiltro.setFecha_hasta("")
            binding.valorSlider.text=Constantes.valorIniciarSlider
            binding.sliderImporte.progress=0
           bind()

        }

        //Bindeo botón filtrar y lógica de validación de campos
        binding.botonFiltrar.setOnClickListener {
            recogerYValidarCheckBox()

            val intent = Intent(this, MainActivity::class.java)

            intent.putExtra(Constantes.wrapperFiltro, Gson().toJson(wrapperFiltro).toString())

            setResult(RESULT_OK,intent)

            finish()
        }
    }


    private fun onDateSelected(day: Int, month: Int, year: Int, id: Int) {
        val ff = DateTimeFormat.forPattern(Constantes.defaultPatternFecha)
        if (id == R.id.fechaDesde) {
            fecha1 = DateTimeFormat.forPattern(Constantes.defaultPatternFecha).parseDateTime("$day/${month + 1}/$year")

            binding.fechaDesde.text = concatenarSinEspacios(day.toString(),"/",(month + 1).toString(),"/",year.toString())

            wrapperFiltro.setFecha_desde(ff.print(fecha1))


        } else {
            fecha2 = DateTimeFormat.forPattern(Constantes.defaultPatternFecha).parseDateTime("$day/${month + 1}/$year")

            println(fecha2)
            binding.fechaHasta.text = concatenarSinEspacios(day.toString(),"/",(month + 1).toString(),"/",year.toString())

            wrapperFiltro.setFecha_hasta(ff.print(fecha2))

        }
    }

    private fun recogerYValidarCheckBox() {

        //Se evalua individualmente cada campo del checkbox
        //Se añade  su correspondiente estado ( no sé si deberiamos guardar los strings en string.xml)
        if (binding.idOpcion1.isChecked) {
            wrapperFiltro.introducirEstadoFiltro(Constantes.opcion1)
        }else{
            wrapperFiltro.eliminarEstadoFiltro(Constantes.opcion1)
        }

        if (binding.idOpcion2.isChecked) {
            wrapperFiltro.introducirEstadoFiltro(Constantes.opcion2)
        }else{
            wrapperFiltro.eliminarEstadoFiltro(Constantes.opcion2)
        }

        if (binding.idOpcion3.isChecked) {
            wrapperFiltro.introducirEstadoFiltro(Constantes.opcion3)
        }else{
            wrapperFiltro.eliminarEstadoFiltro(Constantes.opcion3)
        }

        if (binding.idOpcion4.isChecked) {
            wrapperFiltro.introducirEstadoFiltro(Constantes.opcion4)
        }else{
            wrapperFiltro.eliminarEstadoFiltro(Constantes.opcion4)
        }

        if (binding.idOpcion5.isChecked) {
            wrapperFiltro.introducirEstadoFiltro(Constantes.opcion5)

        }else{
            wrapperFiltro.eliminarEstadoFiltro(Constantes.opcion5)
        }


    }

    private fun pintarCheckBox(datosPrevios:List<String>){

        binding.idOpcion1.isChecked = datosPrevios.contains(Constantes.opcion1)
        binding.idOpcion2.isChecked = datosPrevios.contains(Constantes.opcion2)
        binding.idOpcion3.isChecked = datosPrevios.contains(Constantes.opcion3)
        binding.idOpcion4.isChecked = datosPrevios.contains(Constantes.opcion4)
        binding.idOpcion5.isChecked = datosPrevios.contains(Constantes.opcion5)
    }

    private fun bindeoConDatosWrapper(){
        //Slider
        binding.sliderImporte.progress=wrapperFiltro.getImporteFiltro()
        binding.sliderImporte.max = wrapperFiltro.getImporteMaximo()
        binding.maxSeekbar.text = concatenar(wrapperFiltro.importeMaximo.toString(),"€")
        binding.valorSlider.text = wrapperFiltro.getImporteFiltro().toString()

        //CheckBox
        pintarCheckBox(wrapperFiltro.estadosFiltro)

        //DatePickers
        if(wrapperFiltro.getFecha_desde().isEmpty()){
            binding.fechaDesde.text=getString(R.string.dia_mes_año)

        }else{
            binding.fechaDesde.text=wrapperFiltro.getFecha_desde()
        }

        if(wrapperFiltro.getFecha_hasta().isEmpty()){
            binding.fechaHasta.text=getString(R.string.dia_mes_año)
        }else{
            binding.fechaHasta.text=wrapperFiltro.getFecha_hasta()

        }

    }

    private fun showDateDialog(id: Int) {
        val datePicker =
            DatePickerFragment { day, month, year -> onDateSelected(day, month, year, id) }

        if(id == R.id.fechaDesde){
            if(wrapperFiltro.getFecha_hasta().isNotEmpty()){
                datePicker.setMaxDate(wrapperFiltro.getFecha_hasta())
            }
            if(wrapperFiltro.getFecha_desde().isNotEmpty()){
                datePicker.setDate(wrapperFiltro.getFecha_desde())
            }
        }


        if(id == R.id.fechaHasta){
            if(wrapperFiltro.getFecha_desde().isNotEmpty()){
                datePicker.setMinDate(wrapperFiltro.getFecha_desde())
            }
            if(wrapperFiltro.getFecha_hasta().isNotEmpty()){
                datePicker.setDate(wrapperFiltro.getFecha_hasta())
            }

        }
        datePicker.show(supportFragmentManager, "datePicker")


    }



}