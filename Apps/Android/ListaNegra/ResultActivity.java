package com.example.listanegra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView tvDatosEnemigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvDatosEnemigo = findViewById(R.id.tvDatosEnemigo);

        String nombre = getIntent().getStringExtra("nombre");
        Integer edad = getIntent().getIntExtra("edad", -1);
        String ofensa = getIntent().getStringExtra("ofensa");

        tvDatosEnemigo.setText(nombre + " de " + edad + " años ha cometido la siguiente ofensa: " + ofensa);
    }
}