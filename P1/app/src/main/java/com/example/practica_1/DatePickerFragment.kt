package com.example.practica_1

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment(val listener : (year : Int, month : Int, day : Int ) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener  {

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
            listener(dayOfMonth,month,year)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendario : Calendar = Calendar.getInstance()
        val day = calendario.get(Calendar.DAY_OF_MONTH)
        val month = calendario.get(Calendar.MONTH)-1
        val year = calendario.get(Calendar.YEAR)


        val picker = DatePickerDialog(activity as Context,this,year,month+1,day )
        return picker
    }
}