package com.example.ageinmin

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// This will create the onclick for button.
        agebutton.setOnClickListener { view ->
            datepicker(view)
        }
    }


    fun datepicker(view:View){
 //These are the values which makes the dialog of date picker.
        val mycalender = Calendar.getInstance()
        val year = mycalender.get(Calendar.YEAR)
        val month =mycalender.get(Calendar.MONTH)
        val day = mycalender.get(Calendar.DAY_OF_MONTH)
        val dp=DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    view,selectedYear, selectedMonth, dayofMonth ->
 //JAVA is sick and counts months from 0.
                val selecteddate="$dayofMonth/${selectedMonth+1}/$selectedYear"
                agebutton.setText(selecteddate)
                agebutton.alpha=1f
 //formatting date.
                val sdf = SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH)
 //converting from string to date.
                val dob = sdf.parse(selecteddate)
 // !! is a not null expression
                val ageinMinutes=dob!!.time/60000
                val presentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
                val presentMin=presentDate!!.time/60000
                val dateinmin =presentMin - ageinMinutes
                textView4.setText(dateinmin.toString())

            }
            ,year
            ,month
            ,day)
//this restricts you from selecting current and further dates.
        dp.datePicker.setMaxDate(Date().time-86400000)
        dp.show()
    }



}
