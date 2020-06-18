package com.example.mytown;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class EventsFragment extends Fragment implements EventsRecyclerAdapter.OnItemListener {
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<Eveniment> evenimentArrayList;
    EventsRecyclerAdapter adapter;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        evenimentArrayList = new ArrayList<>();
        setUpRecyclerView();
        setUpFirebase();
        loadDataFromFirebase();
    }

    private void loadDataFromFirebase() {
        if(evenimentArrayList.size()>0)
            evenimentArrayList.clear();

        db.collection("evenimente_locale")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshot: task.getResult()){
                            Eveniment eveniment= new Eveniment(querySnapshot.getString("denumire"),
                                    querySnapshot.getString("data_inceperii"),
                                    querySnapshot.getString("imagine"),
                                    querySnapshot.getString("detalii"));

                            evenimentArrayList.add(eveniment);
                        }
                        adapter = new EventsRecyclerAdapter(EventsFragment.this, evenimentArrayList, EventsFragment.this);
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Eroare!!!", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void setUpFirebase() {
        db = FirebaseFirestore.getInstance();
    }

    private void setUpRecyclerView() {
        mRecyclerView = getView().findViewById(R.id.events_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(getContext(), EventsDetailsPage.class);
        i.putExtra("titlu", evenimentArrayList.get(position).getEventDenumire());
        i.putExtra("descriere", evenimentArrayList.get(position).getEventDescriere());
        i.putExtra("data", evenimentArrayList.get(position).getEventData());
        i.putExtra("imagine", evenimentArrayList.get(position).getEventImg());
        getContext().startActivity(i);
    }
}