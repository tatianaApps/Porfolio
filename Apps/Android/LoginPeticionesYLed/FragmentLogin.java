package com.example.complete_app.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.complete_app.MainActivity;
import com.example.complete_app.PeticionesApi;
import com.example.complete_app.R;
import com.example.complete_app.models.UserModel;
import com.example.complete_app.response.ResponseLogin;
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
    TextView tvRegister;

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
        cbRememberUser = view.findViewById(R.id.cbRememberUser);

        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().hide();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.139")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        peticionesApi = retrofit.create(PeticionesApi.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkPolicy = cbConfirmData.isChecked();
                if (checkPolicy) {
                    if (tiUsername.getEditText().getText().toString().isEmpty() || tiPassword.getEditText().getText().toString().isEmpty()) {
                        Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
                    } else {
                        boolean rememberUser = cbRememberUser.isChecked();
                        peticionesApi.login(
                                tiUsername.getEditText().getText().toString(),
                                tiPassword.getEditText().getText().toString()
                        ).enqueue(new Callback<ResponseLogin>() {
                            @Override
                            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                                if (response.body().value.equals("Login correcto")) {
                                    String name = response.body().response.get(1);
                                    int age = Integer.parseInt(response.body().response.get(2));
                                    String username = tiUsername.getEditText().getText().toString();
                                    String password = response.body().response.get(0);
                                    mainActivity.appConfig.currentUser = new UserModel(name, age, username, password);
                                    mainActivity.appConfig.rememberUser = rememberUser;
                                    mainActivity.updateConfig();
                                    mainActivity.changeScreen(2, false);

                                } else {
                                    Toast.makeText(requireContext(), response.body().value, Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                                Toast.makeText(requireContext(), "Error login", Toast.LENGTH_LONG).show();
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

        return view;
    }
}