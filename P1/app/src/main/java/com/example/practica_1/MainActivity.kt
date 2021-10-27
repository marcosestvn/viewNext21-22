package com.example.practica_1

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
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

    private val BASE_URL = "http://viewnextandroid.mocklab.io/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(
            layoutInflater
        )
        setContentView(binding.root)
        getFacturas()
        initRecyclerView()
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    //Iniciamos el recyclerview con su correspondiente adaptador y lista de datos a mostrar,
    //también indicamos la disposición que queremos en nuestra vista, en este caso vertical
    private fun initRecyclerView() {
        adapter= FacturaAdapter(this)
        binding.recyclerFacturas.layoutManager = LinearLayoutManager(this)
        binding.recyclerFacturas.adapter=adapter
        adapter.submitList(_facturas)

    }

    //Instanciamos la clase Retrofit pasandole como parámetro del método add..Factory el GSONConverter
    //este objeto nos permitira realizar las llamadas a la API y nos devolverá el objeto parseado
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    //Método GET para obtener las fácturas de nuestra API el cual se realiza en un hilo secundario, IO,
    //mediante una corutina
    private fun getFacturas() {

        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<RespuestaFactura> =
                getRetrofit().create(FacturaApi::class.java).getFacturas()
           runOnUiThread {

               //Si la llamada es exitosa almacenamos las facturas recibidas de la API en una variable local
               if (call.isSuccessful) {
                   val callBody = call.body()

                   //si callBody es nullo facturas = lista vacía
                   val facturas = callBody?.facturas ?:emptyList()
                   _facturas.clear()
                    _facturas.addAll(facturas)
                   adapter.notifyDataSetChanged()



               }
               //Si la llamada no es exitosa mostramos un error por consola
               else {
                   Log.i("MyTag", "ERROR COROUTINESCOPE ")
               }
           }

        }
    }


    //Lógica que se realiza al realizar click en el icono de cada factura
    override fun onIconoClick() {

        //Instanciamos el constructor del dialog y seteamos el título y el mensaje
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