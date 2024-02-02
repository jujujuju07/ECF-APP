package com.example.ecf_app.prescription

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityAjouterPrescriptionBinding

class AjouterPrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAjouterPrescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAjouterPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}