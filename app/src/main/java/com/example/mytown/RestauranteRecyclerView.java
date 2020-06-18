package com.example.mytown;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestauranteRecyclerView extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView restDenum, restDesc;
    public ImageView restImagine;
    RestauranteRecyclerAdapter.OnItemListener onItemListener;

    public RestauranteRecyclerView(@NonNull View itemView, RestauranteRecyclerAdapter.OnItemListener onItemListener) {
        super(itemView);

        restDenum = itemView.findViewById(R.id.item_denumireRest);
        restDesc = itemView.findViewById(R.id.item_descriereRest);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);


    }

    public void setImage(Context context, String image){
        restImagine = itemView.findViewById(R.id.item_imgRest);
        Picasso.get().load(image).into(restImagine);
    }
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }

}
