package com.example.recycle1.views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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

import com.example.recycle1.Helpers;
import com.example.recycle1.R;
import com.example.recycle1.data.dto.AssociationDTO;
import com.example.recycle1.data.model.Course;
import com.example.recycle1.data.service.NetworkProvider;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback , CourseFragment.CourseActivityListener, GoogleMap.OnMarkerClickListener {


    Helpers helpers = new Helpers();
    Boolean isConnected = false;
    HomeActivity.CourseActivityListener courseListener;




    //get position conf
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;
    public static final  Float associationsMarkercolor = BitmapDescriptorFactory.HUE_AZURE;
    public static final  Float coursesMarkercolor = BitmapDescriptorFactory.HUE_GREEN;







    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                helpers.drawMarkerCurrentLocation(location,mMap,getBaseContext());
                mLocationManager.removeUpdates(mLocationListener);
            } else {
                Log.e("Location","Location is null");
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    private LocationManager mLocationManager;



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.removeUpdates(mLocationListener);
    }


    private void getCurrentLocation() {
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        String tag = "L";

        Location location = null;
        if (!(isGPSEnabled || isNetworkEnabled)) {
            buildAlertMessageNoGps();

        }
        else {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                if (isNetworkEnabled) {

                    mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                            LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                    location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);


                } else {
                    helpers.Alert(this,this.getString(R.string.noGpsAndCoTitle), this.getString(R.string.enableGPSandConxMSG), "Ok");
                }

                if (isGPSEnabled) {
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                    location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                }


            }
        }
        if (location != null) {
            helpers.drawMarkerCurrentLocation(location,mMap,getBaseContext());
        }
    }




    CourseShowFragment courseShow = new CourseShowFragment();

    @Override
    public void onCourseSent(Course course) {
        courseShow.courseShow = course;
    }

    /**
     * Variable declaration
     */

    private GoogleMap mMap;
    private final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1;
    public static boolean inHome = false;





    PlacesClient placesClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Session variable to check if the user is connected
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        setTitle("Home");


        if(sharedPreferences.getBoolean("isConnected",true)){

            isConnected=true;
            Toast.makeText(this,this.getString(R.string.SuccefulLogin),Toast.LENGTH_SHORT).show();
        } else if (sharedPreferences.getBoolean("isConnected",false)) {

            isConnected=false;
        }

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getCurrentLocation();

        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //header side menu information
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        TextView profilename = (TextView) headerview.findViewById(R.id.name);
        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.nav_header);
        Context context = this;
        headerview.setOnClickListener(v -> {
            // Your code here

            if (isConnected) {
                HideHomeFragment();
                helpers.GoTo(new ProfileFragment(),getSupportFragmentManager());
            } else {
                helpers.Alert(context, context.getString(R.string.notLogin), context.getString(R.string.notLoginMsg), "Ok");
            }
        });



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);



        // Obtain the Fragment and get menu when it's ready to be used.
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this
                , drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        getAssociations();
        getCourses();
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        String apiPLaceKey = this.getString(R.string.apiPlaceKey);
        if(!Places.isInitialized()) {
            Places.initialize(getApplicationContext(),apiPLaceKey);
        }

        placesClient = Places.createClient(this);

        /* setPlaceFields is used for the purpose when they autocompleteplaces returns the places and if you click on any placed than what
         field will you gonna get after clicking on that speciic place */
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG,Place.Field.NAME));
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                final LatLng latLng = place.getLatLng();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f));

                Log.i("PlaceApi","onPlaceSelected: " + latLng.latitude + "\n" + latLng.longitude);
            }

            @Override
            public void onError(@NonNull Status status) {

            }
        });

    }

    /** METHODS for MAP  */

    @Override
    public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;
            mMap.setOnMarkerClickListener(this);

            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted
                buildAlertMessageNoGps();

                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                    buildAlertMessageNoGps();
                } else {
                    // No explanation needed; request the permission
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_PERMISSION_ACCESS_FINE_LOCATION);
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
        /*Handle action bar item clicks here. The action bar will handle clicks on the HomeActivity/Up button, so long
        as you specify a parent activity in AndroidManifest.xml.*/
        int id = item.getItemId();
        if (id == R.id.login) {
            if(isConnected){
                //Get Session variable to check if the user is connected
                SharedPreferences sharedPreferences = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
                // Your code here
                sharedPreferences
                        .edit()
                        .putBoolean("isConnected", false)
                        .putString("email","")
                        .putString("firstname","")
                        .putString("lastname","")
                        .putString("birthdate","")
                        .putString("createdAt","")
                        .apply();
                isConnected=false;
                Toast.makeText(this,this.getString(R.string.deconnect),Toast.LENGTH_LONG).show();


            }
            //Getting current Fragment
            if (getFragmentManager().getBackStackEntryCount() > 1) {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_connexion);
                if (f instanceof ConnexionFragment) {
                    // Do something
                    return super.onOptionsItemSelected(item);
                } else {
                    HideHomeFragment();
                }
            }
            helpers.GoTo(new ConnexionFragment(), getSupportFragmentManager());
        }

        return super.onOptionsItemSelected(item);
    }


    private void HideHomeFragment() {
        Fragment autoCompleteFragment = getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        getSupportFragmentManager().beginTransaction().hide(autoCompleteFragment).commit();
        Fragment mapFragment = getSupportFragmentManager().findFragmentById(R.id.map2);
        getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();
    }


    private void displatSelectedScreen(int id) {
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
        if (fragment != null) {
            HideHomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_home, fragment);

            fragmentTransaction.commit();

            Log.d("frag", this.getTitle().toString());


        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    /** METHODS for Association  */

    public void getAssociations() {

        NetworkProvider.getInstance().getAssociations(new NetworkProvider.Listner<List<AssociationDTO>>() {
            @Override
            public void onSuccess(List<AssociationDTO> data) {
                if(Geocoder.isPresent()) {
                    for( AssociationDTO association: data){


                        helpers.drawMarker(association.getLocation(),association.getName(), association.getId(),associationsMarkercolor,mMap,getBaseContext());




                    }

                }
            }

            @Override
            public void onError(Throwable t) {
                Log.e("HomeActivity","Error Call API Get Associations :" + t.toString());
            }
        });
    }
    /** METHODS for Courses  */

    public void getCourses() {

        NetworkProvider.getInstance().getCourses(new NetworkProvider.Listner<List<Course>>() {
            @Override
            public void onSuccess(List<Course> data) {
                if(Geocoder.isPresent()) {
                    for( Course course: data){
                        String completeAdress = course.getAddress() + " " + course.getZip() + " " + course.getCity();


                        helpers.drawMarker(completeAdress,course.getName(),course.getId(),coursesMarkercolor,mMap,getBaseContext());
                    }

                }
            }

            @Override
            public void onError(Throwable t) {
                Log.e("HomeActivity","Error Call API Get Associations :" + t.toString());
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(this.getString(R.string.noGps))
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                .setNegativeButton(this.getString(R.string.no), (dialog, id) -> dialog.cancel());
        final AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
       /* Log.d("testTagOTH", marker.getTitle());
        if (marker.getTag() != null) {
            char tag = marker.getTag().toString().charAt(0);
            String id = marker.getTag().toString().substring(1);


            if (tag == 'A') {
                Log.d("testTagAsso", marker.getTitle() + " " + id);
                return false;

            }
            else if (tag == 'C') {

                Log.d("testTagCourse", marker.getTitle() + " " + id);

                NetworkProvider networkProvider = new NetworkProvider();
                networkProvider.getCourseCriteria(id, new NetworkProvider.Listner<Course>() {
                    @Override
                    public void onSuccess(Course data) {
                        Course course = data;
                        courseListener.onCourseSent(course);
                        Fragment fragment = new CourseShowFragment();
                        ((CourseShowFragment) fragment).setCourseShow(course);

                        helpers.GoTo(fragment, getSupportFragmentManager());

                    }

                    @Override
                    public void onError(Throwable t) {
                       Toast.makeText(getBaseContext(), "Please wait few seconds ...",Toast.LENGTH_SHORT).show();
                       Log.e("GotoCourseFromHome" , "Error : " + t.toString())
;
                    }
                });
                return false;


            }
            else if (tag == 'L') {
                Log.d("testTagCurLocation", marker.getTitle() + " " + id);
                return false;

            }

        }*/

        return false;
    }
    public interface CourseActivityListener {
        void onCourseSent(Course course);
    }
}





