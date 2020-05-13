package com.example.mytown.atractii.mytown.piscina.mytown;

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

public class PiscinaFragment extends Fragment {

    int piscinaImages[] = {R.drawable.piscina_arinis, R.drawable.piscina2, R.drawable.piscina1, R.drawable.piscina3};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_piscina, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        ViewPager piscinaViewPager = getView().findViewById(R.id.piscina_viewPager);
        ImageAdapter piscinaImgAdapter = new ImageAdapter(getActivity(), piscinaImages);
        piscinaViewPager.setAdapter(piscinaImgAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.piscina_options_menu, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.detalii_piscina_button:
                getFragmentManager().beginTransaction().replace(R.id.fragment_place, new DetaliiPiscinaFragment(), "detaliiPartie").addToBackStack(String.valueOf(new DetaliiPiscinaFragment())).commit();
                return true;

            default:
                return false;
        }
    }
}
