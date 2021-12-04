package com.example.listanegra;

import android.content.Context;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EnemigosAdapter extends ArrayAdapter<Enemigos> {

    Context context;
    int resource;
    List<Enemigos> lista;

    public EnemigosAdapter(@NonNull Context context, int resource, @NonNull List<Enemigos> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.lista = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, parent, false);

        TextView nombre = view.findViewById(R.id.tvNombreEnemigo);

        nombre.setText(lista.get(position).nombre);

        return view;
    }


}