package com.clarkelamothe.intermedia.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clarkelamothe.intermedia.R
import com.clarkelamothe.intermedia.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        initData()
    }

    private fun initData() {
        auth = FirebaseAuth.getInstance()
        clickListener()
    }

    private fun clickListener() {
        binding.apply {
            btnRegister.setOnClickListener {
                createUser()
            }
            haveAccount.setOnClickListener {
                startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            }
        }
    }

    private fun createUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
            if (password == confirmPassword) {
                saveUser(email, password)
            } else {
                Toast.makeText(this, getString(R.string.password_error), Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this, getString(R.string.missing_inputs_error), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun saveUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            checkResult(it.isSuccessful)
        }
    }

    private fun checkResult(isSuccess: Boolean) {
        if (isSuccess) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, getString(R.string.error_creating_user), Toast.LENGTH_SHORT).show()
        }
    }
}