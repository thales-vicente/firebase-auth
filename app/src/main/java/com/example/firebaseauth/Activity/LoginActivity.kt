package com.example.firebaseauth.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseauth.ViewModel.LoginViewModel
import com.example.firebaseauth.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInscrever.setOnClickListener {
            val name = binding.etName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                loginViewModel.register(name, email, password)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.ivFechar.setOnClickListener {
            finish()
        }
        loginViewModel.loginStatus.observe(this, { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        })
        loginViewModel.navigateToHome.observe(this, { navigate ->
            if (navigate) {
                startActivity(Intent(this, DashBoardActivity::class.java))
                loginViewModel.OnNavigateToHomeComplete()
            }
        })
    }

    fun navigateToSingIn(view: View) {
        startActivity(Intent(this, SignInActivity::class.java))
    }
}