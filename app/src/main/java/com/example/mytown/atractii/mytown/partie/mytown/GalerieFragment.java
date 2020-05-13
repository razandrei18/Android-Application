package com.example.mytown.atractii.mytown.partie.mytown;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mytown.R;

import java.util.Vector;


public class GalerieFragment extends Fragment {

    RecyclerView recyclerView;
    Vector<YoutubeVideos> youtubeVideos= new Vector<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_galerie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView= (RecyclerView) getActivity().findViewById(R.id.galeriePartie_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        youtubeVideos.add( new YoutubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1pzDMSC3qzs\" frameborder=\"0\" allowfullscreen></iframe>") );
        VideoAdapter videoAdapter= new VideoAdapter(youtubeVideos);
        recyclerView.setAdapter(videoAdapter);
    }
}
