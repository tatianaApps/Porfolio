package com.example.coolerlogincalculator.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coolerlogincalculator.MainActivity;
import com.example.coolerlogincalculator.PeticionesApi;
import com.example.coolerlogincalculator.R;
import com.example.coolerlogincalculator.response.ResponseBands;
import com.example.coolerlogincalculator.adapter.MetalAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FragmentMetal extends Fragment {

    MainActivity mainActivity;
    Button btnTitleMetal;
    TextView tvSingerName;
    TextView tvSingerDescription;
    ImageView ivSinger;

    ListView lvListMetal;

    PeticionesApi peticionesApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_metal, container, false);

        mainActivity = (MainActivity) requireActivity();
        btnTitleMetal = view.findViewById(R.id.btnTitleMetal);
        tvSingerName = view.findViewById(R.id.tvSingerName);
        tvSingerDescription = view.findViewById(R.id.tvSingerDescription);
        ivSinger = view.findViewById(R.id.ivSinger);
        lvListMetal = view.findViewById(R.id.lvListMetal);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://superapi.netlify.app/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        peticionesApi = retrofit.create(PeticionesApi.class);

        btnTitleMetal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peticionesApi.obtenerMetal().enqueue(new Callback<ResponseBands>() {
                    @Override
                    public void onResponse(Call<ResponseBands> call, Response<ResponseBands> response) {
                        lvListMetal.setAdapter(new MetalAdapter(getContext(), R.layout.celda_avanzada_metal, response.body().grupos));
                    }

                    @Override
                    public void onFailure(Call<ResponseBands> call, Throwable t) {
                        Toast.makeText(getContext(), "K.O", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/

        return view;
    }
}
