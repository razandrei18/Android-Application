package com.example.mytown;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.HashMap;
import java.util.Map;

public class RestauranteEditActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE1 = 1;
    EditText numeUnitate;
    EditText detaliiUnitate;
    TextView alert;
    Button inserareBtn;
    Button alegereImgBtn;
    FirebaseFirestore firebaseFirestore;
    StorageReference mReference;
    int upload_count = 0;
    private ProgressDialog progressDialog;
    ArrayList<Uri> ImageList = new ArrayList<>();
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurante_edit);
        numeUnitate= findViewById(R.id.edit_restaurantName);
        detaliiUnitate = findViewById(R.id.edit_restaurantDetalii);
        alert = findViewById(R.id.edit_restauranteTextViewImg);
        inserareBtn = findViewById(R.id.edit_ButonTrimitereRestaurante);
        alegereImgBtn = findViewById(R.id.edit_chooseImgRest);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mReference = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Vă rugăm asteptați...");

        alegereImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Alegeți imaginile dorite"), RESULT_LOAD_IMAGE1);
            }
        });

        inserareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nume = numeUnitate.getText().toString().trim();
                String detalii = detaliiUnitate.getText().toString().trim();

                //adaugare informatii in Firebase Firestore
                Map<String, String> restauranteMap = new HashMap<>();

                restauranteMap.put("denumire", nume);
                restauranteMap.put("detalii", detalii);

                //adaugare imagini in Firebase Storage
                progressDialog.show();
                StorageReference ImagesFolder = FirebaseStorage.getInstance().getReference().child(nume);
                for(upload_count = 0; upload_count < ImageList.size(); upload_count++){
                    Uri individualImage = ImageList.get(upload_count);
                    StorageReference imageName = ImagesFolder.child("Imaginea" +individualImage.getLastPathSegment());
                    imageName.putFile(individualImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(RestauranteEditActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        }
                    });
                }


                firebaseFirestore.collection("restaurante").add(restauranteMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RestauranteEditActivity.this, "Inserare facută cu succes!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RestauranteEditActivity.this, CazareEditActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();
                        Toast.makeText(RestauranteEditActivity.this, "Eroare: " + error, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RestauranteEditActivity.this, "Vă rugăm selectați cel puțin 2 imagini", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().signOut();
    }
}
