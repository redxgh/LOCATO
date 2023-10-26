package com.example.locato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var adTitle: EditText
    private lateinit var adDesc: EditText
    private lateinit var adPrice: EditText
    private lateinit var nextButton: MaterialButton
    private lateinit var adTypeRadioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adTitle = findViewById(R.id.adTitle)
        adDesc = findViewById(R.id.adDesc)
        adPrice = findViewById(R.id.adPrice)
        nextButton = findViewById(R.id.nextBtn)
        nextButton.setOnClickListener(){
            val intent = Intent(this,AcmdDetailsActivity::class.java)
            startActivity(intent)
        }
        adTypeRadioGroup = findViewById(R.id.adType)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                checkFormCompletion()
            }
        }
        adTitle.addTextChangedListener(textWatcher)
        adDesc.addTextChangedListener(textWatcher)
        adPrice.addTextChangedListener(textWatcher)
    }

    private fun checkFormCompletion() {
        val isFormComplete = adTitle.text.isNotBlank() && adDesc.text.isNotBlank() && adPrice.text.isNotBlank()
        if(isFormComplete){
            nextButton.isEnabled = isFormComplete
            nextButton.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
        }
        else{
            nextButton.isEnabled = isFormComplete
            nextButton.setBackgroundColor(ContextCompat.getColor(this, R.color.disabled_color))
        }


    }
}
