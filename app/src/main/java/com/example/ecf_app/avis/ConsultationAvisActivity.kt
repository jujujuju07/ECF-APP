package com.example.ecf_app.avis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityConsultationAvisBinding
import com.example.ecf_app.json.ListAvisItem
import com.example.ecf_app.json.ListPatientItem
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.retrofit.ApiAdapteur
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ConsultationAvisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsultationAvisBinding
    private lateinit var medecin: Medecin
    private lateinit var patient: ListPatientItem
    private lateinit var listavisitem: ListAvisItem

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultationAvisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        medecin = intent.getSerializableExtra("objetMedecin") as Medecin
        patient = intent.getSerializableExtra("objetPatient") as ListPatientItem
        listavisitem = intent.getSerializableExtra("objetAvis") as ListAvisItem

        GlobalScope.launch(Dispatchers.Main){
            info()
            binding.medecin = medecin
        }
    }

    suspend fun info(){
        val reponseavis = ApiAdapteur.apiClient.getAvis(listavisitem.id)
        if (reponseavis.isSuccessful && reponseavis.body() != null){
            binding.avis = reponseavis.body()
        }
    }
}