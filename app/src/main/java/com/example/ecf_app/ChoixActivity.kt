package com.example.ecf_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecf_app.databinding.ActivityChoixBinding

class ChoixActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChoixBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoixBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}