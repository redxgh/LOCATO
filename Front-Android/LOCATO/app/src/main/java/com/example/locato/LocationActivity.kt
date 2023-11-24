package com.example.locato

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.osmdroid.api.IGeoPoint
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class LocationActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    private lateinit var marker: Marker
    private lateinit var setlocationBtn: Button
    private lateinit var geoPoint:GeoPoint
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        Configuration.getInstance().load(applicationContext, getPreferences(MODE_PRIVATE))
        val selectedCity = intent.getStringExtra("selectedCity")


        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true) // Enable multi-touch gestures

        val mapController = mapView.controller

        val selectedCityLocation = getCityLocation(selectedCity)
        if (selectedCityLocation != null) {
            mapController.setCenter(selectedCityLocation)
            mapController.setZoom(12.0) // Adjust the zoom level as per your requirement
        }
        setlocationBtn = findViewById(R.id.setlocationBtn)



        marker = Marker(mapView)
        marker.position = selectedCityLocation
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(marker)



        mapView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                geoPoint = mapView.projection.fromPixels(event.x.toInt(), event.y.toInt()) as GeoPoint
                setNewMarker(geoPoint)

                Log.d("GeoPoint", geoPoint.toString())
                setlocationBtn.isEnabled=true
            }
            false
        }

        setlocationBtn.setOnClickListener {
            val intent = Intent(this, AcmdDetailsActivity::class.java)
            intent.putExtra("Latitude", geoPoint.latitude.toString())
            intent.putExtra("Longitude", geoPoint.longitude.toString())
            intent.putExtra("name","habib")
            startActivity(intent)
            finish()

            Log.d("setlocationBtn", "setlocationBtn clicked and GeoPoint set")
        }



    }

    private fun setNewMarker(geoPoint: IGeoPoint) {
        mapView.overlays.clear()
        marker.position = geoPoint as GeoPoint?
        mapView.overlays.add(marker)
        mapView.invalidate()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    private fun getCityLocation(city: String?): GeoPoint? {

        val cityLocations = mapOf(
            "Tunis" to GeoPoint(36.8065, 10.1815),
            "Sfax" to GeoPoint(34.7397, 10.7604),
            "Bizerte" to GeoPoint(37.2744, 9.8733),
            "Sousse" to GeoPoint(35.8254, 10.6367),
            "Gabes" to GeoPoint(33.8819, 10.0982),
            "Kairouan" to GeoPoint(35.6784, 10.0963),
            "Gafsa" to GeoPoint(34.4250, 8.7842),
            "Monastir" to GeoPoint(35.7833, 10.8333),
            "Hammamet" to  GeoPoint(36.4000, 10.6167),
            "Nabeul" to GeoPoint(36.4564, 10.7376),
            "Mahdia" to GeoPoint(35.5049, 11.0465),
            "Kebili" to GeoPoint(33.7041, 8.9690),
            "Medenine" to GeoPoint(33.3450, 10.5083),
            "Tataouine" to GeoPoint(32.9297, 10.4518),
            "Beja" to GeoPoint(36.7303, 9.1914),
            "Jendouba" to GeoPoint(36.5011, 8.7802),
            "El Kef" to GeoPoint(36.1826, 8.7147),
            "Siliana" to GeoPoint(36.0833, 9.3833),
            "Zaghouan" to GeoPoint(36.4029, 10.1429),
            "Tozeur" to GeoPoint(33.9197, 8.1335)
        )
        return cityLocations[city]
    }
}