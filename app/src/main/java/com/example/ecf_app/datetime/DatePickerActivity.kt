package com.example.ecf_app.datetime

import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityDatePickerBinding


class DatePickerActivity : AppCompatActivity() {
    private var datePicker: DatePicker? = null
    private lateinit var binding: ActivityDatePickerBinding
    private val REQUEST_DATE_TIME_PICKER = 1
    private lateinit var hour: String
    private lateinit var minute: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        datePicker = binding.DatePickerActivityDatePicker

        val valeur = intent.getBooleanExtra("valeur",false)

        if (valeur){
            val year = intent.getStringExtra("year")
            val month = intent.getStringExtra("month")
            val day = intent.getStringExtra("day")
            hour = intent.getStringExtra("hour").toString()
            minute = intent.getStringExtra("minute").toString()
            datePicker!!.updateDate(year!!.toInt(),month!!.toInt()-1,day!!.toInt())
        }

        binding.DatePickerBtnDone.setOnClickListener { // Récupérez la date sélectionnée
            val intent = Intent(
                it.context,
                TimePickerActivity::class.java
            )
            if (valeur){
                intent.putExtra("valeur",true)
                intent.putExtra("hour",hour)
                intent.putExtra("minute",minute)
            }else{
                intent.putExtra("valeur",false)
            }
            startActivityForResult(intent, REQUEST_DATE_TIME_PICKER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DATE_TIME_PICKER && resultCode == RESULT_OK) {
            val year = datePicker!!.year
            val month = datePicker!!.month
            val day = datePicker!!.dayOfMonth

            // Récupérez les informations renvoyées par DateTimePickerActivity
            val hour = data?.getIntExtra("hour", 0)
            val minute = data?.getIntExtra("minute", 0)

            // Créez un objet Intent pour renvoyer les informations
            val resultIntent = Intent()
            resultIntent.putExtra("year", year)
            resultIntent.putExtra("month", month)
            resultIntent.putExtra("day", day)
            resultIntent.putExtra("hour", hour)
            resultIntent.putExtra("minute", minute)

            // Définissez le résultat et terminez l'activité
            setResult(RESULT_OK, resultIntent)
            finish()

        }
    }

}