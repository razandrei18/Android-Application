package com.example.mytown;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestauranteRecyclerAdapter extends RecyclerView.Adapter<RestauranteRecyclerView> {

    RestauranteFragment restauranteFragment;
    ArrayList<Restanurant> restanurantArrayList;
    OnItemListener onItemListener;

    public RestauranteRecyclerAdapter(RestauranteFragment restauranteFragment, ArrayList<Restanurant> restanurantArrayList, OnItemListener onItemListener) {
        this.restauranteFragment = restauranteFragment;
        this.restanurantArrayList = restanurantArrayList;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public RestauranteRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(restauranteFragment.getContext());
        View view= layoutInflater.inflate(R.layout.restaurante_item, parent, false);
        return new RestauranteRecyclerView(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RestauranteRecyclerView holder, int position) {
        holder.restDenum.setText(restanurantArrayList.get(position).getRestDenumire());
        holder.restDesc.setText(restanurantArrayList.get(position).getRestDescriere());
        holder.setImage(restauranteFragment.getContext(), restanurantArrayList.get(position).getRestImg());
    }

    @Override
    public int getItemCount() {
        return restanurantArrayList.size();
    }

    public interface OnItemListener{
        void onItemClick(int position);
    }
}
