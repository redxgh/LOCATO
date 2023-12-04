package com.example.locato

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class EditAdActivity: AppCompatActivity() {
    private lateinit var adTitle: EditText
    private lateinit var adDesc: EditText
    private lateinit var adPrice: EditText
    private lateinit var nextButton: MaterialButton
    private lateinit var adTypeRadioGroup: RadioGroup
    private lateinit var ida: String
    private var prc : Double = 0.0
    private lateinit var ttl: String
    private lateinit var dsc: String
    private var gen : Int = 0
    private var bst : Int = 0
    private lateinit var loc : String
    private var bth : Int = 0
    private var rooms : Int = 0
    private var surf : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ida = intent.getStringExtra("id")!!
        prc = intent.getDoubleExtra("price",0.0)
        ttl = intent.getStringExtra("title")!!
        dsc = intent.getStringExtra("desc")!!
        gen = intent.getIntExtra("gender",-1)
        bst = intent.getIntExtra("best",0)
        loc  = intent.getStringExtra("location")!!
        bth = intent.getIntExtra("bathrooms",0)
        rooms = intent.getIntExtra("rooms",0)
        surf = intent.getDoubleExtra("surface",0.0)
        Log.d("TEST_TAG", ida)
        // Set up the Toolbar as the ActionBar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        // Enable the "Up" button
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        adTitle = findViewById(R.id.adTitle)
        adDesc = findViewById(R.id.adDesc)
        adPrice = findViewById(R.id.adPrice)
        adTitle.setText(ttl)
        adDesc.setText(dsc)
        adPrice.setText(prc.toString())
        //displaying gender radio group if its colocation ad
        val adTypeRadioGroup = findViewById<RadioGroup>(R.id.adType)
        val adGenderRadioGroup = findViewById<RadioGroup>(R.id.adGender)
        val genderTitle = findViewById<TextView>(R.id.GenderTitle)
        var adGender = "-1"
        adTypeRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.renting -> {
                    adGenderRadioGroup.visibility = View.GONE
                    genderTitle.visibility = View.GONE

                }
                R.id.colocation -> {
                    adGenderRadioGroup.visibility = View.VISIBLE
                    genderTitle.visibility = View.VISIBLE
                }
            }
        }

        adGenderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.female -> {
                    adGender = "1"
                }
                R.id.male -> {
                    adGender = "0"
                }
            }
        }


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
        nextButton = findViewById(R.id.nextBtn)
        nextButton.setOnClickListener() {

            val intent = Intent(this, AcmdDetailsEditActivity::class.java)

            intent.putExtra("adTitle", adTitle.text)
            intent.putExtra("adDesc", adDesc.text)
            intent.putExtra("adPrice", adPrice.text)
            intent.putExtra("adGender",adGender)
            intent.putExtra("id",ida)
            intent.putExtra("rooms",rooms)
            intent.putExtra("bathrooms",bth)
            intent.putExtra("surface",surf)
            intent.putExtra("location",loc)
            startActivity(intent)
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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