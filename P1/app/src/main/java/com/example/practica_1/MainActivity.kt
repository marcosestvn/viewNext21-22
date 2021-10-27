package com.example.practica_1

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica_1.Model.Factura
import com.example.practica_1.Model.RespuestaFactura
import com.example.practica_1.Network.FacturaApi
import com.example.practica_1.RecyclerView.FacturaAdapter
import com.example.practica_1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), FacturaAdapter.onFacturaListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: FacturaAdapter

    private var _facturas= mutableListOf<Factura>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        getFacturas()
        initRecyclerView()


    }

    //Iniciamos el recyclerview con su correspondiente adaptador y lista de datos a mostrar,
    //también indicamos la disposición que queremos en nuestra vista, en este caso vertical
    private fun initRecyclerView() {
        adapter= FacturaAdapter(_facturas, this)
        binding.recyclerFacturas.layoutManager = LinearLayoutManager(this)
        binding.recyclerFacturas.adapter=adapter

    }


    private val BASE_URL = "http://viewnextandroid.mocklab.io/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getFacturas() {

        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<RespuestaFactura> =
                getRetrofit().create(FacturaApi::class.java).getFacturas()
           runOnUiThread {
               if (call.isSuccessful) {
                   val callBody = call.body()

                   //si callBody es nullo facturas = lista vacía
                   val facturas = callBody?.facturas ?:emptyList()
                   _facturas.clear()
                    _facturas.addAll(facturas)
                   adapter.notifyDataSetChanged()

               } else {
                   Log.i("MyTag", "ERROR COROUTINESCOPE ")
               }
           }

        }
    }


    //Función que se realiza al realizar click en el icono de cada factura
    override fun onIconoClick(facturaMostrar: Factura) {

        //Instanciamos el constructor del dialog y seteampos el título y el mensaje de la factura
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Título")
        builder.setMessage("Está función aún no está disponible")
        builder.setNeutralButton("Cerrar"){
            dialog, which ->{}
        }

        //Mostramos el dialog
        builder.show()
    }

}