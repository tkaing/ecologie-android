package com.example.recycle1.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recycle1.R;
import com.example.recycle1.data.model.Waste;
import com.example.recycle1.data.model.WasteType;

import java.util.List;

import static com.example.recycle1.views.HomeActivity.inHome;

public class WasteFragment extends Fragment{
    RecyclerView listeWaste;
    private WasteAdapter wasteAdapter;
    WasteType wasteType ;
   List<Waste> wasteList;

    Waste waste;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.waste);
        inHome = false;
        listeWaste = view.findViewById(R.id.recycle_view_waste);

        initRecyclerView();
        loadData();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.waste_fragmenet, container, false);
    }

    private void initRecyclerView() {
        listeWaste.setLayoutManager(new LinearLayoutManager(getActivity()));
        wasteAdapter = new WasteAdapter();
        listeWaste.setAdapter(wasteAdapter);

        wasteAdapter.setItemClickListener(waste -> {

            Log.d("tst","click");



        });
    }

    private void loadData() {


    }


}
