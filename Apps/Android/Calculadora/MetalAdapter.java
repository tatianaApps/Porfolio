package com.example.coolerlogincalculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.coolerlogincalculator.response.GruposItem;
import com.example.coolerlogincalculator.R;

import java.util.List;

public class MetalAdapter extends ArrayAdapter<GruposItem> {

    Context context;
    int resource;
    List<GruposItem> listMetal;

    public MetalAdapter(@NonNull Context context, int resource, @NonNull List<GruposItem> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listMetal = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, parent, false);

        TextView name = view.findViewById(R.id.tvSingerName);
        TextView description = view.findViewById(R.id.tvSingerDescription);
        ImageView photo = view.findViewById(R.id.ivSinger);

        name.setText(listMetal.get(position).nombre);
        description.setText(listMetal.get(position).descripcion);
        Glide.with(context)
                .load(listMetal.get(position).imagen)
                .into(photo);

        return view;
    }
}
