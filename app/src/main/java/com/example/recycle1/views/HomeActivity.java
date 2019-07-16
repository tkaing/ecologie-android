package com.example.recycle1.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.recycle1.R;
import com.example.recycle1.data.model.Course;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback , CourseFragment.CourseActivityListener{

    CourseShowFragment courseShow =new CourseShowFragment();
    @Override
    public void onCourseSent(Course course) {
        courseShow.courseShow = course;
    }

    /** Variable declaration*/
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    private final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION=1;
    public static boolean inHome = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        inHome = true;
        //header side menu information
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        TextView profilename = (TextView) headerview.findViewById(R.id.name);

        //id of user
        profilename.setText("Dennoun");
        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.nav_header);
        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Your code here
                Fragment mapFragment = getSupportFragmentManager().findFragmentById(R.id.map2);
                if(mapFragment !=  null) {
                    getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();
                }


                FragmentTransaction profileFragmentTransaction = getSupportFragmentManager().beginTransaction();
                profileFragmentTransaction.replace(R.id.content_home, new ProfileFragment());

                profileFragmentTransaction.commit();
            }
        });
        if (inHome) {
            setTitle("HomeActivity");

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map2);
            mapFragment.getMapAsync(this);
        }


        // Obtain the Fragment and get menu when it's ready to be used.
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                , drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



    }



    /* MAP Activity */


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

        if(inHome) {

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
                                        Log.e("UserLocation","location  null" );

                                    }
                                }
                            });
                }
            } else {

                // Add a marker in Paris and move the camera
                LatLng paris = new LatLng(48.8534, 2.3488);
                mMap.addMarker(new MarkerOptions().position(paris).title("Marker in Paris"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paris, 17f));
                Log.e("UserLocation","location null" );
        }





        }



    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        Fragment fragmenLogin = null;
        if (id == R.id.login) {
                fragmenLogin = new ConnexionFragment();

        }
        if(fragmenLogin != null) {

            if(inHome) {

                Fragment mapFragment = getSupportFragmentManager().findFragmentById(R.id.map2);

                getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();

            }
            FragmentTransaction fragmentTransactionLog = getSupportFragmentManager().beginTransaction();
            fragmentTransactionLog.replace(R.id.content_home, fragmenLogin);

            fragmentTransactionLog.commit();
            Toast.makeText(this,"login",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    private void displatSelectedScreen (int id) {
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_home:
                id = R.id.content_home;
                break;
            case R.id.nav_profil:
                fragment = new ProfileFragment();
                break;
            case R.id.nav_parcours:
                fragment = new CourseFragment();
                break;
            case R.id.nav_waste:
                fragment = new WasteFragment();
                break;
        }
        if(fragment != null) {

            if(inHome) {
                Log.d("test", "yes");
                Fragment mapFragment = getSupportFragmentManager().findFragmentById(R.id.map2);

                    getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();

            }


            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_home, fragment);

            fragmentTransaction.commit();

            Log.d("frag", this.getTitle().toString() );


        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {

                Intent intent1 = new Intent(this, HomeActivity.class);
                this.startActivity(intent1);

        } else {
            displatSelectedScreen(id);
        }


        return true;
    }



}
