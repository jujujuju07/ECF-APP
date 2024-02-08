package com.example.ecf_app.prescription

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityConsultationPrescriptionBinding
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.ListPrescriptionItem
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.json.Prescription
import com.example.ecf_app.retrofit.ApiAdapteur
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConsultationPrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsultationPrescriptionBinding
    private lateinit var medecin: Medecin
    private lateinit var patient: ListPatientItem
    private lateinit var listprescritptionitem: ListPrescriptionItem
    private lateinit var prescription: Prescription

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultationPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        medecin = intent.getSerializableExtra("objetMedecin") as Medecin
        patient = intent.getSerializableExtra("objetPatient") as ListPatientItem
        listprescritptionitem = intent.getSerializableExtra("objetPrescription") as ListPrescriptionItem


        GlobalScope.launch(Dispatchers.Main){
            info()
        }

        binding.ConsultationPrescriptionActivityButton.setOnClickListener {
            val intent = Intent(it.context, ModifierPrescriptionActivity::class.java)
            intent.putExtra("objetPatient",patient)
            intent.putExtra("objetMedecin",medecin)
            intent.putExtra("objetPrescription",prescription)
            it.context.startActivity(intent)

        }

        binding.ConsultationPrescriptionActivityImageViewHeader.setOnClickListener {
            val intent = baseContext.packageManager.getLaunchIntentForPackage(
                baseContext.packageName
            )
            intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

    }

    suspend fun info(){
        val reponse = ApiAdapteur.apiClient.getPrescription(listprescritptionitem.id)
        if (reponse.isSuccessful && reponse.body() != null){
            prescription = reponse.body()!!
            binding.prescription = reponse.body()
        }
    }


}