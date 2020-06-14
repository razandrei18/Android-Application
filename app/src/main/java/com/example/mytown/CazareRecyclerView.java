package com.example.mytown;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class CazareRecyclerView extends RecyclerView.ViewHolder {
    public TextView cazareDenum, cazareDet;
    public ImageView cazareImagine ;


    public CazareRecyclerView(@NonNull View itemView) {
        super(itemView);

        cazareDenum = itemView.findViewById(R.id.item_denumireCazare);
        cazareDet  = itemView.findViewById(R.id.item_detaliiCazare);
    }

    public void setImage(Context context, String image){
        cazareImagine = itemView.findViewById(R.id.item_imgCazare);
        Picasso.get().load(image).into(cazareImagine);
    }

}
