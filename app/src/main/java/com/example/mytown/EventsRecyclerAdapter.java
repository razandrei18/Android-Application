package com.example.mytown;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerView> {

    EventsFragment eventsFragment;
    ArrayList<Eveniment> evenimentArrayList;

    public EventsRecyclerAdapter(EventsFragment eventsFragment, ArrayList<Eveniment> evenimentArrayList) {
        this.eventsFragment = eventsFragment;
        this.evenimentArrayList = evenimentArrayList;
    }

    @NonNull
    @Override
    public EventsRecyclerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(eventsFragment.getContext());
        View view = layoutInflater.inflate(R.layout.item_evenimente, parent, false);
        return new EventsRecyclerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsRecyclerView holder, int position) {
        holder.eventDenum.setText(evenimentArrayList.get(position).getEventDenumire());
        holder.eventDat.setText("Data de început:" + evenimentArrayList.get(position).getEventData());
        holder.setImage(eventsFragment.getContext(), evenimentArrayList.get(position).getEventImg());
    }

    @Override
    public int getItemCount() {
        return evenimentArrayList.size();
    }
}
