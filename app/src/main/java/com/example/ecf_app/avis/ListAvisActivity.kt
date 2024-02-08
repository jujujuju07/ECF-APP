package com.example.ecf_app.avis

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecf_app.databinding.ActivityListAvisBinding
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.recyclerView.MyAdapterAvis
import com.example.ecf_app.retrofit.ApiAdapteur
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListAvisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListAvisBinding
    private lateinit var medecin: Medecin
    private lateinit var patient: ListPatientItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAvisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        medecin = intent.getSerializableExtra("objetMedecin") as Medecin
        patient = intent.getSerializableExtra("objetPatient") as ListPatientItem
        recyclerView()
        binding.ListAvisActivityImageViewAjouter.setOnClickListener {
            val intent = Intent(it.context, AjouterAvisActivity::class.java)
            intent.putExtra("objetPatient",patient)
            intent.putExtra("objetMedecin",medecin)
            it.context.startActivity(intent)
        }

        binding.ListAvisActivityImageViewHeader.setOnClickListener {
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
                val rep = ApiAdapteur.apiClient.getListAvis(medecin.id)
                if (rep.isSuccessful() && rep.body() != null){
                    val recyclerViewNews = binding.ListAvisActivityRecylerView
                    val newsAdapter = MyAdapterAvis(rep.body()!!,medecin,patient)
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