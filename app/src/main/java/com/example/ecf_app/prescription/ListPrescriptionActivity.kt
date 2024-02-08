package com.example.ecf_app.prescription

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecf_app.databinding.ActivityListPrescriptionBinding
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.recyclerView.MyAdapterPrescription
import com.example.ecf_app.retrofit.ApiAdapteur
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListPrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListPrescriptionBinding
    private lateinit var medecin: Medecin
    private lateinit var patient: ListPatientItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        medecin = intent.getSerializableExtra("objetMedecin") as Medecin
        patient = intent.getSerializableExtra("objetPatient") as ListPatientItem
        recyclerView()
        binding.ListPrescriptionActivityImageViewAjouter.setOnClickListener {
            val intent = Intent(it.context, AjouterPrescriptionActivity::class.java)
            intent.putExtra("objetPatient",patient)
            intent.putExtra("objetMedecin",medecin)
            it.context.startActivity(intent)
        }
        binding.ListPrescriptionActivityImageViewHeader.setOnClickListener {
            val intent = baseContext.packageManager.getLaunchIntentForPackage(
                baseContext.packageName
            )
            intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        recyclerView()
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun recyclerView(){
        GlobalScope.launch(Dispatchers.Main){
            try {
                val rep = ApiAdapteur.apiClient.getListPrescription(medecin.id)
                if (rep.isSuccessful() && rep.body() != null){
                    val recyclerViewNews = binding.ListPrescriptionActivityRecylerView
                    val newsAdapter = MyAdapterPrescription(rep.body()!!,medecin,patient)
                    recyclerViewNews.adapter = newsAdapter
                    val layoutManager = LinearLayoutManager(parent, LinearLayoutManager.VERTICAL, false)
                    recyclerViewNews.layoutManager = layoutManager
                }
            }catch (e: Exception){
                println(e.message)
            }
        }

    }

}