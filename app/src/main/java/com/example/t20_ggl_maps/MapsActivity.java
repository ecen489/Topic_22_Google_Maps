package com.example.t20_ggl_maps;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng loc = new LatLng(30.5610, -96.3628);
        setMyMap(loc, "CLL");
    }


    public void click1(View view) {
        LatLng loc = new LatLng(30.5610, -96.3628);
        setMyMap(loc, "CLL");
    }

    public void click2(View view) {

        LatLng loc = new LatLng(37.6213, -122.3790);
        setMyMap(loc, "SFO");

    }

    public void setMyMap(LatLng myLatLng, String name) {
        mMap.addMarker(new MarkerOptions().position(myLatLng).title(name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
    }


    public void click3(View view) {
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
    }

    public void click4(View view) {
        mMap.animateCamera(CameraUpdateFactory.zoomOut());
    }

    public void clickLoc(View view) {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        else {

            Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc != null) {
                double myLat = loc.getLatitude();
                double myLng = loc.getLongitude();
                LatLng locn = new LatLng(myLat, myLng);
                setMyMap(locn, "Me");

            }
        }

    }
}
