package com.example.ecf_app.prescription

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityModifierPrescriptionBinding

class ModifierPrescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModifierPrescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifierPrescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}