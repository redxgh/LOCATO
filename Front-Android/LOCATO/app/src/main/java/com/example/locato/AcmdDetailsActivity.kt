package com.example.locato

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.button.MaterialButton
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.osmdroid.util.GeoPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.AdService
import java.io.File


@Suppress("DEPRECATION")
class AcmdDetailsActivity : AppCompatActivity() {

    //el items bch ywaliw yjiw ml entity(enum) category
    private val itemsCategory = arrayOf("cat1","cat2")
    private val itemsType = arrayOf("type1","type2","type3")
    private val itemsCity = arrayOf(
        "Tunis",
        "Sfax" ,
        "Bizerte" ,
        "Sousse",
        "Gabes" ,
        "Kairouan" ,
        "Gafsa" ,
        "Monastir" ,
        "Hammamet" ,
        "Nabeul" ,
        "Mahdia" ,
        "Kebili" ,
        "Medenine" ,
        "Tataouine" ,
        "Beja" ,
        "Jendouba" ,
        "El Kef" ,
        "Siliana" ,
        "Zaghouan" ,
        "Tozeur" ,
    )
    private lateinit var categorySelect: AutoCompleteTextView
    private lateinit var typeSelect: AutoCompleteTextView

    private lateinit var locationSelect: AutoCompleteTextView

    private lateinit var adapterItems: ArrayAdapter<String>

    private lateinit var setlocationButton :Button
    private lateinit var selectedCity : String
    private lateinit var EditTextRooms :EditText
    private lateinit var EditTextBath :EditText
    private lateinit var EditTextSur :EditText

    private var position: String? = null

    private lateinit var nextButton: MaterialButton
    private lateinit var selectedImageUri: Uri
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acmd_details)


        //retrieve data from previous activity


        //Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the "Up" button
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        lateinit var adCategory:String
        lateinit var adType: String
        lateinit var adLocation:String


        //----------------ACC Category --------------

        //Category
        categorySelect = findViewById(R.id.categorySelect)

        adapterItems = ArrayAdapter(this, R.layout.list_item, itemsCategory)
        categorySelect.setAdapter(adapterItems)

        categorySelect.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position).toString()
            adCategory = item
            Toast.makeText(applicationContext, "Item: $item", Toast.LENGTH_SHORT).show()
        }
        //----------------ACC TYpe --------------

        //Type
        typeSelect = findViewById(R.id.typeSelect)

        adapterItems = ArrayAdapter(this, R.layout.list_item, itemsType)
        typeSelect.setAdapter(adapterItems)

        typeSelect.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position).toString()
            adType = item
            Toast.makeText(applicationContext, "Item: $item", Toast.LENGTH_SHORT).show()
        }
        //----------------ACC DETAILS --------------
        //rooms
        EditTextRooms = findViewById(R.id.accRooms)
        val adRooms = EditTextRooms.text
        //bath
        EditTextBath = findViewById(R.id.accBath)
        val adBath = EditTextBath.text
        //surface
        EditTextSur = findViewById(R.id.accSur)
        val adSurface = EditTextSur.text


        //----------------ACC City --------------

        //city
        setlocationButton = findViewById(R.id.setlocationBtn)
        locationSelect = findViewById(R.id.locationSelect)
        adapterItems = ArrayAdapter(this, R.layout.list_item, itemsCity)
        locationSelect.setAdapter(adapterItems)
        locationSelect.dropDownAnchor = locationSelect.id
        locationSelect.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            //enabled setlocation btn
            setlocationButton.isEnabled = true
            selectedCity = selectedItem
            adLocation = selectedItem
        }

        //location map
        setlocationButton.setOnClickListener(){
            val intent = Intent(this,LocationActivity::class.java)
            intent.putExtra("selectedCity", selectedCity)
            startActivity(intent)
        }

        val latitude = intent.getStringExtra("Latitude")
        val longitude = intent.getStringExtra("Longitude")
        if (latitude != null && longitude != null) {
            val geoPoint = GeoPoint(latitude.toDouble(), longitude.toDouble())
            position= geoPoint.toString()
            setlocationButton.setText(position)
            Log.d("GeoPoint", geoPoint.toString())
        }
     //filled all form condtion missing !

       //-----------------------Post request ---------------------------
        //nextButton
        nextButton = findViewById(R.id.nextBtn)
        nextButton.setOnClickListener() {
            val adTitle : CharSequence? = intent.getCharSequenceExtra("adTitle")
            val adDesc: CharSequence? = intent.getCharSequenceExtra("adDesc")
            val adPrice : CharSequence?= intent.getCharSequenceExtra("adPrice")
            val adGender : String?= intent.getStringExtra("adGender")
            Log.d("adTitle", adTitle.toString())
            Log.d("adDesc", adDesc.toString())
            Log.d("adPrice", adPrice.toString())
            Log.d("adGender", adGender.toString())
            Log.d("adCategory", adCategory)
            Log.d("adType", adType)
            Log.d("adLocation", adLocation)
            Log.d("adRooms", adRooms.toString())
            Log.d("adBath", adBath.toString())
            Log.d("adSurface", adSurface.toString())
            val retrofit = Retrofit.Builder()
<<<<<<< HEAD
                .baseUrl("http://192.168.1.12:8081/")
=======
                .baseUrl("http://192.168.1.15:8081/")
>>>>>>> 935685b3d887276576dd8083e2f682789530e403
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(AdService::class.java)
            val title = adTitle.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val description =  adDesc.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val price = adPrice.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val location = adLocation.toRequestBody("text/plain".toMediaTypeOrNull())
            val surface = adSurface.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val rooms = adRooms.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val bathrooms = adRooms.toString().toRequestBody("text/plain".toMediaTypeOrNull())
            val best = "1".toRequestBody("text/plain".toMediaTypeOrNull())


            //val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "BakiHanma.jpg")
            val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = getFileFromUri(contentResolver, selectedImageUri, externalFilesDir ?: filesDir)
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
                        Toast.makeText(this@AcmdDetailsActivity, "Request successful", Toast.LENGTH_SHORT).show()

                    } else {

                        Toast.makeText(this@AcmdDetailsActivity, "Request failed", Toast.LENGTH_SHORT).show()

                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(this@AcmdDetailsActivity, "on failure ! ", Toast.LENGTH_SHORT).show()
                    Log.e("error", t.message.toString())
                }
            })
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // or navigate to the previous activity
        return true
    }

    fun onUploadButtonClick(view:View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            data?.data?.let { selectedImageUri = it }
            // Now you can use this URI to get the image
        }
    }
    private fun getFileFromUri(contentResolver: ContentResolver, uri: Uri, directory: File): File {
        // Get the file extension from the content resolver
        val extension = contentResolver.getType(uri)?.substringAfter('/') ?: "jpg"

        val file = File(directory, "prefix_${System.currentTimeMillis()}.$extension")
        file.outputStream().use { outputStream ->
            contentResolver.openInputStream(uri)?.copyTo(outputStream)
        }
        return file
    }



}