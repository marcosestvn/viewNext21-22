package com.example.practica1.UI.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practica1.Model.Factura
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practica1.network.FacturasApi
import com.example.practica1.network.FacturasService
import com.example.practica1.network.RetrofitHelp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FacturaViewModel: ViewModel() {

    private var _facturas = MutableLiveData<List<Factura>>()

    val factura:LiveData<List<Factura>>
    get()=_facturas

    init{

    }


    private fun getFacturas(){
        val response : List<Factura>


    }
}