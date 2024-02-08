package com.example.ecf_app.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecf_app.databinding.ItemListAvisBinding
import com.example.ecf_app.databinding.ItemListPatientBinding
import com.example.ecf_app.json.ListAvis
import com.example.ecf_app.json.ListPatient
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.Medecin

class MyAdapterAvis (private val listPatient: ListAvis, private val medecin: Medecin, private val patient: ListPatientItem) : RecyclerView.Adapter<MyHolderAvis>() {
    private lateinit var binding: ItemListAvisBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderAvis {
        binding = ItemListAvisBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyHolderAvis(binding,medecin,patient)
    }

    override fun onBindViewHolder(holder: MyHolderAvis, position: Int) {
        holder.bind(listPatient.get(position))
    }

    override fun getItemCount(): Int = listPatient.size


}