package com.example.ecf_app.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecf_app.databinding.ItemListPatientBinding
import com.example.ecf_app.json.ListPatient
import com.example.ecf_app.json.Medecin

class MyAdapterPatient (private val listPatient: ListPatient, private val medecin: Medecin) : RecyclerView.Adapter<MyHolderPatient>() {
    private lateinit var binding: ItemListPatientBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolderPatient {
        binding = ItemListPatientBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyHolderPatient(binding,medecin)
    }

    override fun onBindViewHolder(holder: MyHolderPatient, position: Int) {
        holder.bind(listPatient.get(position))
    }

    override fun getItemCount(): Int = listPatient.size


}