package com.example.ecf_app.prescription

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.datetime.DatePickerActivity
import com.example.ecf_app.databinding.ActivityAjouterPrescriptionBinding
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.retrofit.ApiAdapteur
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale


class AjouterPrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAjouterPrescriptionBinding
    private lateinit var medecin: Medecin
    private lateinit var patient: ListPatientItem

    private lateinit var TextInputEditText: TextInputEditText
    private val REQUEST_DATE_TIME_PICKER = 1

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAjouterPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        medecin = intent.getSerializableExtra("objetMedecin") as Medecin
        patient = intent.getSerializableExtra("objetPatient") as ListPatientItem

        binding.AjouterPrescriptionActivityButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main){
                ajouter()
            }
        }

        binding.AjouterPrescriptionActivityConstraintLayoutDateDebut.setOnClickListener {
            TextInputEditText = binding.AjouterPrescriptionActivityTextInputEditTextDateDebut
            datetime(it)
        }

        binding.AjouterPrescriptionActivityTextInputEditTextDateDebut.setOnClickListener {
            TextInputEditText = binding.AjouterPrescriptionActivityTextInputEditTextDateDebut
            datetime(it)
        }

        binding.AjouterPrescriptionActivityConstraintLayoutDateFin.setOnClickListener {
            TextInputEditText = binding.AjouterPrescriptionActivityTextInputEditTextDateFin
            datetime(it)
        }

        binding.AjouterPrescriptionActivityTextInputEditTextDateFin.setOnClickListener {
            TextInputEditText = binding.AjouterPrescriptionActivityTextInputEditTextDateFin
            datetime(it)
        }

        binding.AjouterPrescriptionActivityImageViewHeader.setOnClickListener {
            val intent = baseContext.packageManager.getLaunchIntentForPackage(
                baseContext.packageName
            )
            intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }


    }

    private fun datetime(it: View){
        val intent = Intent(
            it.context,
            DatePickerActivity::class.java
        )
        if (TextInputEditText.text?.isEmpty() == false){
            intent.putExtra("valeur",true)

            val sapce = TextInputEditText.text?.split(" ")
            val date = sapce?.get(0)?.split("-")
            val time = sapce?.get(1)?.split(":")

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

    @Deprecated("Deprecated in Java")
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

    suspend fun ajouter(){
        val liste_medicament = binding.AjouterPrescriptionActivityTextInputEditTextListeMedicament.text.toString()
        val posologie = binding.AjouterPrescriptionActivityTextInputEditTextPosologie.text.toString()
        val date_debut = binding.AjouterPrescriptionActivityTextInputEditTextDateDebut.text.toString()
        val date_fin = binding.AjouterPrescriptionActivityTextInputEditTextDateFin.text.toString()
        val patient = patient.id
        var pass = true

        if (liste_medicament.isEmpty()){
            pass = false
            binding.AjouterPrescriptionActivityTextViewErreur.setText("Liste de medicament vide")
        }else if (posologie.isEmpty()) {
            pass = false
            binding.AjouterPrescriptionActivityTextViewErreur.setText("Posologie vide")
        }else if (date_debut.isEmpty()){
            pass = false
            binding.AjouterPrescriptionActivityTextViewErreur.setText("Date de début vide")
        }else if (date_fin.isEmpty()){
            pass = false
            binding.AjouterPrescriptionActivityTextViewErreur.setText("Date de Fin vide")
        }


        if (pass){
            ApiAdapteur.apiClient.setPrescription(liste_medicament,posologie,date_debut,date_fin,patient)
            finish()
        }
    }

}