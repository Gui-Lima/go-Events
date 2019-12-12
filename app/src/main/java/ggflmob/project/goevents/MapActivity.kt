package ggflmob.project.goevents

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MapActivity : FragmentActivity(){

    private lateinit var map : MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map)
        map = findViewById(R.id.map)
        configureMap()
        Log.d("DEBUG", " AAAAAAAAAA")
    }


    override fun onResume() {
        super.onResume()
        if(::map.isInitialized) map.onResume()
    }

    override fun onPause(){
        super.onPause()
        if(::map.isInitialized)  map.onPause()
    }

    private fun configureMap(){
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)
        map.minZoomLevel = 16.0
        map.maxZoomLevel = 19.0
        val mapController = map.controller
        mapController.setZoom(9.5)
        val startPoint = GeoPoint(-8.036887, -34.904318)
        mapController.setCenter(startPoint)
    }
}