package com.example.ecf_app.prescription

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityModifierPrescriptionBinding
import com.example.ecf_app.datetime.DatePickerActivity
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.json.Prescription
import com.example.ecf_app.retrofit.ApiAdapteur
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale

class ModifierPrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModifierPrescriptionBinding
    private lateinit var medecin: Medecin
    private lateinit var patient: ListPatientItem
    private lateinit var prescription: Prescription

    private lateinit var TextInputEditText: TextInputEditText
    private val REQUEST_DATE_TIME_PICKER = 1


    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifierPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        medecin = intent.getSerializableExtra("objetMedecin") as Medecin
        patient = intent.getSerializableExtra("objetPatient") as ListPatientItem
        prescription = intent.getSerializableExtra("objetPrescription") as Prescription


        GlobalScope.launch(Dispatchers.Main){
            info()
        }

        binding.ModifierPrescriptionActivityButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main){
                modifier()
            }
        }

        binding.ModifierPrescriptionActivityConstraintLayoutDateDebut.setOnClickListener {
            TextInputEditText = binding.ModifierPrescriptionActivityTextInputEditTextDateDebut
            datetime(it)
        }

        binding.ModifierPrescriptionActivityTextInputEditTextDateDebut.setOnClickListener {
            TextInputEditText = binding.ModifierPrescriptionActivityTextInputEditTextDateDebut
            datetime(it)
        }

        binding.ModifierPrescriptionActivityConstraintLayoutDateFin.setOnClickListener {
            TextInputEditText = binding.ModifierPrescriptionActivityTextInputEditTextDateFin
            datetime(it)
        }


        binding.ModifierPrescriptionActivityTextInputEditTextDateFin.setOnClickListener {
            TextInputEditText = binding.ModifierPrescriptionActivityTextInputEditTextDateFin
            datetime(it)
        }


    }

    private fun datetime(it: View){
        val intent = Intent(
            it.context,
            DatePickerActivity::class.java
        )
        if (TextInputEditText.text?.isEmpty() == false){
            intent.putExtra("valeur",true)

            var sapce = TextInputEditText.text?.split(" ")
            var date = sapce?.get(0)?.split("-")
            var time = sapce?.get(1)?.split(":")

            intent.putExtra("year", date?.get(0))
            intent.putExtra("month", date?.get(1))
            intent.putExtra("day", date?.get(2))
            intent.putExtra("hour", time?.get(0))
            intent.putExtra("minute", time?.get(1))
        }else{
            intent.putExtra("valeur",false)
        }
        startActivityForResult(intent, REQUEST_DATE_TIME_PICKER)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_DATE_TIME_PICKER && resultCode == RESULT_OK) {
            // Récupérez les informations renvoyées par DatePickerActivity
            val year = data?.getIntExtra("year", 0)
            val month = data?.getIntExtra("month", 0)
            val day = data?.getIntExtra("day", 0)
            val hour = data?.getIntExtra("hour", 0)
            val minute = data?.getIntExtra("minute", 0)

            val selectedDateTime = String.format(
                Locale.getDefault(),
                "%04d-%02d-%02d %02d:%02d", year, month?.plus(1) , day, hour, minute
            )
            TextInputEditText.setText(selectedDateTime)
        }
    }


    suspend fun info(){
        val reponse = ApiAdapteur.apiClient.getPrescription(prescription.id)
        if (reponse.isSuccessful && reponse.body() != null){
            prescription = reponse.body()!!
            binding.prescription = reponse.body()
        }
    }

    suspend fun modifier(){
        val id = prescription.id
        val liste_medicament = binding.ModifierPrescriptionActivityTextInputEditTextListeMedicament.text.toString()
        val posologie = binding.ModifierPrescriptionActivityTextInputEditTextPosologie.text.toString()
        val date_debut = binding.ModifierPrescriptionActivityTextInputEditTextDateDebut.text.toString()
        val date_fin = binding.ModifierPrescriptionActivityTextInputEditTextDateFin.text.toString()
        val patient = prescription.patient
        var pass = true

        if (liste_medicament.isEmpty()){
            pass = false
            binding.ModifierPrescriptionActivityTextViewErreur.text = "Liste de medicament vide"
        }else if (posologie.isEmpty()) {
            pass = false
            binding.ModifierPrescriptionActivityTextViewErreur.text = "Posologie vide"
        }else if (date_debut.isEmpty()){
            pass = false
            binding.ModifierPrescriptionActivityTextViewErreur.text = "Date de début vide"
        }else if (date_fin.isEmpty()){
            pass = false
            binding.ModifierPrescriptionActivityTextViewErreur.text = "Date de Fin vide"
        }

        if (pass){
            ApiAdapteur.apiClient.getModifierPrescription(id,liste_medicament,posologie,date_debut,date_fin,patient)
            finish()
        }
    }

}