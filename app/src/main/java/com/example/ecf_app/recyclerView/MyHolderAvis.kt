package com.example.ecf_app.recyclerView

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.ecf_app.avis.ConsultationAvisActivity
import com.example.ecf_app.databinding.ItemListAvisBinding
import com.example.ecf_app.json.ListAvisItem
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.Medecin

class MyHolderAvis (private val binding: ItemListAvisBinding, private val medecin: Medecin, private val patient: ListPatientItem) : RecyclerView.ViewHolder(binding.root) {

    fun bind(avis: ListAvisItem) {
        binding.avis = avis

        binding.ItemListAvisConstraintLayoutTitre.setOnClickListener{
            val intent = Intent(it.context, ConsultationAvisActivity::class.java)
            intent.putExtra("objetPatient",patient)
            intent.putExtra("objetMedecin",medecin)
            intent.putExtra("objetAvis",avis)
            it.context.startActivity(intent)
        }

    }

}