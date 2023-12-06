package com.example.locato.Authetication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.locato.R

class LoginActivity : AppCompatActivity() {
    private lateinit var loginUsername: EditText
    private lateinit var loginPassword: EditText
    private lateinit var signupRedirectText: TextView
    private lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        loginUsername = findViewById(R.id.login_username)
        loginPassword = findViewById(R.id.login_password)
        signupRedirectText = findViewById(R.id.signupRedirectText)
        loginButton = findViewById(R.id.login_button)

        signupRedirectText.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener {
            if (!validateUsername() || !validatePassword()) {
                Toast.makeText(this@LoginActivity, "All fields must be filled", Toast.LENGTH_SHORT)
                    .show()
            } else {
                null
            }
        }
    }
        fun validateUsername(): Boolean {
            val valStr = loginUsername.text.toString()
            return if (valStr.isEmpty()) {
                loginUsername.error = "Username cannot be empty"
                false
            } else {
                loginUsername.error = null
                true
            }
        }

         fun validatePassword(): Boolean {
            val valStr = loginPassword.text.toString()
            return if (valStr.isEmpty()) {
                loginPassword.error = "Password cannot be empty"
                false
            } else {
                loginPassword.error = null
                true
            }
        }

    }
