package com.example.mytown.atractii.mytown.partie.mytown;

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
import android.widget.AdapterView;

import com.example.mytown.R;
import com.example.mytown.objectives.mytown.ImageAdapter;


public class PartiaSoimulFragment extends Fragment {
    int partieImages[]={R.drawable.partie11,R.drawable.partie1,R.drawable.partie2,R.drawable.partie3,R.drawable.partie4,R.drawable.partie5};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_partia_soimul, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.partie_options_menu, menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        ViewPager partieView= getView().findViewById(R.id.partiaSoimul_viewpager);
        ImageAdapter partieImgAdapter= new ImageAdapter(getActivity(), partieImages);
        partieView.setAdapter(partieImgAdapter);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.detalii_partie_button:
                getFragmentManager().beginTransaction().replace(R.id.fragment_place, new DetaliiPartieFragment(), "detaliiPartie").addToBackStack(String.valueOf(new DetaliiPartieFragment())).commit();
                return true;

            case R.id.galerie_partie_button:
                getFragmentManager().beginTransaction().replace(R.id.fragment_place, new GalerieFragment(), "galeriePartie").addToBackStack(String.valueOf(new GalerieFragment())).commit();
            default:
                return false;
        }
    }
}
