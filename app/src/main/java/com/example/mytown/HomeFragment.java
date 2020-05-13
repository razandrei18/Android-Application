package com.example.mytown;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeFragment extends Fragment {

    ViewFlipper viewFlipper;
    int images[] = {R.drawable.ghh2,R.drawable.primaria_gh,R.drawable.ghh, R.drawable.ghh4, R.drawable.ghh3, R.drawable.ghh5};
    TextView currentUser;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        currentUser = getActivity().findViewById(R.id.current_userMail);

        firebaseAuth =firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser == null){
            currentUser.setText("Nu sunteti autentificat");
            currentUser.setVisibility(View.INVISIBLE);
        }
        else{

            currentUser.setVisibility(View.VISIBLE);
            currentUser.setText("Bun venit, " + firebaseUser.getEmail() + "!");
        }

        viewFlipper = getView().findViewById(R.id.viewFlipper);
        for (int image : images) {
            imageFlipper(image);
        }

    }



    public void imageFlipper(int image) {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setOutAnimation(getContext(), android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(getContext(), android.R.anim.slide_in_left);
    }

}

