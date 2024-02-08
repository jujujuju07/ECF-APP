package com.example.ecf_app.recyclerView

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.ecf_app.databinding.ItemListPrescriptionBinding
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.ListPrescriptionItem
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.prescription.ConsultationPrescriptionActivity

class MyHolderPrescription (private val binding: ItemListPrescriptionBinding, private val medecin: Medecin, private val patient: ListPatientItem) : RecyclerView.ViewHolder(binding.root) {

    fun bind(prescription: ListPrescriptionItem) {
        binding.prescription = prescription

        binding.ItemListPrescriptionConstraintLayoutTitre.setOnClickListener{
            val intent = Intent(it.context, ConsultationPrescriptionActivity::class.java)
            intent.putExtra("objetPatient",patient)
            intent.putExtra("objetMedecin",medecin)
            intent.putExtra("objetPrescription",prescription)
            it.context.startActivity(intent)
        }

    }

}