package com.example.assignmenttopsadvance.find_places;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.assignmenttopsadvance.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;

public class FindPlacesByID extends AppCompatActivity {

    Place place= Place.builder().build();
    final CharSequence name = place.getName();
    final CharSequence address = place.getAddress();
    final LatLng location = place.getLatLng();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_places_by_id);
    }
}