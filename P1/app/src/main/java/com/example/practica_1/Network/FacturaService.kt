package com.example.practica_1.Network

import androidx.databinding.DataBindingUtil
import com.example.practica_1.Model.Factura
import com.example.practica_1.Model.RespuestaFactura
import com.example.practica_1.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FacturaService {

    private val  BASE_URL  = "http://viewnextandroid.mocklab.io/"

    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    suspend fun getFacturas():Response<RespuestaFactura>{

        return withContext(Dispatchers.IO){
            val response = retrofit.create(FacturaApi::class.java).getFacturas()
            response
        }
    }
}