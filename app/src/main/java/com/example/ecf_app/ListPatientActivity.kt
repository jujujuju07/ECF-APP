package com.example.ecf_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecf_app.databinding.ActivityListPatientBinding
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.recyclerView.MyAdapterPatient
import com.example.ecf_app.retrofit.ApiAdapteur
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ListPatientActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListPatientBinding
    private lateinit var medecin: Medecin
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        medecin = intent.getSerializableExtra("objetMedecin") as Medecin

        recyclerView()
    }

    override fun onResume() {
        super.onResume()
        recyclerView()
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun recyclerView(){
        GlobalScope.launch(Dispatchers.Main){
            try {
                val rep = ApiAdapteur.apiClient.getListPatient(medecin.id)
                if (rep.isSuccessful() && rep.body() != null){
                    val recyclerViewNews = binding.ListPatientActivityRecylerView
                    val newsAdapter = MyAdapterPatient(rep.body()!!,medecin)
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