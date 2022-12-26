package com.lucasginard.pelispedia.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucasginard.pelispedia.databinding.ActivityMainBinding
import com.lucasginard.pelispedia.login.signUp.SignUpFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragment()
    }

    fun setupFragment(){
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, SignUpFragment())
            .commit()
    }
}