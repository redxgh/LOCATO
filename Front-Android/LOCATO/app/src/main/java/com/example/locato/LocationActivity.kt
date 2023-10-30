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
        installSplashScreen()
        Configuration.getInstance().load(applicationContext, getPreferences(MODE_PRIVATE))

        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true) // Enable multi-touch gestures

        val mapController = mapView.controller
        val tunisiaCenter = GeoPoint(33.8869, 9.5375) // Latitude and longitude of Tunisia
        mapController.setCenter(tunisiaCenter)
        mapController.setZoom(8.0) // Adjust the zoom level as per your requirement

        zoomInButton = findViewById(R.id.zoomInButton)
        zoomOutButton = findViewById(R.id.zoomOutButton)

        zoomInButton.setOnClickListener {
            mapView.controller.zoomIn()
        }

        zoomOutButton.setOnClickListener {
            mapView.controller.zoomOut()
        }

        marker = Marker(mapView)
        marker.position = tunisiaCenter
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(marker)
    // Add the following code to mark a place on the map
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
}