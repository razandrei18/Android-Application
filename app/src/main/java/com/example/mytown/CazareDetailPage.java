package com.example.mytown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CazareDetailPage extends AppCompatActivity {
    TextView pTitlu, pDescriere, pEmailContact, pDetaliiContact;
    ImageView pImage;
    Button rezervareBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cazare_detail_page);

        final Intent data= getIntent();

        pTitlu = findViewById(R.id.cazarePage_titlu);
        pDescriere = findViewById(R.id.cazarePage_descriere);
        pEmailContact = findViewById(R.id.cazarePage_email_contact);
        pDetaliiContact = findViewById(R.id.cazarePage_detaliiContact);
        rezervareBtn = findViewById(R.id.cazarePage_rezervareBtn);


        pTitlu.setText(data.getStringExtra("titlu"));
        pDescriere.setText(Html.fromHtml(data.getStringExtra("descriere")));
        pEmailContact.setText("Email: "+data.getStringExtra("email_contact"));
        pDetaliiContact.setText(data.getStringExtra("detalii_contact"));
        setImage(data.getStringExtra("imagine"));

        rezervareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MailActivity.class );
                intent.putExtra("email_contact", data.getStringExtra("email_contact"));
                v.getContext().startActivity(intent);
            }
        });
    }

    public void setImage(String image){
        pImage = findViewById(R.id.cazarePage_imagine);
        Picasso.get().load(image).into(pImage);
    }
}
