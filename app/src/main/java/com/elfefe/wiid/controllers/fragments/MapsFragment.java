package com.elfefe.wiid.controllers.fragments;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.elfefe.wiid.R;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

        private GoogleMap map;
        private MapView mapView;
        private EditText search;
        private Context context;

    public static Fragment newInstance(){
        return new MapsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        mapView = view.findViewById(R.id.fragment_maps_mapView);
        search = view.findViewById(R.id.fragment_maps_search);

        context = getActivity();

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                List<Address> locations = new ArrayList<>();
                if (!search.getText().toString().equals("")) {
                    Geocoder geocoder = new Geocoder(context);
                    try {
                        if(geocoder.getFromLocationName(search.getText().toString().toLowerCase(), 1) != null) {
                            locations = geocoder.getFromLocationName(search.getText().toString().toLowerCase(), 1);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (locations != null) {
                    if (locations.size() > 0) {
                        Address location = locations.get(0);
                        LatLng localisation = new LatLng(location.getLatitude(), location.getLongitude());
                        map.animateCamera(CameraUpdateFactory.newLatLngZoom(localisation, 10f));
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}