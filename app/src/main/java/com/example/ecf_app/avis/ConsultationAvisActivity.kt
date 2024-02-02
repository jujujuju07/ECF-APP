package com.example.ecf_app.avis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityConsultationAvisBinding

class ConsultationAvisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityConsultationAvisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultationAvisBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}