package com.example.ecf_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityIdentificationBinding
import com.example.ecf_app.json.Medecin
import com.example.ecf_app.retrofit.ApiAdapteur
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class IdentificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIdentificationBinding
    private lateinit var medecin: Medecin
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.IdentificationActivityButtonSeConnecter.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                val matricule = binding.IdentificationActivityTextInputEditText.text.toString()
                if (matricule.isNotEmpty()){
                    val reponse = connexion(matricule.toInt())
                    if (reponse == 0){
                        println("matricule introuvable")
                        binding.IdentificationActivityTextViewErreure.text = "matricule introuvable"
                    }else if (reponse == 1){
                        println("matricule trouver")
                        val intent = Intent(it.context, ListPatientActivity::class.java)
                        intent.putExtra("objetMedecin",medecin)
                        it.context.startActivity(intent)
                    }else{
                        println("probleme connexion")
                        binding.IdentificationActivityTextViewErreure.text = "probleme connexion"
                    }
                }
                else{
                    println("matricule vide")
                    binding.IdentificationActivityTextViewErreure.text = "matricule vide"
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.IdentificationActivityTextViewErreure.text = ""
        binding.IdentificationActivityTextInputEditText.setText("")
    }


    suspend fun connexion(matricule: Int): Int {
        try {
            println(matricule)
            val reponse = ApiAdapteur.apiClient.getMedecin(matricule)
            return if (reponse.isSuccessful && reponse.body() != null){
                Log.i("reponse",reponse.body().toString())
                medecin = reponse.body()!!
                if (reponse.body()!!.matricule == matricule){
                    1
                }else{
                    0
                }
            }else{
                0
            }
        }catch (e: Exception){
            Log.e("connexion",e.message.toString())
            return 2
        }
    }
}