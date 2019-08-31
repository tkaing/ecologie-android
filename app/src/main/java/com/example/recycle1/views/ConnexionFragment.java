package com.example.recycle1.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.recycle1.Helpers;
import com.example.recycle1.R;
import com.example.recycle1.SeizureControl;
import com.example.recycle1.data.dto.UserDTO;
import com.example.recycle1.data.model.User;
import com.example.recycle1.data.service.NetworkProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConnexionFragment extends Fragment {

    Helpers helpers = new Helpers();
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
                helpers.GoTo(new NewUserFragment(), getFragmentManager());


            }
        });

        connexion_btn.setOnClickListener(view1 -> {
            //UserDTO userDTO = new UserDTO("a@live.fr","HYj1VEZJ");
            SeizureControl seizureControl = new SeizureControl();
            Helpers helpers = new Helpers();

            if (seizureControl.isNull(login_ev.getText().toString())) {
                helpers.Alert(getContext(),getContext().getString(R.string.missingInformation), getContext().getString(R.string.emptyMailSC) , "Ok");

            }
            /*
            if (seizureControl.valiemail(login_ev.getText().toString())) {
                helpers.Alert(getContext(),getContext().getString(R.string.invalid_Information),
                        getContext().getString(R.string.invalidMailSC) , "Ok");

            }
            */
            if (seizureControl.isNull(password_ev.getText().toString())) {
                helpers.Alert(getContext(),getContext().getString(R.string.missingInformation),
                        getContext().getString(R.string.PasswordSC) , "Ok");

            }
            /*
            Test for password valide
            if (seizureControl.validPasswor(password_ev.getText().toString())) {
                helpers.Alert(getContext(),"Inforation invalide", "Le nom n'est composé que de lettre " , "Ok");
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
