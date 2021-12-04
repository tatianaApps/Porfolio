package com.example.coolerlogincalculator.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coolerlogincalculator.MainActivity;
import com.example.coolerlogincalculator.R;

public class FragmentRecover extends Fragment {

    MainActivity mainActivity;
    Button btnRecover;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recover, container, false);

        mainActivity = (MainActivity) requireActivity();
        btnRecover = view.findViewById(R.id.btnRecover);

        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().hide();
        }

        btnRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onBackPressed();
                Toast.makeText(getContext(),"Email de recuperación enviado",Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
