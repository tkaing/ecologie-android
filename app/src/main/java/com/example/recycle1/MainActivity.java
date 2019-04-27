package com.example.recycle1;

import android.Manifest;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.recycle1.R.id;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {




    /** MAP Activity */
    private FusedLocationProviderClient fusedLocationClient;
    private final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.connexion:
                Intent intent1 = new Intent(MainActivity.this, Connexion.class);
                this.startActivity(intent1);
                return true;
            case R.id.Parcours:
                Intent intent2 = new Intent(MainActivity.this, Parcours.class);
                this.startActivity(intent2);
                return true;
            case R.id.profil:
                Intent intent3 = new Intent(MainActivity.this, Profil.class);
                this.startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }



    private GoogleMap mMap;
    private MapFragment map;


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            // Add a marker in Paris and move the camera
            LatLng paris = new LatLng(48.8534, 2.3488);
            mMap.addMarker(new MarkerOptions().position(paris).title("Marker in Paris"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paris, 17f));
            Log.d("TestPermission","Permission is not granted" );

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSION_ACCESS_FINE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.

                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {

                                    //Convert Location to LatLng
                                    LatLng newLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                                    // Logic to handle location object
                                    mMap.addMarker(new MarkerOptions().position(newLatLng).title("Marker in Position"));
                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLatLng, 17f));

                                } else {
                                    // Add a marker in Paris and move the camera
                                    LatLng paris = new LatLng(48.8534, 2.3488);
                                    mMap.addMarker(new MarkerOptions().position(paris).title("Marker in Paris"));
                                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paris, 17f));
                                    Log.d("TestLocationNull","location  null" );

                                }
                            }
                        });
            }
        } else {

                                // Add a marker in Paris and move the camera
                                LatLng paris = new LatLng(48.8534, 2.3488);
                                mMap.addMarker(new MarkerOptions().position(paris).title("Marker in Paris"));
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paris, 17f));
                                Log.d("TestLocationNull","location null" );




        }



    }


}
