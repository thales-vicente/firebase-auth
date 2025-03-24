package com.example.firebaseauth.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseauth.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInscrever.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnEntrar.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }
}