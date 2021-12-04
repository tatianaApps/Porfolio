package com.example.memesrandom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnCategoria1, btnCategoria2, btnCategoria3, btnCategoria4, btnRandom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCategoria1 = findViewById(R.id.btnCategoria1);
        btnCategoria2 = findViewById(R.id.btnCategoria2);
        btnCategoria3 = findViewById(R.id.btnCategoria3);
        btnCategoria4 = findViewById(R.id.btnCategoria4);
        btnRandom = findViewById(R.id.btnRandom);

        btnCategoria1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                intent.putExtra("categorias", "1");
                startActivity(intent);
            }
        });

        btnCategoria2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                intent.putExtra("categorias", "2");
                startActivity(intent);
            }
        });

        btnCategoria3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                intent.putExtra("categorias", "3");
                startActivity(intent);
            }
        });

        btnCategoria4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                intent.putExtra("categorias", "4");
                startActivity(intent);
            }
        });

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ResultadoActivity.class);
                intent.putExtra("categorias", "5");
                startActivity(intent);
            }
        });
    }
}