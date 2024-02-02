package com.example.ecf_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ecf_app.databinding.ActivityListPatientBinding

class ListPatientActivity : AppCompatActivity() {
    private lateinit var binbing: ActivityListPatientBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binbing = ActivityListPatientBinding.inflate(layoutInflater)
        setContentView(binbing.root)
    }
}