package com.example.dagger_hilt.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dagger_hilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding:ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}