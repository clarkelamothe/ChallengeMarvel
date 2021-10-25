package com.clarkelamothe.intermedia.ui.auth

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clarkelamothe.intermedia.R
import com.clarkelamothe.intermedia.databinding.ActivityLoginBinding
import com.clarkelamothe.intermedia.ui.MainActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var callbackManager: CallbackManager

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
        binding.btnLogin.setOnClickListener { getUserData() }
        binding.btnFb.setOnClickListener { loginWithFacebook() }
    }

    private fun getUserData() {
//        val email = binding.etEmail.text.toString()
//        val password = binding.etPassword.text.toString()
        val email = "test@test.com"
        val password = "test@123"
        if (email.isNotEmpty() && password.isNotEmpty()) {
            authUser(email, password)
        } else Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
    }

    private fun authUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            checkResult(it.isSuccessful)
        }
    }

    private fun checkResult(isSuccess: Boolean) {
        if (isSuccess) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
        }
    }

    private fun loginWithFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, setOf("email"))
        LoginManager.getInstance().registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    Log.d(ContentValues.TAG, "facebook:onSuccess:$result")
                    handleFacebookAccessToken(result.accessToken)
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }

                override fun onCancel() {
                    Log.d(ContentValues.TAG, "facebook:onCancel")
                }

                override fun onError(error: FacebookException) {
                    Log.d(ContentValues.TAG, "facebook:onError", error)
                    Toast.makeText(
                        baseContext, R.string.facebook_auth_error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(ContentValues.TAG, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    Log.d(ContentValues.TAG, "signInWithCredential:success")
                    auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext, R.string.facebook_auth_error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}