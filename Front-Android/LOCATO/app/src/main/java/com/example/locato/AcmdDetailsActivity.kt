package com.example.locato

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.util.GeoPoint


@Suppress("DEPRECATION")
class AcmdDetailsActivity : AppCompatActivity() {
    //el items bch ywaliw yjiw ml entity(enum) category
    private val itemsType = arrayOf("Appartment", "House", "Studio", "Hotel", "Villa")
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
    private lateinit var autoCompleteTxt: AutoCompleteTextView
    private lateinit var adapterItems: ArrayAdapter<String>
    private lateinit var setlocationButton :Button
    private lateinit var selectedCity : String
    private var position: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acmd_details)

        //Toolbar
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Enable the "Up" button
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        //Type
        autoCompleteTxt = findViewById(R.id.auto_complete_txt)
        setlocationButton = findViewById(R.id.setlocationBtn)
        adapterItems = ArrayAdapter(this, R.layout.list_item, itemsType)
        autoCompleteTxt.setAdapter(adapterItems)

        autoCompleteTxt.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position).toString()
            Toast.makeText(applicationContext, "Item: $item", Toast.LENGTH_SHORT).show()
        }

       //city
        autoCompleteTxt = findViewById(R.id.auto_complete_txt1)
        adapterItems = ArrayAdapter(this, R.layout.list_item, itemsCity)
        autoCompleteTxt.setAdapter(adapterItems)
        autoCompleteTxt.dropDownAnchor = autoCompleteTxt.id
        autoCompleteTxt.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()
            //enabled setlocation btn
            setlocationButton.isEnabled = true
            selectedCity = selectedItem
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
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // or navigate to the previous activity
        return true
    }
    fun onUploadButtonClick(view: View) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1) // The "1" is a request code, you can use any unique number
    }






}