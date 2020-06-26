package com.example.mytown;

import android.content.Context;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegisterFragment extends Fragment {
    EditText registerFullName;
    EditText registerPassword;
    EditText registerEmail;
    Button registerButton;
    TextView registerLogin;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;


    public RegisterFragment() {
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
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerFullName = getView().findViewById(R.id.edittext_fullName);
        registerPassword = getView().findViewById(R.id.edittext_newPassword);
        registerEmail = getView().findViewById(R.id.edittext_mail);
        registerButton = getView().findViewById(R.id.register_button);
        registerLogin = getView().findViewById(R.id.textview_logare);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = getView().findViewById(R.id.progressBar);

        if (firebaseAuth.getCurrentUser() != null) {
            getFragmentManager().beginTransaction().replace(R.id.fragment_place, new HomeFragment(), "homeFragment").addToBackStack(String.valueOf(new HomeFragment())).commit();
        }

        //setam evenimentul ce are loc la apasarea butonului Inregistrare
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = registerEmail.getText().toString().trim();
                String pwd = registerPassword.getText().toString().trim();

                if (TextUtils.isEmpty(mail)) { //verificam daca email ul a fost introdus
                    registerEmail.setError("Va rugam introduceti adresa de email");
                    return;
                }
                if (TextUtils.isEmpty(pwd) || pwd.length() < 6) {  //verificam daca parola a fost introdusa si este de minim 6 caractere
                    registerEmail.setError("Va rugam introduceti o parola de minim 6 caractere");
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);  //facem vizibil progressBar-ul pentru a anunta utilizatorul ca are loc un proces

                firebaseAuth.createUserWithEmailAndPassword(mail, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterFragment.this.getContext(), "Cont creat cu succes", Toast.LENGTH_LONG).show();
                            getFragmentManager().beginTransaction().replace(R.id.fragment_place, new LoginFragment(), "loginFrag").addToBackStack(String.valueOf(new LoginFragment())).commit();
                        } else {
                            Toast.makeText(RegisterFragment.this.getContext(), "Eroare la crearea contului", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

        registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_place, new LoginFragment(), "loginFrag").addToBackStack(String.valueOf(new LoginFragment())).commit();
            }
        });
    }
}
