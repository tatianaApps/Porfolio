package com.example.townsspain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etUserName, etUserDate;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserName = findViewById(R.id.etUserName);
        etUserDate = findViewById(R.id.etUserDate);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUserName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplication(), "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                } else if (etUserDate.getText().toString().isEmpty()) {
                    Toast.makeText(getApplication(), "La fecha no puede estar vacía", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String nombre = etUserName.getText().toString();
                        String fechaNacimiento[] = etUserDate.getText().toString().split("-");
                        String diaNacimiento = fechaNacimiento[0];
                        String mesNacimiento = fechaNacimiento[1];
                        String añoNacimiento = fechaNacimiento[2];
                        Intent intent = new Intent(getApplicationContext(), FraseResultadoActivity.class);

                        intent.putExtra("nombre", nombre);
                        intent.putExtra("diaNacimiento", diaNacimiento);
                        intent.putExtra("mesNacimiento", mesNacimiento);
                        startActivity(intent);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        etUserDate.setError("Formato fecha incorrecto");
                        Toast.makeText(getApplicationContext(),"Introduce la fecha en el formato correcto. Ejemplo 26-01-1997",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}