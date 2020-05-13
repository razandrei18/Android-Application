package com.example.mytown.objectives.mytown;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytown.ObjectivesFragment;
import com.example.mytown.R;

public class MuzeuFragment extends Fragment {

    int imagesMuzeu[]={R.drawable.muzeu1, R.drawable.muzeu2, R.drawable.muzeu3, R.drawable.muzeu4};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_muzeu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager= getView().findViewById(R.id.viewpager_muzeu);
        ImageAdapter imageAdapterMuzeu = new ImageAdapter(getActivity(), imagesMuzeu);
        viewPager.setAdapter(imageAdapterMuzeu);
    }
}
