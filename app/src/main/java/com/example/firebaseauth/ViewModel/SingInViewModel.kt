package com.example.firebaseauth.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SingInViewModel : ViewModel() {
    private val _singInStatus = MutableLiveData<String>()
    val signInStatus: LiveData<String> get() = _singInStatus

    private val _navigateToHome = MutableLiveData<Boolean>()
    val navigateToHome: LiveData<Boolean> get() = _navigateToHome

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _singInStatus.value = "Login bem-sucedido!"
                    _navigateToHome.value = true
                } else {
                    _singInStatus.value = "Falha no login: ${task.exception?.message}"
                }
            }
    }

    fun OnNavigateToHomeComplete(){
        _navigateToHome.value = false
    }
}