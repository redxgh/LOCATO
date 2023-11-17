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


class AcmdDetailsActivity : AppCompatActivity() {
    //el items bch ywaliw yjiw ml entity(enum) category
    private val itemsType = arrayOf("Appartment", "House", "Studio", "Hotel", "Villa")
    private val itemsCity = arrayOf("Beja",
        "Ben Arous",
        "Bizerte",
        "Gabes",
        "Gafsa",
        "Jendouba",
        "Kairouan",
        "Kasserine",
        "Kebili",
        "L'Ariana",
        "La Manouba",
        "Le Kef",
        "Mahdia",
        "Medenine",
        "Monastir",
        "Nabeul",
        "Sfax",
        "Sidi Bouzid",
        "Siliana",
        "Sousse",
        "Tataouine",
        "Tozeur",
        "Tunis",
        "Zaghouan")
    private lateinit var autoCompleteTxt: AutoCompleteTextView
    private lateinit var adapterItems: ArrayAdapter<String>
    private lateinit var setlocationButton :Button
    private lateinit var selectedCity : String

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

    // Override the onActivityResult method to handle the selected image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            val selectedImage = data.data
            //bch nestaamlo selectedImage ka result ( naarash list kfe )
        }
    }

}