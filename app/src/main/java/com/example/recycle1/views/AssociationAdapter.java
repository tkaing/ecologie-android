package com.example.recycle1.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recycle1.R;
import com.example.recycle1.data.dto.AssociationDTO;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class AssociationAdapter extends RecyclerView.Adapter<AssociationAdapter.associationViewHolder> {
    private List<AssociationDTO> associationList;
    private ItemClickListener itemClickListener;

    public void setAssociationList(List<AssociationDTO> associationList) {
        this.associationList = associationList;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override public associationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_parcours, viewGroup, false);
        return new associationViewHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull associationViewHolder associationViewHolder, int i) {

        if (itemClickListener != null) {

        }
    }

    @Override public int getItemCount() {
        return associationList != null ? associationList.size() : 0;
    }

    class associationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.file_name_tv)
        TextView nameTv;

        public associationViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }

    public interface ItemClickListener {
        void onClick(File file);
    }
}
