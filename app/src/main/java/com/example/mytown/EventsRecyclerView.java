package com.example.mytown;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class EventsRecyclerView extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView eventDenum, eventDat;
    public ImageView eventImagine;
    EventsRecyclerAdapter.OnItemListener onItemListener;

    public EventsRecyclerView(@NonNull View itemView, EventsRecyclerAdapter.OnItemListener onItemListener) {
        super(itemView);
        eventDenum = itemView.findViewById(R.id.item_denumireEveniment);
        eventDat = itemView.findViewById(R.id.item_dataEveniment);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);

    }

    public void setImage(Context context, String image) {
        eventImagine = itemView.findViewById(R.id.item_imgEveniment);
        Picasso.get().load(image).into(eventImagine);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition());
    }

}
