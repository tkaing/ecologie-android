package com.example.recycle1.views;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recycle1.Methodes;
import com.example.recycle1.R;
import com.example.recycle1.SeizureControl;
import com.example.recycle1.data.dto.LoginDTO;
import com.example.recycle1.data.dto.UserDTO;
import com.example.recycle1.data.model.Course;
import com.example.recycle1.data.model.User;
import com.example.recycle1.data.service.NetworkProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnexionFragment extends Fragment {

    Methodes methodes = new Methodes();
    //Bind view
    @BindView(R.id.login_ev) TextView login_ev;
    @BindView(R.id.password_ev) TextView password_ev;
    @BindView(R.id.connexion_btn) Button connexion_btn;
    @BindView(R.id.createNewUser) TextView createNewUser;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.connection);
        User user = new User();





    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.connexion_fragment, container, false);

        ButterKnife.bind(this,view);
        createNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                methodes.GoTo(new NewUserFragment(), getFragmentManager());


            }
        });

        connexion_btn.setOnClickListener(view1 -> {
            //UserDTO userDTO = new UserDTO("a@live.fr","HYj1VEZJ");
            SeizureControl seizureControl = new SeizureControl();
            Methodes methodes = new Methodes();

            if (seizureControl.isNull(login_ev.getText().toString())) {
                methodes.Alert(getContext(),getContext().getString(R.string.missingInformation), getContext().getString(R.string.emptyMailSC) , "Ok");

            }
            /*
            if (seizureControl.valiemail(login_ev.getText().toString())) {
                methodes.Alert(getContext(),getContext().getString(R.string.invalid_Information),
                        getContext().getString(R.string.invalidMailSC) , "Ok");

            }
            */
            if (seizureControl.isNull(password_ev.getText().toString())) {
                methodes.Alert(getContext(),getContext().getString(R.string.missingInformation),
                        getContext().getString(R.string.PasswordSC) , "Ok");

            }
            /*
            Test for password valide
            if (seizureControl.validPasswor(password_ev.getText().toString())) {
                methodes.Alert(getContext(),"Inforation invalide", "Le nom n'est composé que de lettre " , "Ok");
            }
            */
            UserDTO userDTO = new UserDTO(login_ev.getText().toString(),password_ev.getText().toString());
            NetworkProvider.getInstance().Login(userDTO, view1.getContext());

        });


        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
