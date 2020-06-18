package com.example.mytown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EventsDetailsPage extends AppCompatActivity {
    TextView pEvTitlu, pEvDescriere, pEvData;
    ImageView pEvImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_details_page);
        final Intent data= getIntent();

        pEvTitlu = findViewById(R.id.eventsPage_titlu);
        pEvData = findViewById(R.id.eventsPage_data);
        pEvDescriere = findViewById(R.id.eventsPage_descriere);

        pEvTitlu.setText(data.getStringExtra("titlu"));
        pEvDescriere.setText(Html.fromHtml(data.getStringExtra("descriere")));
        pEvData.setText(data.getStringExtra("data"));
        setImage(data.getStringExtra("imagine"));
    }

    public void setImage(String image){
        pEvImage = findViewById(R.id.eventsPage_imagine);
        Picasso.get().load(image).into(pEvImage);
    }
}
