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

import com.example.recycle1.Methodes;
import com.example.recycle1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.recycle1.views.HomeActivity.inHome;


public class ConfirmNewUserFragment extends Fragment {


    Methodes methodes = new Methodes();
    @BindView(R.id.text) TextView text;
    @BindView(R.id.sendbtn) Button sendBtn;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.congratulation));
        inHome = false;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_new_user, container, false);

        ButterKnife.bind(this,view);

        sendBtn.setOnClickListener(view1 -> {
            methodes.Alert(getContext(),"Email",getContext().getString(R.string.sendAutherMail),"Ok");
            text.setText(getContext().getString(R.string.TextReSendMail));
        });
        return view;
    }



}
