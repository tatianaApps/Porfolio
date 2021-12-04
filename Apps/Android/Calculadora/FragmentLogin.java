package com.example.coolerlogincalculator.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coolerlogincalculator.MainActivity;
import com.example.coolerlogincalculator.PeticionesApi;
import com.example.coolerlogincalculator.R;
import com.example.coolerlogincalculator.response.ResponseLogin;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FragmentLogin extends Fragment {

    MainActivity mainActivity;
    TextInputLayout tiUsername, tiPassword;
    CheckBox cbConfirmData, cbRememberUser;
    Button btnLogin;
    TextView tvRegister, tvForgotPassword;

    PeticionesApi peticionesApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mainActivity = (MainActivity) requireActivity();

        tiUsername = view.findViewById(R.id.tiUsername);
        tiPassword = view.findViewById(R.id.tiPassword);
        cbConfirmData = view.findViewById(R.id.cbConfirmData);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvRegister = view.findViewById(R.id.tvRegister);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        cbRememberUser = view.findViewById(R.id.cbRememberUser);

        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().hide();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.154")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        peticionesApi = retrofit.create(PeticionesApi.class);

        /*btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkPolicy = cbConfirmData.isChecked();

                if (checkPolicy) {
                    if (tiUsername.getEditText().getText().toString().isEmpty() || tiPassword.getEditText().getText().toString().isEmpty()) {
                        Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
                    } else if (mainActivity.checkIfCorrectLogin(
                            tiUsername.getEditText().getText().toString(),
                            tiPassword.getEditText().getText().toString(),
                            cbRememberUser.isChecked())
                    ) {
                        mainActivity.changeScreen(4);
                    } else {
                        Toast.makeText(requireContext(), "LOGIN INCORRECTO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    cbConfirmData.setError("Debes aceptar los términos y condiciones");
                }
            }
        });*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkPolicy = cbConfirmData.isChecked();
                if (checkPolicy) {
                    if (tiUsername.getEditText().getText().toString().isEmpty() || tiPassword.getEditText().getText().toString().isEmpty()) {
                        Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
                    } else {
                        peticionesApi.login(
                                tiUsername.getEditText().getText().toString(),
                                tiPassword.getEditText().getText().toString(),
                                cbRememberUser.isChecked()
                        ).enqueue(new Callback<ResponseLogin>() {
                            @Override
                            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                                if (response.body().value.equals("Login correcto")) { 
                                    Toast.makeText(requireContext(), "Acceso permitido", Toast.LENGTH_LONG).show();
                                    mainActivity.changeScreen(4, false);
                                } else {
                                    Toast.makeText(requireContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                                Toast.makeText(requireContext(), "KO", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } else {
                    cbConfirmData.setError("Debes aceptar los términos y condiciones");
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.changeScreen(1);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.changeScreen(5);
            }
        });

        return view;
    }
}
