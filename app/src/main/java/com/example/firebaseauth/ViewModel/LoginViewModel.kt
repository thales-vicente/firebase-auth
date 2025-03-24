package com.example.firebaseauth.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> get() = _loginStatus

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> get() = _navigateToHome

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginStatus.value = "Login bem-sucedido!"
                    _navigateToHome.value = true
                } else {
                    _loginStatus.value = "Falha no login: ${task.exception?.message}"
                }
            }
    }

    fun register(name: String, email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginStatus.value = "Registro bem-sucedido"
                    _navigateToHome.value = true
                } else {
                    _loginStatus.value = "Falha no registro: ${task.exception?.message}"
                }
            }
    }

    fun OnNavigateToHomeComplete() {
        _navigateToHome.value = false
    }
}