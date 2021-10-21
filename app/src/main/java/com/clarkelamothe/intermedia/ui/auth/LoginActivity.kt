package com.clarkelamothe.intermedia.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clarkelamothe.intermedia.databinding.ActivityLoginBinding
import com.clarkelamothe.intermedia.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
    }

    private fun initData() {
        auth = FirebaseAuth.getInstance()
        clickListener()
    }

    private fun clickListener() {
        binding.createAccount.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
            finish()
        }
        binding.btnLogin.setOnClickListener {
            getUserData()
        }
    }

    private fun getUserData() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty()) {
            authUser(email, password)
        } else {
            Toast.makeText(this, "Oops! There are some missing inputs", Toast.LENGTH_SHORT).show()
        }
    }

    private fun authUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            checkResult(it.isSuccessful)
        }
    }

    private fun checkResult(isSuccess: Boolean) {
        if (isSuccess) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Authentication failed...", Toast.LENGTH_SHORT).show()
        }
    }
}