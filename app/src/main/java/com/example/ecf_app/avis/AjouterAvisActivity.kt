package com.example.ecf_app.avis

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityAjouterAvisBinding
import com.example.ecf_app.datetime.DatePickerActivity
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.retrofit.ApiAdapteur
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Locale

class AjouterAvisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAjouterAvisBinding
    private lateinit var medecin: Medecin
    private lateinit var patient: ListPatientItem

    private lateinit var TextInputEditText: TextInputEditText
    private val REQUEST_DATE_TIME_PICKER = 1

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAjouterAvisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        medecin = intent.getSerializableExtra("objetMedecin") as Medecin
        patient = intent.getSerializableExtra("objetPatient") as ListPatientItem

        binding.AjouterAvisActivityButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main){
                ajouter()
            }
        }
        binding.AjouterAvisActivityConstraintLayoutDate.setOnClickListener {
            TextInputEditText = binding.AjouterAvisActivityTextInputEditTextDate
            datetime(it)
        }

        binding.AjouterAvisActivityTextInputEditTextDate.setOnClickListener {
            TextInputEditText = binding.AjouterAvisActivityTextInputEditTextDate
            datetime(it)
        }

        binding.AjouterAvisActivityImageViewHeader.setOnClickListener {
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
        val TitreAvis = binding.AjouterAvisActivityTextInputEditTextTitreAvis.text.toString()
        val Date = binding.AjouterAvisActivityTextInputEditTextDate.text.toString()
        val Description = binding.AjouterAvisActivityTextInputEditTextDescription.text.toString()
        val NomPrenomMedecin = binding.AjouterAvisActivityTextInputEditTextNomPrenomMedecin.text.toString()
        val patient = patient.id
        var pass = true

        if (TitreAvis.isEmpty()){
            pass = false
            binding.AjouterAvisActivityTextViewErreur.text = "Liste de medicament vide"
        }else if (Date.isEmpty()) {
            pass = false
            binding.AjouterAvisActivityTextViewErreur.text = "Posologie vide"
        }else if (Description.isEmpty()){
            pass = false
            binding.AjouterAvisActivityTextViewErreur.text = "Date de début vide"
        }else if (NomPrenomMedecin.isEmpty()){
            pass = false
            binding.AjouterAvisActivityTextViewErreur.text = "Date de Fin vide"
        }


        if (pass){
            ApiAdapteur.apiClient.setAvis(TitreAvis,Date,Description,NomPrenomMedecin,patient)
            finish()
        }
    }

}