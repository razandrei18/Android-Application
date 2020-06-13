package com.example.mytown;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventsEditActivity extends AppCompatActivity {
    EditText eventName;
    EditText eventDetails;
    TextView alert;
    Button alegereDataBtn;
    TextView afisareData;
    Button alegereImgEvent;
    Button insertEvent;
    Button backBtn;
    FirebaseFirestore firebaseFirestore;
    StorageReference mReference;
    int upload_count = 0;
    private ProgressDialog progressDialog;
    ArrayList<Uri> ImageList = new ArrayList<>();
    private Uri imageUri;
    private static final int RESULT_LOAD_IMAGE1 = 1;
    DatePickerDialog.OnDateSetListener onDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_edit);

        eventName= findViewById(R.id.edit_eventName);
        eventDetails= findViewById(R.id.edit_eventDetalii);
        alert= findViewById(R.id.edit_textViewImgEvent);
        alegereImgEvent= findViewById(R.id.edit_chooseImgEvent);
        insertEvent= findViewById(R.id.edit_ButonTrimitereEvent);
        alegereDataBtn =  findViewById(R.id.edit_chooseDate);
        afisareData = findViewById(R.id.events_showDate);
        backBtn= findViewById(R.id.edit_ButonInapoiEvent);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vă rugăm asteptați...");
        firebaseFirestore = FirebaseFirestore.getInstance();
        mReference = FirebaseStorage.getInstance().getReference();


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AdminActivity.class));
            }
        });

        alegereDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int anul = calendar.get(Calendar.YEAR);
                int luna = calendar.get(Calendar.MONTH);
                int ziua = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(EventsEditActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onDateSetListener, anul, luna, ziua);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               month=month+1;

               String data = dayOfMonth + "/" + month + "/" + year;
               afisareData.setText(data);
            }
        };

        alegereImgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Alegeți imaginile dorite"), RESULT_LOAD_IMAGE1);
            }
        });

        alegereImgEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Alegeți imaginile dorite"), RESULT_LOAD_IMAGE1);
            }
        });


        insertEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nume = eventName.getText().toString().trim();
                String detalii = eventDetails.getText().toString().trim();
                String dataInceperii = afisareData.getText().toString().trim();


                //adaugare informatii in Firebase Firestore
                Map<String, String> eventsMap = new HashMap<>();

                eventsMap.put("denumire", nume);
                eventsMap.put("detalii", detalii);
                eventsMap.put("data_inceperii", dataInceperii);

                //adaugare imagini in Firebase Storage
                progressDialog.show();
                StorageReference ImagesFolder = FirebaseStorage.getInstance().getReference().child(nume);
                for(upload_count = 0; upload_count < ImageList.size(); upload_count++){
                    Uri individualImage = ImageList.get(upload_count);
                    StorageReference imageName = ImagesFolder.child("Imaginea" +individualImage.getLastPathSegment());
                    imageName.putFile(individualImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(EventsEditActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


                firebaseFirestore.collection("evenimente_locale").add(eventsMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(EventsEditActivity.this, "Inserare facută cu succes!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EventsEditActivity.this, CazareEditActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();
                        Toast.makeText(EventsEditActivity.this, "Eroare: " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE1 &&resultCode== RESULT_OK){

            if(data.getClipData() != null){
                int countImages = data.getClipData().getItemCount();
                int currentImageSelect= 0;
                while (currentImageSelect<countImages){
                    imageUri = data.getClipData().getItemAt(currentImageSelect).getUri();
                    ImageList.add(imageUri);
                    currentImageSelect++;
                }
                alert.setVisibility(View.VISIBLE);
                alert.setText("Au fost selectate " + ImageList.size() + " imagini.");
            }
            else {
                Toast.makeText(EventsEditActivity.this, "Vă rugăm selectați cel puțin 2 imagini", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}