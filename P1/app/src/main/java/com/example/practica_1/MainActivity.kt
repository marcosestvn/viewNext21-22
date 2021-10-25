package com.example.practica_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica_1.Model.Factura
import com.example.practica_1.Model.RespuestaFactura
import com.example.practica_1.Network.FacturaApi
import com.example.practica_1.RecyclerView.FacturaAdapter
import com.example.practica_1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

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

    private fun initRecyclerView() {
        adapter= FacturaAdapter(_facturas)
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
                   Log.i("bubi", callBody.toString())

               } else {
                   Log.i("MyTag", "ERROR COROUTINESCOPE ")
               }
           }

        }
    }


}