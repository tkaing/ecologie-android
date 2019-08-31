package com.example.recycle1.views;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.recycle1.R;
import com.example.recycle1.data.dto.AssociationDTO;
import com.example.recycle1.data.model.Association;
import com.example.recycle1.data.service.NetworkProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AssociationActivity extends AppCompatActivity {
    @BindView(R.id.recycle_view)
    RecyclerView listeItem;
    private AssociationAdapter associationAdapter;
    String associationPath = "" ;
    List<AssociationDTO> associationList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association);

        NetworkProvider.getInstance().getAssociations(new NetworkProvider.Listner<List<AssociationDTO>>() {
            @Override
            public void onSuccess(List<AssociationDTO> data) {
                Log.d("AssociationActivity", data.toString());

            }

            @Override
            public void onError(Throwable t) {
                Log.d("AssociationActivity",t.toString());
            }
        });
    }


}
