package com.example.recycle1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Connexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        TextView id, mdp;
        id = (TextView) findViewById(R.id.textView);
        mdp = (TextView) findViewById(R.id.textView2);
        id.setText("Identifiant");
        mdp.setText("Mot de passe");
        Button button= (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page1();
            }
        });

    }


    public  void  page1 () {
        Intent Intent = new Intent(this, MainActivity.class);
        startActivity(Intent);

    }
}
