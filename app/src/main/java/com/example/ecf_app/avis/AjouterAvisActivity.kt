package com.example.ecf_app.avis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ecf_app.databinding.ActivityAjouterAvisBinding

class AjouterAvisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAjouterAvisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAjouterAvisBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}