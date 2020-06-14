package com.example.mytown;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

public class CazareRecyclerView extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView cazareDenum, cazareDet;
    public ImageView cazareImagine ;
    CazareRecyclerAdapter.OnItemListener onItemListener;


    public CazareRecyclerView(@NonNull View itemView, CazareRecyclerAdapter.OnItemListener onItemListener) {
        super(itemView);

        cazareDenum = itemView.findViewById(R.id.item_denumireCazare);
        cazareDet  = itemView.findViewById(R.id.item_detaliiCazare);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    public void setImage(Context context, String image){
        cazareImagine = itemView.findViewById(R.id.item_imgCazare);
        Picasso.get().load(image).into(cazareImagine);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }
}
