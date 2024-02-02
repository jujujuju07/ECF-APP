package com.example.ecf_app.prescription

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityConsultationPrescriptionBinding

class ConsultationPrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsultationPrescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultationPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}