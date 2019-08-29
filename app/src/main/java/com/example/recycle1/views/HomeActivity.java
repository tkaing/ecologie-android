package com.example.recycle1.views;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import com.example.recycle1.Methodes;
import com.example.recycle1.R;
import com.example.recycle1.data.dto.AssociationDTO;
import com.example.recycle1.data.dto.CourseDTO;
import com.example.recycle1.data.dto.UserDTO;
import com.example.recycle1.data.model.Association;
import com.example.recycle1.data.model.Course;
import com.example.recycle1.data.service.GMailSender;
import com.example.recycle1.data.service.NetworkProvider;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback , CourseFragment.CourseActivityListener {


    Methodes methodes = new Methodes();
    //Test get position
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 10;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;
    public static final  Float associationsMarkercolor = BitmapDescriptorFactory.HUE_AZURE;
    public static final  Float coursesMarkercolor = BitmapDescriptorFactory.HUE_GREEN;





    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                methodes.drawMarkerCurrentLocation(location,mMap,getBaseContext());
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
        getCurrentLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.removeUpdates(mLocationListener);
    }


    private void getCurrentLocation() {
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

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
                    methodes.Alert(this,this.getString(R.string.noGpsAndCoTitle), this.getString(R.string.enableGPSandConxMSG), "Ok");
                }

                if (isGPSEnabled) {
                    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            LOCATION_UPDATE_MIN_TIME, LOCATION_UPDATE_MIN_DISTANCE, mLocationListener);
                    location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                } else {

                           }


            }
        }
        if (location != null) {
            methodes.drawMarkerCurrentLocation(location,mMap,getBaseContext());
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
    Boolean isConnected = false;
    private GoogleMap mMap;
    private final int REQUEST_PERMISSION_ACCESS_FINE_LOCATION = 1;
    public static boolean inHome = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        //Get Session variable to check if the user is connected
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);


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
        inHome = true;
        this.setTitle("Home");




        //header side menu information
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        TextView profilename = (TextView) headerview.findViewById(R.id.name);
        LinearLayout header = (LinearLayout) headerview.findViewById(R.id.nav_header);



        //id of user
        if(isConnected){
            profilename.setText(sharedPreferences.getString("firstname","No Name"));
            headerview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                    Toast.makeText(getApplication(),"Disconnected",Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            headerview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Your code here
                    Fragment mapFragment = getSupportFragmentManager().findFragmentById(R.id.map2);
                    if (mapFragment != null) {
                        getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();
                    }


                    FragmentTransaction profileFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    profileFragmentTransaction.replace(R.id.content_home, new ProfileFragment());

                    profileFragmentTransaction.commit();
                }
            });

        }

        if (inHome) {
            setTitle("Home");

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

        getAssociations();
        getCourses();


    }





    /** METHODS for MAP  */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (inHome) {

            mMap = googleMap;
            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                // Permission is not granted
                // Should we show an explanation?
                // Add a marker in Paris and move the camera
                Log.d("testloc","strange , why i'm priting that");
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


                }
            } else {
                getCurrentLocation();
            }




        } else {

            // Add a marker in Paris and move the camera
            LatLng paris = new LatLng(48.8534, 2.3488);
            mMap.addMarker(new MarkerOptions().position(paris).title("Marker in Paris"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(paris, 17f));
            Log.e("UserLocation", "location null");
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
        if (id == R.id.login) {fragmenLogin = new ConnexionFragment();}

        // redirection vers login
        if (fragmenLogin != null) {

            if (inHome) { HideMap();}


                    Fragment fragment = new ConnexionFragment();

            if(!fragment.isVisible()) {
                FragmentTransaction fragmentTransactionLog = getSupportFragmentManager().beginTransaction();
                fragmentTransactionLog.replace(R.id.content_home, fragmenLogin,"login");
                fragmentTransactionLog.commit();
                Toast.makeText(this, "login", Toast.LENGTH_LONG).show();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = HomeActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }

    private void HideMap() {
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

            if (inHome) {
                Log.d("test", "yes");
                Fragment mapFragment = getSupportFragmentManager().findFragmentById(R.id.map2);

                getSupportFragmentManager().beginTransaction().hide(mapFragment).commit();

            }


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

                        methodes.drawMarker(association.getLocation(),association.getName(),associationsMarkercolor,mMap,getBaseContext());




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


                        methodes.drawMarker(completeAdress,course.getName(),coursesMarkercolor,mMap,getBaseContext());
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
}
