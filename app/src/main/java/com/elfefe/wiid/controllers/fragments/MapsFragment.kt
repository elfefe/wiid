package com.elfefe.wiid.controllers.fragments

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.elfefe.wiid.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng

class MapsFragment : Fragment(), OnMapReadyCallback {

    lateinit var map: GoogleMap
    lateinit var mapView: MapView
    lateinit var search: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_maps, container, false)

        mapView = view.findViewById(R.id.fragment_maps_mapView)
        search = view.findViewById(R.id.fragment_maps_search)
        mapView.onCreate(savedInstanceState)

        mapView.getMapAsync(this)

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
        search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                    if (search.text.toString().toLowerCase() == "amsterdam") {

                        val location: Address = Geocoder(context).getFromLocationName("Amsterdam", 1).get(0)
                        val localisation = LatLng(location.latitude, location.longitude)
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(localisation, 10f))
                    }
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    companion object {

        fun newInstance(): MapsFragment {
            return MapsFragment()
        }
    }
}