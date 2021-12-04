package com.example.coolerlogincalculator.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.coolerlogincalculator.MainActivity;
import com.example.coolerlogincalculator.R;

import java.util.ArrayList;

public class FragmentCalculator extends Fragment {

    MainActivity mainActivity;
    EditText etToolbar;
    Button btnUno, btnDos, btnTres, btnCuatro, btnCinco, btnSeis, btnSiete, btnOcho, btnNueve, btnCero, btnPorcentaje, btnRaiz, btnMultiplicar, btnDividir, btnMas, btnMenos, btnPunto, btnCambioSigno, btnIgual;
    String mostrar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        mainActivity = (MainActivity) getActivity();
        etToolbar = view.findViewById(R.id.etToolbar);
        btnUno = view.findViewById(R.id.btnUno);
        btnDos = view.findViewById(R.id.btnDos);
        btnTres = view.findViewById(R.id.btnTres);
        btnCuatro = view.findViewById(R.id.btnCuatro);
        btnCinco = view.findViewById(R.id.btnCinco);
        btnSeis = view.findViewById(R.id.btnSeis);
        btnSiete = view.findViewById(R.id.btnSiete);
        btnOcho = view.findViewById(R.id.btnOcho);
        btnNueve = view.findViewById(R.id.btnNueve);
        btnCero = view.findViewById(R.id.btnCero);
        btnPorcentaje = view.findViewById(R.id.btnPorcentaje);
        btnRaiz = view.findViewById(R.id.btnRaiz);
        btnMultiplicar = view.findViewById(R.id.btnMultiplicar);
        btnDividir = view.findViewById(R.id.btnDividir);
        btnMas = view.findViewById(R.id.btnMas);
        btnMenos = view.findViewById(R.id.btnMenos);
        btnPunto = view.findViewById(R.id.btnPunto);
        btnCambioSigno = view.findViewById(R.id.btnCambioSigno);
        btnIgual = view.findViewById(R.id.btnIgual);

        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().show();
        }

        btnUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = etToolbar.getText().toString();
                mostrar = mostrar + "1";
                etToolbar.setText(mostrar);
            }
        });

        btnDos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = etToolbar.getText().toString();
                mostrar = mostrar + "2";
                etToolbar.setText(mostrar);
            }
        });

        btnTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = etToolbar.getText().toString();
                mostrar = mostrar + "3";
                etToolbar.setText(mostrar);
            }
        });

        btnCuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = etToolbar.getText().toString();
                mostrar = mostrar + "4";
                etToolbar.setText(mostrar);
            }
        });

        btnCinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = etToolbar.getText().toString();
                mostrar = mostrar + "5";
                etToolbar.setText(mostrar);
            }
        });

        btnSeis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = etToolbar.getText().toString();
                mostrar = mostrar + "6";
                etToolbar.setText(mostrar);
            }
        });

        btnSiete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = etToolbar.getText().toString();
                mostrar = mostrar + "7";
                etToolbar.setText(mostrar);
            }
        });

        btnOcho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = etToolbar.getText().toString();
                mostrar = mostrar + "8";
                etToolbar.setText(mostrar);
            }
        });

        btnNueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = etToolbar.getText().toString();
                mostrar = mostrar + "9";
                etToolbar.setText(mostrar);
            }
        });

        btnCero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar = etToolbar.getText().toString();
                mostrar = mostrar + "0";
                etToolbar.setText(mostrar);
            }
        });

        btnPorcentaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnRaiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCambioSigno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }
}
