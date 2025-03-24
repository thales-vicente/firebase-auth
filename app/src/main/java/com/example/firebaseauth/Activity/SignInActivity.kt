package com.example.firebaseauth.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firebaseauth.ViewModel.SingInViewModel
import com.example.firebaseauth.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val singInViewModel: SingInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                singInViewModel.signIn(email, password)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
        binding.ivFechar.setOnClickListener {
            finish()
        }
        singInViewModel.signInStatus.observe(this, { status ->
            Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
        })
        singInViewModel.navigateToHome.observe(this, { navigate ->
            if (navigate) {
                startActivity(Intent(this, DashBoardActivity::class.java))
                singInViewModel.OnNavigateToHomeComplete()
            }
        })
    }
}