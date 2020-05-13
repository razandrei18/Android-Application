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

import com.example.mytown.R;


public class HumorMFragment extends Fragment {


    int imagesHumor[] = {R.drawable.mh1, R.drawable.mh2, R.drawable.mh3,R.drawable.mh4, R.drawable.mh5};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_humor_m, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = getView().findViewById(R.id.viewpager_humor);
        ImageAdapter adapter = new ImageAdapter(getContext(), imagesHumor);
        viewPager.setAdapter(adapter);

    }

}
