package com.example.practica_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import android.widget.SeekBar
import android.widget.Toast
import com.example.practica_1.databinding.ActivityMainBinding
import com.example.practica_1.databinding.ActivitySegundaActividadBinding
import kotlin.math.roundToInt

class SegundaActividad : AppCompatActivity() {
    private lateinit var binding: ActivitySegundaActividadBinding
    private lateinit var calendarView: CalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val seekBarSlider = binding.sliderImporte

        var valorSeekBar  :Int = 0

        binding.botonFiltrar.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("importeFiltro", valorSeekBar)
            finish()
            startActivity(intent)
        }

        binding.botonCerrarFiltro.setOnClickListener{
            finish()
        }
        binding.botonEliminarFiltros.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        seekBarSlider.max=intent.getDoubleExtra("importeMaximo",0.00).toInt()+1

        seekBarSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                valorSeekBar=progress
                binding.valorSlider.text=valorSeekBar.toString()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {


            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {


            }


        })
    }

}