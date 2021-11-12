package com.example.practica_1

import junit.framework.TestCase
import org.junit.Test

class MainActivityTest : TestCase() {

    private val mainActivity: MainActivity = MainActivity()

    @Test
    fun testGetMaxImporte() {
        val resultEsperado = 70.00
        val facturas: List<Factura> = listOf<Factura>(
            Factura("Pagad", 700.00, "18/04/1998"),
            Factura("Pagad", -70.00, "18/04/1998")
        )

        assertEquals(resultEsperado,mainActivity.getMaxImporte(facturas))
    }
}