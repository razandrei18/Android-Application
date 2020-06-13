package com.example.mytown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
    Button editCazareBtn, editRestauranteBtn, editEventsBtn, editLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        editCazareBtn = findViewById(R.id.editare_cazareBtn);
        editRestauranteBtn = findViewById(R.id.editare_restauranteBtn);
        editEventsBtn = findViewById(R.id.editare_eventsBtn);
        editLogoutBtn = findViewById(R.id.editare_logoutBtn);

        editLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getBaseContext(), MainActivity.class));

            }
        });

        editCazareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), com.example.mytown.CazareEditActivity.class));
            }
        });

        editRestauranteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), com.example.mytown.RestauranteEditActivity.class));
            }
        });

        editEventsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), com.example.mytown.EventsEditActivity.class));
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}
