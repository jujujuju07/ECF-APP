package com.example.ecf_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.avis.ListAvisActivity
import com.example.ecf_app.databinding.ActivityChoixBinding
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.prescription.ListPrescriptionActivity

class ChoixActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoixBinding
    private lateinit var medecin: Medecin
    private lateinit var patient: ListPatientItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoixBinding.inflate(layoutInflater)
        setContentView(binding.root)
        medecin = intent.getSerializableExtra("objetMedecin") as Medecin
        patient = intent.getSerializableExtra("objetPatient") as ListPatientItem


        binding.ChoixActivityConstraintLayoutAvis.setOnClickListener {
            val intent = Intent(it.context, ListAvisActivity::class.java)
            lancerActivity(it,intent)
        }
        binding.ChoixActivityConstraintLayoutPrescription.setOnClickListener {
            val intent = Intent(it.context, ListPrescriptionActivity::class.java)
            lancerActivity(it,intent)
        }

        binding.ChoixActivityImageViewHeader.setOnClickListener {
            val intent = baseContext.packageManager.getLaunchIntentForPackage(
                baseContext.packageName
            )
            intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

    }
    fun lancerActivity(it: View, intent: Intent){
        intent.putExtra("objetPatient",patient)
        intent.putExtra("objetMedecin",medecin)
        it.context.startActivity(intent)
    }
}