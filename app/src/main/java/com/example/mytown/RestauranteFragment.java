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


public class RestauranteFragment extends Fragment implements RestauranteRecyclerAdapter.OnItemListener {
    FirebaseFirestore db;
    RecyclerView mRecyclerView;
    ArrayList<Restanurant> restaurantList;
    RestauranteRecyclerAdapter adapter;
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        restaurantList = new ArrayList<>();
        setUpRecyclerView();
        setUpFirebase();
        loadDataFromFirebase();
    }

    private void loadDataFromFirebase() {
        if(restaurantList.size()>0)
            restaurantList.clear();

        db.collection("restaurante")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshot: task.getResult()){
                            Restanurant restanurant = new Restanurant(querySnapshot.getString("denumire"),
                                                                        querySnapshot.getString("detalii"),
                                                                        querySnapshot.getString("imagine"),
                                                                        querySnapshot.getString("detalii_contact")
                                                                       );

                            restaurantList.add(restanurant);
                        }
                        adapter = new RestauranteRecyclerAdapter(RestauranteFragment.this, restaurantList, RestauranteFragment.this);
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
        mRecyclerView = getView().findViewById(R.id.recycler_restaurante);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_restaurante, container, false);
    }

    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(getContext(), RestaurantDetailsPage.class);
        i.putExtra("titlu", restaurantList.get(position).getRestDenumire());
        i.putExtra("detaliiRest", restaurantList.get(position).getRestDescriere());
        i.putExtra("contactRest", restaurantList.get(position).getRestContact());
        i.putExtra("imagineRest", restaurantList.get(position).getRestImg());
        getContext().startActivity(i);
    }
}
