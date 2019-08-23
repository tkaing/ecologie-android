package com.example.recycle1.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.recycle1.R;
import com.example.recycle1.data.dto.UserDTO;
import com.example.recycle1.data.model.User;
import com.example.recycle1.data.service.NetworkProvider;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewUserFragment extends Fragment {
    @BindView(R.id.firstname_ev) TextView firstname_ev;
    @BindView(R.id.lastname_ev) TextView lastname_ev;
    @BindView(R.id.adresse_ev) TextView adresse_ev;
    @BindView(R.id.birthdate_ev) TextView birthdate_ev;
    @BindView(R.id.email_ev) TextView email_ev;
    @BindView(R.id.phone_ev) TextView phone_ev;
    @BindView(R.id.button) Button button;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Inscription");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.new_user_fragment, container, false);

        ButterKnife.bind(this,view);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    UserDTO userDTO = new UserDTO(
                            email_ev.getText().toString(),
                        firstname_ev.getText().toString(),
                        lastname_ev.getText().toString(),
                            "1559127315.510997",
                        phone_ev.getText().toString(),
                        adresse_ev.getText().toString(),
                            "1559127315.510997");

                NetworkProvider.getInstance().putUser(userDTO);

            }
        });

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

}
