package com.example.mytown;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestauranteRecyclerView extends RecyclerView.ViewHolder {

    public TextView restDenum, restDesc;
    public ImageView restImagine;

    public RestauranteRecyclerView(@NonNull View itemView) {
        super(itemView);

        restDenum = itemView.findViewById(R.id.item_denumireRest);
        restDesc = itemView.findViewById(R.id.item_descriereRest);


    }

    public void setImage(Context context, String image){
        restImagine = itemView.findViewById(R.id.item_imgRest);
        Picasso.get().load(image).into(restImagine);
    }


}
