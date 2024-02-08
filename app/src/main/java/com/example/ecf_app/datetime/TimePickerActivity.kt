package com.example.ecf_app.datetime

import android.content.Intent
import android.os.Bundle
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityTimePickerBinding

class TimePickerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimePickerBinding
    private var timePicker: TimePicker? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimePickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timePicker = binding.TimePickerActivityTimePicker

        timePicker!!.setIs24HourView(true)

        val valeur = intent.getBooleanExtra("valeur",false)
        if (valeur){
            val hour = intent.getStringExtra("hour")
            val minute = intent.getStringExtra("minute")
            timePicker!!.hour = hour!!.toInt()
            timePicker!!.minute = minute!!.toInt()
        }


        binding.TimePickerActivityBtnDone.setOnClickListener {
            // Récupérez l'heure sélectionnée
            val hour = timePicker!!.hour
            val minute = timePicker!!.minute

            // Créez un objet Intent pour renvoyer les informations
            val resultIntent = Intent()
            resultIntent.putExtra("hour", hour)
            resultIntent.putExtra("minute", minute)

            // Définissez le résultat et terminez l'activité
            setResult(RESULT_OK, resultIntent)
            finish()
        }



    }
}