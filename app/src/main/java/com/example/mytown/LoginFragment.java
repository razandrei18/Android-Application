package com.example.mytown;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;


public class LoginFragment extends Fragment {

    EditText loginUsername;
    EditText loginPassword;
    Button loginButton;
    TextView loginRegister;
    TextView resetPassword;
    FirebaseAuth firebaseAuth;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginUsername = getView().findViewById(R.id.edittext_username);
        loginPassword = getView().findViewById(R.id.edittext_password);
        loginButton = getView().findViewById(R.id.login_button);
        loginRegister = getView().findViewById(R.id.textview_inregistrare);
        resetPassword = getView().findViewById(R.id.resetPassword_id);
        firebaseAuth = firebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {
            getFragmentManager().beginTransaction().replace(R.id.fragment_place, new HomeFragment(), "homeFragment").addToBackStack(String.valueOf(new HomeFragment())).commit();
        }


        //adaugarea evenimentelor la butonul de Login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = loginUsername.getText().toString().trim();
                String pwd = loginPassword.getText().toString().trim();

                if(TextUtils.isEmpty(mail)){ //verificam daca email ul a fost introdus
                    loginUsername.setError("Va rugam introduceti adresa de email");
                    return;
                }
                if (TextUtils.isEmpty(pwd) || pwd.length() < 6){  //verificam daca parola a fost introdusa si este de minim 6 caractere
                    loginUsername.setError("Va rugam introduceti o parola de minim 6 caractere");
                    return;
                }


                //authenticate the user
                firebaseAuth.signInWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String mail = loginUsername.getText().toString().trim();
                        if(task.isSuccessful()) {
                            if (mail.contentEquals("razvanioan.andrei22@gmail.com")) {
                                Toast.makeText(LoginFragment.this.getContext(), "Sunteti în modul Admin.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), AdminActivity.class));
                            } else {
                                Toast.makeText(LoginFragment.this.getContext(), "Autentificare reusita", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getActivity(), MainActivity.class));
                            }
                        }
                        else {
                            Toast.makeText(LoginFragment.this.getContext(), "Autentificarea a esuat", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        //Adaugarea evenimentelor la textview-ul Register din login fragment
        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_place, new RegisterFragment(), "registerFrag").addToBackStack(String.valueOf(new RegisterFragment())).commit();
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Resetare parolă");
                passwordResetDialog.setMessage("Va rugam introduceti Email-ul pentru resetarea parolei");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Trimite", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the email and send the reset link
                        String mailField = resetMail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mailField).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginFragment.this.getContext(), "Link-ul pentru resetarea parolei a fost trimis la adresa de email", Toast.LENGTH_LONG).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginFragment.this.getContext(), "Eroare la trimiterea mail-ului! Va rugam reincercati.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("Anulare", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                passwordResetDialog.create().show();
            }
        });
    }


}
