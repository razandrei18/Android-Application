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

import com.example.mytown.objectives.mytown.HumorMFragment;
import com.example.mytown.objectives.mytown.MuzeuFragment;
import com.example.mytown.objectives.mytown.VoronetMFragment;


public class ObjectivesFragment extends Fragment {
    public static ListView listView;
    public static String[] titles = {"Mănăstirea Voroneț", "Mănăstirea Humor", "Muzeul Obiceiurilor Populare din Bucovina"};
    public static int[] images = {R.drawable.voronet, R.drawable.manastirea_humor, R.drawable.muzeu};

    public ObjectivesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_objectives, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = getView().findViewById(R.id.listview_obj);
        MyAdapter myAdapter = new MyAdapter(getActivity(), titles, images);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Fragment fragment_v = new VoronetMFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_place, fragment_v, "first").addToBackStack(String.valueOf(fragment_v)).commit();
                }
                if (position == 1) {
                    Fragment fragment_humor = new HumorMFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_place, fragment_humor, "second").addToBackStack(String.valueOf(fragment_humor)).commit();
                }
                if (position == 2) {
                    Fragment fragment_muzeu = new MuzeuFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_place, fragment_muzeu, "third").addToBackStack(String.valueOf(fragment_muzeu)).commit();
                }
            }
        });
    }

    public class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String[] mTitle;
        int[] mImg;

        public MyAdapter(Context c, String[] mTitle, int[] mImg) {
            super(c, R.layout.item_obj_list, R.id.title_textview, titles);
            this.context = c;
            this.mTitle = mTitle;
            this.mImg = mImg;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item = layoutInflater.inflate(R.layout.item_obj_list, parent, false);
            ImageView imageView = item.findViewById(R.id.item_imageview);
            TextView textView = item.findViewById(R.id.title_textview);
            imageView.setImageResource(mImg[position]);
            textView.setText(mTitle[position]);
            return item;
        }
    }
}