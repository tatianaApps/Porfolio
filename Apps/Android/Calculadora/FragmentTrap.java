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
import com.example.coolerlogincalculator.response.ResponseTrapItem;
import com.example.coolerlogincalculator.adapter.TrapAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FragmentTrap extends Fragment {

    MainActivity mainActivity;
    Button btnTitleTrap;
    TextView tvSingerName;
    TextView tvSingerGenre;
    ImageView ivSinger;

    ListView lvListTrap;

    PeticionesApi peticionesApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trap, container, false);

        mainActivity = (MainActivity) requireActivity();
        btnTitleTrap = view.findViewById(R.id.btnTitleTrap);
        tvSingerName = view.findViewById(R.id.tvSingerName);
        tvSingerGenre = view.findViewById(R.id.tvSingerGenre);
        ivSinger = view.findViewById(R.id.ivSinger);
        lvListTrap = view.findViewById(R.id.lvListTrap);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://superapi.netlify.app/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        peticionesApi = retrofit.create(PeticionesApi.class);

        btnTitleTrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peticionesApi.obtenerArtistas().enqueue(new Callback<List<ResponseTrapItem>>() {
                    @Override
                    public void onResponse(Call<List<ResponseTrapItem>> call, Response<List<ResponseTrapItem>> response) {
                        lvListTrap.setAdapter(new TrapAdapter(getContext(), R.layout.celda_avanzada, response.body()));
                    }

                    @Override
                    public void onFailure(Call<List<ResponseTrapItem>> call, Throwable t) {
                        Toast.makeText(getContext(), "K.O", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/

        return view;
    }
}
