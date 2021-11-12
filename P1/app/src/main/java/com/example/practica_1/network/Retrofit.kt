package com.example.practica_1.network

import com.example.practica_1.Constantes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//Se instancia la clase Retrofit pasandole como parámetro del método add..Factory el GSONConverter
//este objeto permitira realizar las llamadas a la API y devolverá el objeto parseado
 fun getRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(Constantes.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

