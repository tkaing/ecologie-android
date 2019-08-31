package com.example.recycle1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.recycle1.data.service.GMailSender;
import com.example.recycle1.views.NewUserFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class Helpers {




    public void Alert (Context context, String title, String message, String btnTitle) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, btnTitle,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }
    public void GoTo (Fragment fragment,FragmentManager fragmentManager ) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_home, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void SendMailPassword (String lastname,String mail,String password ) {

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    //Your code goes here
                    try {
                        GMailSender sender = new GMailSender(
                                "associationecolo@gmail.com", "ecology2019");
                        sender.sendMail("Creation de compte",
                                "Felicitaion M/Mme " + lastname + " votre compte a été crée ! Vous trouverez ci joint vos informations de connexion : \n" +
                                        "identifiant : " + mail + "\n " +
                                        "Mot de passe : " + password +"\n" +
                                        "Esperant vous voir bientot sur un de nos parcours ",
                                "ecologyAssociation@hotmail.com",
                                mail);
                        Log.d("sendmail","that's works !");

                    } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
    public void drawMarker(String location , String title,String id, Float color, GoogleMap mMap, Context context) {

        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses;
        String tag= "";
        if(color ==  BitmapDescriptorFactory.HUE_AZURE ) {
            tag="A" + id;

        } else {
            tag="C" + id;
        }

        try {
            addresses = geocoder.getFromLocationName(location, 1);
            if(addresses.size() > 0) {
                double latitude= addresses.get(0).getLatitude();
                double longitude= addresses.get(0).getLongitude();
                Log.d("latLongLocation",location );
                Log.d("latitudeLongitude",latitude + " " + longitude );
                if (mMap != null) {
                    LatLng gps = new LatLng(latitude, longitude);
                    Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(gps)
                            .title(title)
                            .icon(BitmapDescriptorFactory.defaultMarker(color)));
                    marker.setTag(tag);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void drawMarkerCurrentLocation(Location location,GoogleMap mMap, Context context) {
        if (mMap != null) {
            mMap.clear();
            LatLng gps = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(gps)
                    .title(context.getString(R.string.currentLocation)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gps, 12));
        }

    }

}
