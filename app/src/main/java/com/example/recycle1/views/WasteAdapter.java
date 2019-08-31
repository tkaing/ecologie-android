package com.example.recycle1.views;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recycle1.R;
import com.example.recycle1.data.model.Waste;
import com.example.recycle1.data.model.WasteType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WasteAdapter extends RecyclerView.Adapter<WasteAdapter.WeastViewHolder> {

    private List<Waste> weastList = new ArrayList<Waste>(){{

        add(WasteType.getById(1));
        add(WasteType.getById(2));
        add(WasteType.getById(3));
        add(WasteType.getById(4));
    }};
    private ItemClickListener itemClickListener;

    public void setWeastList(List<Waste> weastList) {
        this.weastList = weastList;
        notifyDataSetChanged();
    }

    public List<Waste> getWeastList() {
        return weastList;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override public WeastViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_waste, viewGroup, false);
        return new WeastViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull WeastViewHolder wasteViewHolder, int i) {
         List<Waste> wasteList = new ArrayList<Waste>(){{

            add(WasteType.getById(1));
            add(WasteType.getById(2));
            add(WasteType.getById(3));
            add(WasteType.getById(4));
        }};
        Waste waste = wasteList.get(i);
        wasteViewHolder.nameTv.setText(waste.getName());
        wasteViewHolder.idTv.setText(String.valueOf(waste.getId()));
        String image = "tree.jpg";
        String completePath = Environment.getExternalStorageDirectory() + "/" + image;

        File file = new File(completePath);
        Uri uri = Uri.fromFile(file);


        Glide.with(wasteViewHolder.itemView).load(waste.getPictureUrl()).into(wasteViewHolder.pictureImv);

        if (itemClickListener != null) {
            wasteViewHolder.itemView.setOnClickListener(v -> itemClickListener.onClick(waste));
        }
    }

    @Override public int getItemCount() {
        return weastList != null ? weastList.size() : 0;
    }

    class WeastViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_weast_picture_imv)
        ImageView pictureImv;
        @BindView(R.id.item_weast_name_tv)
        TextView nameTv;
        @BindView(R.id.item_weast_id_tv) TextView idTv;

        public WeastViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemClickListener {
        void onClick(Waste waste);
    }
}


