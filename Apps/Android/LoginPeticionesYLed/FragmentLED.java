package com.example.complete_app.view;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.complete_app.MainActivity;
import com.example.complete_app.PeticionesApi;
import com.example.complete_app.R;
import com.example.complete_app.response.ResponseLED;
import com.example.complete_app.response.ResponseLedStatus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FragmentLED extends Fragment {

    MainActivity mainActivity;
    Button btnOn, btnOff;
    TextView tvLedStatus;

    PeticionesApi peticionesApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_led, container, false);

        mainActivity = (MainActivity) requireActivity();

        btnOn = view.findViewById(R.id.btnOn);
        btnOff = view.findViewById(R.id.btnOff);
        tvLedStatus = view.findViewById(R.id.tvLedStatus);

        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().show();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.139")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        peticionesApi = retrofit.create(PeticionesApi.class);

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peticionesApi.led("1").enqueue(new Callback<ResponseLED>() {
                    @Override
                    public void onResponse(Call<ResponseLED> call, Response<ResponseLED> response) {
                        Toast.makeText(requireContext(), "LED encendido", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseLED> call, Throwable t) {
                        Toast.makeText(requireContext(), "El LED no funciona", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peticionesApi.led("0").enqueue(new Callback<ResponseLED>() {
                    @Override
                    public void onResponse(Call<ResponseLED> call, Response<ResponseLED> response) {
                        Toast.makeText(requireContext(), "LED apagado", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseLED> call, Throwable t) {
                        Toast.makeText(requireContext(), "Algo no funciona", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        statusRefresh();
        return view;
    }

    public void statusRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                peticionesApi.statusLed("1").enqueue(new Callback<ResponseLedStatus>() {
                    @Override
                    public void onResponse(Call<ResponseLedStatus> call, Response<ResponseLedStatus> response) {
                        if (response.body().response.get(0).equals("On")) {
                            tvLedStatus.setText(("LED Encendido"));
                        } else {
                            tvLedStatus.setText(("LED Apagado"));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLedStatus> call, Throwable t) {
                        Toast.makeText(requireContext(), "El LED no funciona", Toast.LENGTH_LONG).show();
                    }
                });
                statusRefresh();
            }
        }, 5000);
    }
}
