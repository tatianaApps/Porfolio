package com.example.coolerlogincalculator.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.coolerlogincalculator.MainActivity;
import com.example.coolerlogincalculator.R;
import com.example.coolerlogincalculator.models.UserModel;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentConf extends Fragment {

    MainActivity mainActivity;
    TextInputLayout tiFullNameChange, tiUsernameChange, tiPasswordChange, tiUserAgeChange;
    Button btnChangeData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conf, container, false);

        mainActivity = (MainActivity) requireActivity();
        tiFullNameChange = view.findViewById(R.id.tiFullNameChange);
        tiUsernameChange = view.findViewById(R.id.tiUsernameChange);
        tiPasswordChange = view.findViewById(R.id.tiPasswordChange);
        tiUserAgeChange = view.findViewById(R.id.tiUserAgeChange);
        btnChangeData = view.findViewById(R.id.btnChangeData);

        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().show();
        }

        tiFullNameChange.getEditText().setText(mainActivity.appConfig.currentUser.name);
        tiUsernameChange.getEditText().setText(mainActivity.appConfig.currentUser.username);
        tiPasswordChange.getEditText().setText(mainActivity.appConfig.currentUser.password);
        tiUserAgeChange.getEditText().setText("" + mainActivity.appConfig.currentUser.age);

        btnChangeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userAge;
                if (tiUserAgeChange.getEditText().getText().toString().equals("")) {
                    userAge = -1;
                } else {
                    userAge = Integer.parseInt(tiUserAgeChange.getEditText().getText().toString());
                }

                if (tiFullNameChange.getEditText().getText().toString().isEmpty() ||
                        tiUsernameChange.getEditText().getText().toString().isEmpty() ||
                        tiPasswordChange.getEditText().getText().toString().isEmpty() ||
                        tiUserAgeChange.getEditText().getText().toString().isEmpty()
                ) {
                    Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
                } else if (mainActivity.checkIfUserExists(tiUsernameChange.getEditText().getText().toString())) {
                    Toast.makeText(requireContext(), "El usuario ya existe", Toast.LENGTH_LONG).show();
                } else if (userAge < 18 || userAge > 100) {
                    tiUserAgeChange.setError("La edad tiene que estar comprendida entre 18 y 100 años");
                } else {
                    mainActivity.appConfig.currentUser.name = tiFullNameChange.getEditText().getText().toString();
                    mainActivity.appConfig.currentUser.username = tiUsernameChange.getEditText().getText().toString();
                    mainActivity.appConfig.currentUser.password = tiPasswordChange.getEditText().getText().toString();
                    mainActivity.appConfig.currentUser.age = Integer.parseInt(tiUserAgeChange.getEditText().getText().toString());
                    mainActivity.updateConfig();
                    mainActivity.changeScreen(4, false);
                    Toast.makeText(requireContext(), "Cambiado correctamente", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
