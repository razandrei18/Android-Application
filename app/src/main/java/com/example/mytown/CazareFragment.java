package com.example.mytown;

import android.content.Intent;
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

public class CazareFragment extends Fragment implements CazareRecyclerAdapter.OnItemListener {
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<Cazare> cazareArrayList;
    CazareRecyclerAdapter adapter;

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cazareArrayList = new ArrayList<>();
        setUpRecyclerView();
        setUpFirebase();
        loadDataFromFirebase();
    }

    private void loadDataFromFirebase() {
        if(cazareArrayList.size()>0)
            cazareArrayList.clear();

        db.collection("unitati_cazare")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshot: task.getResult()){
                            Cazare cazare= new Cazare(querySnapshot.getString("denumire"),
                                    querySnapshot.getString("email_contact"),
                                    querySnapshot.getString("imagine_principala"),
                                    querySnapshot.getString("descriere"),
                                    querySnapshot.getString("detalii_contact"));

                            cazareArrayList.add(cazare);
                        }
                        adapter = new CazareRecyclerAdapter(CazareFragment.this, cazareArrayList, CazareFragment.this);
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
        mRecyclerView = getView().findViewById(R.id.cazare_recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cazare, container, false);
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(getContext(), CazareDetailPage.class);
        i.putExtra("titlu", cazareArrayList.get(position).getCazareDenumire());
        i.putExtra("email_contact", cazareArrayList.get(position).getCazareEmailContact());
        i.putExtra("descriere", cazareArrayList.get(position).getCazareDescriere());
        i.putExtra("imagine", cazareArrayList.get(position).getCazareImg());
        i.putExtra("detalii_contact", cazareArrayList.get(position).getCazareDetaliiContact());
        getContext().startActivity(i);
    }
}
