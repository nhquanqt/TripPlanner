package com.example.tripplannew;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tripplannew.data.webservice.Place;
import com.example.tripplannew.viewmodels.PlaceOfInterestViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.Executor;

public class FindPlacesFragment extends Fragment implements OnMapReadyCallback {

    private PlaceOfInterestViewModel mPlaceOfInterestViewModel;

    private GoogleMap mGoogleMap;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private LatLng mMyLocation;

    private int locationRequestCode = 1000;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find_places, container, false);

        mPlaceOfInterestViewModel = new ViewModelProvider(getActivity()).get(PlaceOfInterestViewModel.class);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, locationRequestCode);
        }
        else
        {
            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(getActivity(), location -> {
                if (location != null) {
                    findNearbyPlaces(location);
                }
            });
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mGoogleMap = googleMap;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mFusedLocationProviderClient.getLastLocation().addOnSuccessListener((Executor) this, location -> {
                        if (location != null) {
                            findNearbyPlaces(location);
                        }
                    });
                } else {
                    Toast.makeText(getActivity(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void findNearbyPlaces(Location location)
    {
        mMyLocation = new LatLng(location.getLatitude(), location.getLongitude());

        mGoogleMap.addMarker(new MarkerOptions().position(mMyLocation).title("My Position"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mMyLocation, 16));

        String placeType = mPlaceOfInterestViewModel.getPlaceType();

        mPlaceOfInterestViewModel.getNearbyPlaces(mMyLocation.latitude, mMyLocation.longitude, placeType, 20000)
                .observe(getActivity(), placeList -> {
                    for(int i = 0; i < placeList.size(); ++i)
                    {
                        Place place = placeList.get(i);
                        LatLng latLng = new LatLng(place.getLat(), place.getLon());
                        mGoogleMap.addMarker(
                                new MarkerOptions()
                                        .position(latLng)
                                        .title("" + place.getName())
                        );
                    }
                });;
    }
}