package com.example.locato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class LocationActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    private lateinit var marker: Marker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        Configuration.getInstance().load(applicationContext, getPreferences(MODE_PRIVATE))

        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        val mapController = mapView.controller
        val tunisiaCenter = GeoPoint(33.8869, 9.5375) // Latitude and longitude of Tunisia
        mapController.setCenter(tunisiaCenter)
        mapController.setZoom(8.0) // Adjust the zoom level as per your requirement

        // Inside onCreate method, after setting the center and zoom level
        marker = Marker(mapView)
        marker.position = tunisiaCenter
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        mapView.overlays.add(marker)
        mapView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_UP -> {
                    val projectedPoint = mapView.projection.fromPixels(event.x.toInt(), event.y.toInt())
                    marker.position = projectedPoint as GeoPoint?
                    mapView.invalidate()
                    Toast.makeText(
                        this,
                        "Latitude: ${projectedPoint.latitude}, Longitude: ${projectedPoint.longitude}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                    // Handle multi-touch events for zooming
                    mapView.onTouchEvent(event)
                }
            }
            true
        }

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