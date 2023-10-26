package com.example.locato

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class AcmdDetailsActivity : AppCompatActivity() {
    //el items bch ywaliw yjiw ml entity(enum) category
    private val items = arrayOf("Appartment", "House", "Studio", "Hotel", "Villa")
    private lateinit var autoCompleteTxt: AutoCompleteTextView
    private lateinit var adapterItems: ArrayAdapter<String>
    private lateinit var setlocationButton :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acmd_details)
        //Type
        autoCompleteTxt = findViewById(R.id.auto_complete_txt)
        adapterItems = ArrayAdapter(this, R.layout.list_item, items)
        autoCompleteTxt.setAdapter(adapterItems)
        setlocationButton = findViewById(R.id.setlocationBtn)
        setlocationButton.setOnClickListener(){
            val intent = Intent(this,LocationActivity::class.java)
            startActivity(intent)
        }
        autoCompleteTxt.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position).toString()
            Toast.makeText(applicationContext, "Item: $item", Toast.LENGTH_SHORT).show()
        }



    }


}