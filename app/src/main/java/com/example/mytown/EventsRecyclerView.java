package com.example.mytown;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class EventsRecyclerView extends RecyclerView.ViewHolder {
    public TextView eventDenum, eventDat;
    public ImageView eventImagine;


    public EventsRecyclerView(@NonNull View itemView) {
        super(itemView);

        eventDenum = itemView.findViewById(R.id.item_denumireEveniment);
        eventDat = itemView.findViewById(R.id.item_dataEveniment);

    }
    public void setImage(Context context, String image){
        eventImagine = itemView.findViewById(R.id.item_imgEveniment);
        Picasso.get().load(image).into(eventImagine);
    }


}
