package com.example.ecf_app.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecf_app.databinding.ItemListPrescriptionBinding
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.ListPrescription
import com.example.ecf_app.json.Medecin

class MyAdapterPrescription (private val listPrescription: ListPrescription, private val medecin: Medecin, private val patient: ListPatientItem) : RecyclerView.Adapter<MyHolderPrescription>() {
    private lateinit var binding: ItemListPrescriptionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderPrescription {
        binding = ItemListPrescriptionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyHolderPrescription(binding,medecin,patient)
    }

    override fun onBindViewHolder(holder: MyHolderPrescription, position: Int) {
        holder.bind(listPrescription.get(position))
    }

    override fun getItemCount(): Int = listPrescription.size


}