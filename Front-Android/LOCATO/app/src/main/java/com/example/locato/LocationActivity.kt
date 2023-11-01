package com.example.locato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class LocationActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    private lateinit var marker: Marker
    private lateinit var zoomInButton: Button
    private lateinit var zoomOutButton: Button
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
        zoomInButton = findViewById(R.id.zoomInButton)
        zoomOutButton = findViewById(R.id.zoomOutButton)

        zoomInButton.setOnClickListener {
            mapView.controller.zoomIn()
        }

        zoomOutButton.setOnClickListener {
            mapView.controller.zoomOut()
        }

        marker = Marker(mapView)
        marker.position = selectedCityLocation
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(marker)


        val targetLocation = GeoPoint(37.7749, -122.4194) // Latitude and longitude of the target location
        val targetMarker = Marker(mapView)
        targetMarker.position = targetLocation
        targetMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(targetMarker)

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
        )
        return cityLocations[city]
    }
}