package com.example.recycle1.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recycle1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnexionFragment extends Fragment {
    String login = "user";
    String password = "user";
    TextView loginTv;
    TextView passwordTv;
    Button connect;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.Connexion);
        loginTv = getActivity().findViewById(R.id.login);
        passwordTv =view.findViewById(R.id.mdp);
        connect = view.findViewById(R.id.connect);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginTv.getText() == login && password == passwordTv.getText()) {
                    NavigationView navigationView = (NavigationView) view.findViewById(R.id.login);
                    View header = navigationView.getHeaderView(0);
                    TextView username = (TextView) header.findViewById(R.id.name);
                     username.setText("Thierry");

                } else {

                Toast.makeText(view.getContext(), "Identifiants incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.connexion_fragment, container, false);
    }
}
