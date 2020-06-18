package com.example.mytown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RestaurantDetailsPage extends AppCompatActivity {
    TextView pRestTitlu, pRestDetalii, pRestContact;
    ImageView pRestImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details_page);
        Intent data = getIntent();

        pRestTitlu = findViewById(R.id.restPage_titlu);
        pRestDetalii = findViewById(R.id.restPage_detalii);
        pRestContact = findViewById(R.id.restPage_detaliiContact);

        pRestTitlu.setText(data.getStringExtra("titlu"));
        pRestDetalii.setText(data.getStringExtra("detaliiRest"));
        pRestContact.setText(data.getStringExtra("contactRest"));
        setImage(data.getStringExtra("imagineRest"));

    }

    public void setImage(String image){
        pRestImage = findViewById(R.id.restPage_imagine);
        Picasso.get().load(image).into(pRestImage);
    }
}
