package com.example.mytown;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CazareRecyclerAdapter extends RecyclerView.Adapter<CazareRecyclerView> {
    CazareFragment cazareFragment;
    ArrayList<Cazare> cazareArrayList;

    public CazareRecyclerAdapter(CazareFragment cazareFragment, ArrayList<Cazare> cazareArrayList) {
        this.cazareFragment = cazareFragment;
        this.cazareArrayList = cazareArrayList;
    }

    @NonNull
    @Override
    public CazareRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(cazareFragment.getContext());
        View view = layoutInflater.inflate(R.layout.item_cazare, parent, false);
        return new CazareRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CazareRecyclerView holder, int position) {
        holder.cazareDenum.setText(cazareArrayList.get(position).getCazareDenumire());
        holder.cazareDet.setText("Email contact: "+cazareArrayList.get(position).getCazareDetalii());
        holder.setImage(cazareFragment.getContext(), cazareArrayList.get(position).getCazareImg());
    }

    @Override
    public int getItemCount() {
        return cazareArrayList.size();
    }
}
