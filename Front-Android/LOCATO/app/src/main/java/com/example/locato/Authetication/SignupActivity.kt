package com.example.locato.Authetication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.locato.R

class SignupActivity : AppCompatActivity() {
    private lateinit var signupName: EditText
    private lateinit var signupEmail: EditText
    private lateinit var signupUsername: EditText
    private lateinit var signupPassword: EditText
    private lateinit var signupButton: Button
    private lateinit var loginRedirectText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        signupName = findViewById(R.id.signup_name)
        signupEmail = findViewById(R.id.signup_email)
        signupUsername = findViewById(R.id.signup_username)
        signupPassword = findViewById(R.id.signup_password)
        signupButton = findViewById(R.id.signup_button)
        loginRedirectText = findViewById(R.id.loginRedirectText)


        signupButton.setOnClickListener {
            if (validateFields()) {
                //function add
                Toast.makeText(
                    this@SignupActivity,
                    "You have signed up successfully!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        loginRedirectText.setOnClickListener {
            val intent = Intent(this@SignupActivity, LoginActivity::class.java)
            startActivity(intent)
        }



    }
    private fun validateFields(): Boolean {
        if (signupName.text.isEmpty()) {
            showToast("Name field must be filled")
            return false
        }

        if (signupEmail.text.isEmpty()) {
            showToast("Email field must be filled")
            return false
        }

        if (signupUsername.text.isEmpty()) {
            showToast("Username field must be filled")
            return false
        }

        if (signupPassword.text.isEmpty()) {
            showToast("Password field must be filled")
            return false
        }

        return true
    }

    private fun showToast(message: String) {
        Toast.makeText(this@SignupActivity, message, Toast.LENGTH_SHORT).show()
    }
}
