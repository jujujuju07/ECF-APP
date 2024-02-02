package com.example.ecf_app.prescription

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityListPrescriptionBinding

class ListPrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListPrescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}