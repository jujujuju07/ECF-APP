package com.example.ecf_app.avis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecf_app.R
import com.example.ecf_app.databinding.ActivityListAvisBinding

class ListAvisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListAvisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListAvisBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}