package com.example.coolerlogincalculator.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coolerlogincalculator.MainActivity;
import com.example.coolerlogincalculator.R;
import com.example.coolerlogincalculator.dialogs.DialogInfo;

public class FragmentHome extends Fragment {

    TextView tvHomeName, tvHomeAge;
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mainActivity = (MainActivity) requireActivity();
        tvHomeName = view.findViewById(R.id.tvHomeName);
        tvHomeAge = view.findViewById(R.id.tvHomeAge);


        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().show();
        }

        tvHomeName.setText(mainActivity.appConfig.currentUser.name);
        tvHomeAge.setText("de " + mainActivity.appConfig.currentUser.age + " años");
        mainActivity.updateConfig();

        return view;
    }

}
