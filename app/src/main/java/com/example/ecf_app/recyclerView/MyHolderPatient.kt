package com.example.ecf_app.recyclerView

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.ecf_app.ChoixActivity
import com.example.ecf_app.databinding.ItemListPatientBinding
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.Medecin

class MyHolderPatient (private val binding: ItemListPatientBinding, private val medecin: Medecin) : RecyclerView.ViewHolder(binding.root) {

    fun bind(patient: ListPatientItem) {
        binding.patient = patient

        binding.ItemListPatientConstraintLayoutTitre.setOnClickListener{
            val intent = Intent(it.context, ChoixActivity::class.java)
            intent.putExtra("objetPatient",patient)
            intent.putExtra("objetMedecin",medecin)
            it.context.startActivity(intent)
        }

    }

}