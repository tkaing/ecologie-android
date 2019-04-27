package com.example.recycle1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.recycle1.R;


public class Profil extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);
        RecyclerView myView = (RecyclerView) findViewById(R.id.recyclerview);
    
    }


}