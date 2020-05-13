package com.example.mytown.objectives.mytown;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.mytown.R;

public class VoronetMFragment extends Fragment {


    int imagesVoronet[] = {R.drawable.voro2, R.drawable.voro1, R.drawable.voro3,R.drawable.voro6, R.drawable.voro5, R.drawable.voro4,};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voronet_m, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = getView().findViewById(R.id.viewpager_voro);
        ImageAdapter adapter = new ImageAdapter(getContext(), imagesVoronet);
        viewPager.setAdapter(adapter);

    }
}
