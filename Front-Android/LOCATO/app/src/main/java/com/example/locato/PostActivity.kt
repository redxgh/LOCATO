package com.example.locato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.AdService
import java.io.File

class PostActivity : AppCompatActivity() {

    private lateinit var adTitle: EditText
    private lateinit var adDesc: EditText
    private lateinit var adPrice: EditText
    private lateinit var nextButton: MaterialButton
    private lateinit var adTypeRadioGroup: RadioGroup
    private val baseUrl = "http://192.168.1.19:8081/addAd"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Set up the Toolbar as the ActionBar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the "Up" button
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        adTitle = findViewById(R.id.adTitle)
        adDesc = findViewById(R.id.adDesc)
        adPrice = findViewById(R.id.adPrice)
        nextButton = findViewById(R.id.nextBtn)
        nextButton.setOnClickListener() {
            val intent = Intent(this, AcmdDetailsActivity::class.java)
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

        /*val myVolleyRequest = MyVolleyRequest.getInstance(this)
        myVolleyRequest.addAd(
            "Your Title",
            "Your Description",
            100.0,
            "Your Location",
            100.0,
            2,
            1,
            1,
            listOf(File(""), File("")),
            "Your Type",
            "cat1",
            -1
        ) { response ->
            runOnUiThread {
                if (response != null) {
                    // Handle successful response
                    Toast.makeText(this, "Ad added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    // Handle the case where the request failed or the response is null
                    Toast.makeText(this, "Failed to add ad", Toast.LENGTH_SHORT).show()
                    Log.e("MyVolleyRequest", "Failed to add ad")
                }
            }
        }*/


        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.19:8081/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(AdService::class.java)

        val title = "My Ad".toRequestBody("text/plain".toMediaTypeOrNull())
        val description = "This is my ad description.".toRequestBody("text/plain".toMediaTypeOrNull())
        val price = "1000".toRequestBody("text/plain".toMediaTypeOrNull())
        val location = "New York".toRequestBody("text/plain".toMediaTypeOrNull())
        val surface = "100".toRequestBody("text/plain".toMediaTypeOrNull())
        val rooms = "3".toRequestBody("text/plain".toMediaTypeOrNull())
        val bathrooms = "2".toRequestBody("text/plain".toMediaTypeOrNull())
        val best = "1".toRequestBody("text/plain".toMediaTypeOrNull())


        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "BakiHanma.jpg")
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val imagesArr = MultipartBody.Part.createFormData("imagesArr", file.name, requestFile)

        val type = "Apartment".toRequestBody("text/plain".toMediaTypeOrNull())
        val categoryId = "cat1".toRequestBody("text/plain".toMediaTypeOrNull())
        val gender = "1".toRequestBody("text/plain".toMediaTypeOrNull())
        val call = service.addAd(
            title,
            description,
            price,
            location,
            surface,
            rooms,
            bathrooms,
            best,
            imagesArr,
            type,
            categoryId,
            gender
        )

// Execute the call
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@PostActivity, "Request successful", Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(this@PostActivity, "Request failed", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@PostActivity, "on failure ! ", Toast.LENGTH_SHORT).show()
                Log.e("error", t.message.toString())
            }
        })

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
