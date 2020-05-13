package com.example.mytown.atractii.mytown.aventuraParc.mytown;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytown.R;
import com.example.mytown.objectives.mytown.ImageAdapter;

public class AventuraParcFragment extends Fragment {

    int aventuraImgs[] = {R.drawable.aventura2, R.drawable.aventura3, R.drawable.aventura4, R.drawable.aventura5, R.drawable.aventura6, R.drawable.aventura7};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_aventura_parc, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.aventuraparc_options_menu, menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        ViewPager aventuraParcView = getView().findViewById(R.id.aventura_viewPager);
        ImageAdapter aventuraAdapter = new ImageAdapter(getActivity(), aventuraImgs);
        aventuraParcView.setAdapter(aventuraAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.echipament_button:
                getFragmentManager().beginTransaction().replace(R.id.fragment_place, new EchipamentAventuraFragment(), "echipamentAv").addToBackStack(String.valueOf(new EchipamentAventuraFragment())).commit();
                return true;
            case R.id.preturi_button:
                getFragmentManager().beginTransaction().replace(R.id.fragment_place, new PreturiAventuraFragment(), "echipamentAv").addToBackStack(String.valueOf(new PreturiAventuraFragment())).commit();
                return true;
            case R.id.program_button:
                getFragmentManager().beginTransaction().replace(R.id.fragment_place, new ProgramAventura(), "echipamentAv").addToBackStack(String.valueOf(new ProgramAventura())).commit();
                return true;
            default:
                return false;
        }
    }
}
