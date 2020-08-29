package com.elfefe.coffeejoin.controllers.fragments

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.elfefe.coffeejoin.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import java.io.IOException
import java.util.*

class MapsFragment : Fragment(), OnMapReadyCallback {
    private var map: GoogleMap? = null
    private var mapView: MapView? = null
    private var search: EditText? = null
    private var context: Context? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)
        mapView = view.findViewById(R.id.fragment_maps_mapView)
        search = view.findViewById(R.id.fragment_maps_search)
        context = activity
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    override fun onResume() {
        mapView!!.onResume()
        super.onResume()
        search!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                var locations: List<Address> =
                    ArrayList()
                if (search!!.text.toString() != "") {
                    val geocoder = Geocoder(context)
                    try {
                        if (geocoder.getFromLocationName(
                                search!!.text.toString().toLowerCase(),
                                1
                            ) != null
                        ) {
                            locations = geocoder.getFromLocationName(
                                search!!.text.toString().toLowerCase(), 1
                            )
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (locations != null) {
                    if (locations.size > 0) {
                        val location = locations[0]
                        val localisation =
                            LatLng(location.latitude, location.longitude)
                        map!!.animateCamera(CameraUpdateFactory.newLatLngZoom(localisation, 10f))
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView!!.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView!!.onLowMemory()
    }

    companion object {
        fun newInstance(): Fragment {
            return MapsFragment()
        }
    }
}