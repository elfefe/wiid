package com.elfefe.coffeejoin.controllers.fragments

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.elfefe.coffeejoin.R
import com.elfefe.coffeejoin.models.User
import com.elfefe.coffeejoin.models.credential.AccessRule
import com.elfefe.coffeejoin.models.credential.Credentials
import com.elfefe.coffeejoin.mvvm.ViewModelFactory
import com.elfefe.coffeejoin.mvvm.viewmodel.ApiViewModel
import com.elfefe.coffeejoin.mvvm.viewmodel.OltpViewModel
import com.elfefe.coffeejoin.oltp.model.ChannelCommunity
import com.elfefe.coffeejoin.utility.log
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_maps.*
import java.io.IOException
import java.util.*

class MapsFragment : Fragment(), OnMapReadyCallback {
    val geocoder by lazy { Geocoder(context) }
    val oltpViewModel by viewModels<OltpViewModel> { ViewModelFactory.INSTANCE }
    val apiViewModel by viewModels<ApiViewModel> { ViewModelFactory.INSTANCE }

    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_maps_mapView.onCreate(savedInstanceState)
        fragment_maps_mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        map.setOnMapClickListener {
            geocoder.getFromLocation(it.latitude, it.longitude, 1)?.let { addresses ->
                fragment_maps_search.setText(addresses[0].locality)
            }
        }
    }

    override fun onResume() {
        fragment_maps_mapView.onResume()
        super.onResume()
        fragment_maps_search.addTextChangedListener(object : TextWatcher {
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
                var addresses: List<Address> =
                    ArrayList()
                if (fragment_maps_search.text.toString() != "") {
                    try {
                        geocoder.getFromLocationName(
                            fragment_maps_search.text.toString().toLowerCase(),
                            1
                        )?.let {
                            addresses = it
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                if (addresses.isNotEmpty()) {
                    val localisation = LatLng(addresses[0].latitude, addresses[0].longitude)
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(localisation, 10f))
                    saveChannel(
                        name = addresses[0].locality,
                        latitude = addresses[0].latitude,
                        longitude = addresses[0].longitude
                    )
                }
            }
        })

        oltpViewModel.getAllChannels().observe(viewLifecycleOwner, Observer {
            log("CHAANNELS ${it.size}")
        })

        apiViewModel.queryConsumerValidation(Credentials(
            listOf(
                AccessRule("POST", "/*"),
                AccessRule("GET", "/*")
            )
            ,""
        )).observe(viewLifecycleOwner, Observer {
            log("Consumer Validation: $it")

            apiViewModel.queryContainerContent().observe(viewLifecycleOwner, Observer {content ->
                log("Container content: $content")
            })
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        fragment_maps_mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        fragment_maps_mapView.onLowMemory()
    }

    companion object {
        fun newInstance(): Fragment {
            return MapsFragment()
        }
    }

    private fun saveChannel(
        name: String,
        latitude: Double,
        longitude: Double
    ) {
        oltpViewModel.getChannelByName(name).observe(viewLifecycleOwner, Observer {
            it.run {
                val channelCommunity: ChannelCommunity
                if (this != null){
                    this.name = name
                    this.latitude = latitude
                    this.longitude = longitude
                    authorIds.add(User.INFO.id)
                    channelCommunity = this
                } else {
                    channelCommunity = ChannelCommunity(
                        name,
                        latitude,
                        longitude,
                        arrayListOf(User.INFO.id)
                    )
                }

                log(channelCommunity.name)

                User.INFO.communityChannel = channelCommunity
                oltpViewModel.sendChannelCommunity(channelCommunity)
            }
        })
    }
}