package com.example.mytown;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mytown.atractii.mytown.aventuraParc.mytown.AventuraParcFragment;
import com.example.mytown.atractii.mytown.piscina.mytown.PiscinaFragment;
import com.example.mytown.atractii.mytown.partie.mytown.PartiaSoimulFragment;


public class AtractiiFragment extends Fragment {
    public static ListView listView;
    public static String titlesAtr[]= {"Pârtia Șoimul", "Piscina Ariniș", "Aventura Parc"};
    public static int imagesAtr[]={R.drawable.partia_soimul,R.drawable.piscina_arinis, R.drawable.aventura_parc};

    public AtractiiFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atractii, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView= getView().findViewById(R.id.listview_atractii);
        MyAdapter myAdapter= new MyAdapter(getActivity(), titlesAtr,imagesAtr );
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position==0){
                    getFragmentManager().beginTransaction().replace(R.id.fragment_place, new PartiaSoimulFragment(),"soimul").addToBackStack(String.valueOf(new PartiaSoimulFragment())).commit();
                }
                if (position==1){
                    getFragmentManager().beginTransaction().replace(R.id.fragment_place, new PiscinaFragment(),"piscina").addToBackStack(String.valueOf(new PiscinaFragment())).commit();
                }
                if (position==2){
                    getFragmentManager().beginTransaction().replace(R.id.fragment_place, new AventuraParcFragment(),"piscina").addToBackStack(String.valueOf(new AventuraParcFragment())).commit();
                }
            }
        });
    }

    public class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String mTitle[];
        int mImg[];

        public MyAdapter(Context c, String mTitle[], int mImg[]) {
            super(c, R.layout.item_obj_list, R.id.title_textview, titlesAtr);
            this.context = c;
            this.mTitle = mTitle;
            this.mImg = mImg;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item= layoutInflater.inflate(R.layout.item_atractii_list, parent, false);
            ImageView imageView= item.findViewById(R.id.item_imageviewAtr);
            TextView textView= item.findViewById(R.id.title_textviewAtr);
            imageView.setImageResource(mImg[position]);
            textView.setText(mTitle[position]);
            return  item;
        }
    }
}