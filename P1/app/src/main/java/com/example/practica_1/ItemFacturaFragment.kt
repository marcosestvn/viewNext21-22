package com.example.practica_1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.practica_1.databinding.ItemFacturaBinding

class ItemFacturaFragment : Fragment() {
    private lateinit var bindingItemFactura: ItemFacturaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        bindingItemFactura= ItemFacturaBinding.inflate(
            layoutInflater
        )
        bindingItemFactura.botonPopUp.setOnClickListener{
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}

