package com.example.listanegra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvListaNombres;
    Button btnAniadirEnemigo;
    List<Enemigos> arrayEnemigos;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    EditText etNombre;
    EditText etEdad;
    EditText etOfensa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvListaNombres = findViewById(R.id.lvListaNombres);
        btnAniadirEnemigo = findViewById(R.id.btnAniadirEnemigo);
        etNombre = findViewById(R.id.etNombre);
        etEdad = findViewById(R.id.etEdad);
        etOfensa = findViewById(R.id.etOfensa);

        sharedPreferences = getSharedPreferences("ListaEnemigos", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();

        arrayEnemigos = new ArrayList<>();
        String listaRecuperada = sharedPreferences.getString("Lista_completa", "");

        if (listaRecuperada.isEmpty()) {
            arrayEnemigos.add(new Enemigos("Tatiana", 24, "No se valora"));
        } else {
            arrayEnemigos = gson.fromJson(listaRecuperada, new TypeToken<List<Enemigos>>() {
            }.getType());
        }

        EnemigosAdapter adapterEnemigos = new EnemigosAdapter(getApplicationContext(), R.layout.celda_avanzada, arrayEnemigos);
        lvListaNombres.setAdapter(adapterEnemigos);

        lvListaNombres.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {
                arrayEnemigos.remove(i);
                ((EnemigosAdapter) lvListaNombres.getAdapter()).notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Enemigo perdonado", Toast.LENGTH_LONG).show();
                updateList();
                return false;
            }
        });

        lvListaNombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String nombre = arrayEnemigos.get(i).nombre;
                String ofensa = arrayEnemigos.get(i).ofensa;
                int edad = arrayEnemigos.get(i).edad;

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("edad", edad);
                intent.putExtra("ofensa", ofensa);
                startActivity(intent);
            }
        });

        btnAniadirEnemigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreAniadido = etNombre.getText().toString();
                String ofensaAniadida = etOfensa.getText().toString();
                int edadAniadida;
                if (etEdad.getText().toString().equals("")) {
                    edadAniadida = -1;
                } else {
                    edadAniadida = Integer.parseInt(etEdad.getText().toString());
                }

                if (nombreAniadido.isEmpty() && ofensaAniadida.isEmpty() && etEdad.getText().toString().isEmpty()) {
                    Toast.makeText(getApplication(), "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                } else if (etNombre.getText().toString().isEmpty()) {
                    etNombre.setError("Nombre vacío");
                    Toast.makeText(getApplication(), "El nombre de tu enemigo no puede estar vacío", Toast.LENGTH_SHORT).show();
                } else if (edadAniadida < 0 || edadAniadida > 150) {
                    etEdad.setError("Edad no comprendida");
                    Toast.makeText(getApplication(), "La edad tiene que estar comprendida entre 0 y 150", Toast.LENGTH_SHORT).show();
                } else if (etEdad.getText().toString().isEmpty()) {
                    etEdad.setError("Edad vacía");
                    Toast.makeText(getApplication(), "La edad de tu enemigo no puede estar vacía", Toast.LENGTH_SHORT).show();
                } else if (etOfensa.getText().toString().isEmpty()) {
                    etOfensa.setError("Ofensa vacía");
                    Toast.makeText(getApplication(), "La ofensa de tu enemigo no puede estar vacía", Toast.LENGTH_SHORT).show();
                } else {
                    arrayEnemigos.add(new Enemigos(nombreAniadido, edadAniadida, ofensaAniadida));
                    ((EnemigosAdapter) lvListaNombres.getAdapter()).notifyDataSetChanged();
                    updateList();
                    etEdad.getText().clear();
                    etOfensa.getText().clear();
                    etNombre.getText().clear();
                }
            }
        });
    }

    void updateList() {
        editor.putString("Lista_completa", gson.toJson(arrayEnemigos));
        editor.apply();
    }
}