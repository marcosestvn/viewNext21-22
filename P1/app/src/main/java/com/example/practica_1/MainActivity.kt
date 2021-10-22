package com.example.practica_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practica_1.Model.Factura
import com.example.practica_1.Model.RespuestaFactura
import com.example.practica_1.Network.FacturaApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private var _facturas: MutableLiveData<List<Factura>> = MutableLiveData<List<Factura>>()
    val facturas: LiveData<List<Factura>>
        get() = _facturas


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFacturas()
        initRecyclerView()

    }

    private fun initRecyclerView() {

    }


    private val BASE_URL = "http://viewnextandroid.mocklab.io/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun getFacturas() {

        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<RespuestaFactura> = getRetrofit().create(FacturaApi::class.java).getFacturas()
            if(call.isSuccessful){
                val callBody= call.body()
                Log.i("MyTag",callBody.toString())

            }else{
                Log.i("MyTag","ERROR COROUTINESCOPE ")
            }
        }
    }


}