package com.example.recycle1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Parcours extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parcours);
        Toast.makeText(getBaseContext(), "Reason can not be blank", Toast.LENGTH_SHORT).show();

    }
}