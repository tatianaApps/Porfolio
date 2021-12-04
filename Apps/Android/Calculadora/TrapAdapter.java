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
import com.example.coolerlogincalculator.R;
import com.example.coolerlogincalculator.response.ResponseTrapItem;

import java.util.List;

public class TrapAdapter extends ArrayAdapter<ResponseTrapItem> {

    Context context;
    int resource;
    List<ResponseTrapItem> listTrap;

    public TrapAdapter(@NonNull Context context, int resource, @NonNull List<ResponseTrapItem> objects){
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listTrap = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, parent, false);

        TextView name = view.findViewById(R.id.tvSingerName);
        TextView gender = view.findViewById(R.id.tvSingerGenre);
        ImageView photo = view.findViewById(R.id.ivSinger);

        name.setText(listTrap.get(position).name);
        gender.setText(listTrap.get(position).genre);
        Glide.with(context) //with sirve para dar contexto
                .load(listTrap.get(position).pic) //cargar el recurso
                .into(photo);// lo muestra

        return view;
    }
}
