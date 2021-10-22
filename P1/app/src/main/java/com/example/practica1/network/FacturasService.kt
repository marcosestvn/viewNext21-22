package com.example.practica1.network


import com.example.practica1.Model.Factura
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//CUERPO DE ENDPOINTS DE NUESTRA API
private const val URL = "http://viewnextandroid.mocklab.io/"
private val retrofit = RetrofitHelp.getRetrofit()

class FacturasService {


    //Función para retornar todas las facturas en un hilo no principal
    suspend fun getFacturas():List<Factura>{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(FacturaApiClient::class.java).getFacturas()
            response.body() ?: emptyList()
        }

    }

}

//Endpoints de nuestra API
public interface FacturaApiInterface {
    @GET("facturas")
     fun getFacturas(): Call<List<Factura>>
}

object RetrofitHelp {
    //Instanciamos retrofit y usamos la libería GSON CONVERTER para parsear los datos
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
object FacturasApi{
    val retrofitService:FacturasService by lazy{
        retrofit.create(FacturasService::class.java)
    }
}


