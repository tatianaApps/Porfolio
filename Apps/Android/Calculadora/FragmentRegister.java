package com.example.coolerlogincalculator.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coolerlogincalculator.MainActivity;
import com.example.coolerlogincalculator.PeticionesApi;
import com.example.coolerlogincalculator.R;
import com.example.coolerlogincalculator.response.ResponseRegister;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FragmentRegister extends Fragment {

    MainActivity mainActivity;
    TextInputLayout tiFullNameRegister, tiUsernameRegister, tiPasswordRegister, tiPasswordConf, tiUserAgeRegister;
    Button btnRegister;
    CheckBox cbConfirmData;
    Button btnGallery;
    //RadioButton rbBoy, rbGirl, rbNeutral;

    PeticionesApi peticionesApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        mainActivity = (MainActivity) requireActivity();

        tiFullNameRegister = view.findViewById(R.id.tiFullNameRegister);
        tiUsernameRegister = view.findViewById(R.id.tiUsernameRegister);
        tiPasswordRegister = view.findViewById(R.id.tiPasswordRegister);
        tiPasswordConf = view.findViewById(R.id.tiPasswordConf);
        tiUserAgeRegister = view.findViewById(R.id.tiUserAgeRegister);
        btnRegister = view.findViewById(R.id.btnRegister);
        cbConfirmData = view.findViewById(R.id.cbConfirmData);
        btnGallery = view.findViewById(R.id.btnGallery);
        //rbBoy = view.findViewById(R.id.rbBoy);
        //rbGirl = view.findViewById(R.id.rbGirl);
        //rbNeutral = view.findViewById(R.id.rbNeutral);

        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().hide();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.154")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        peticionesApi = retrofit.create(PeticionesApi.class);

        /*btnGallery.setOnClickListener(new View.OnClickListener() { //LLEVA AL EXTRA
            @Override
            public void onClick(View v) {
                mainActivity.changeScreen(3);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean checkPolicy = cbConfirmData.isChecked();
                Boolean genderSelected = (rbBoy.isChecked() || rbGirl.isChecked() || rbNeutral.isChecked());

                int userAge;
                if (tiUserAgeRegister.getEditText().getText().toString().equals("")) {
                    userAge = -1;
                } else {
                    userAge = Integer.parseInt(tiUserAgeRegister.getEditText().getText().toString());
                }

                if (checkPolicy && genderSelected) {
                    if (tiFullNameRegister.getEditText().getText().toString().isEmpty() ||
                            tiUsernameRegister.getEditText().getText().toString().isEmpty() ||
                            tiPasswordRegister.getEditText().getText().toString().isEmpty() ||
                            tiPasswordConf.getEditText().getText().toString().isEmpty() ||
                            tiUserAgeRegister.getEditText().getText().toString().isEmpty()
                    ) {
                        Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
                    } else if (mainActivity.checkIfUserExists(tiUsernameRegister.getEditText().getText().toString())) {
                        Toast.makeText(requireContext(), "El usuario ya existe", Toast.LENGTH_LONG).show();
                    } else if (tiPasswordRegister.equals(tiPasswordConf)) {
                        tiPasswordRegister.setError("Las contraseñas no coinciden");
                    } else if (userAge < 18 || userAge > 100) {
                        tiUserAgeRegister.setError("La edad tiene que estar comprendida entre 18 y 100 años");
                    } else {
                        mainActivity.registerUser(new UserModel(
                                tiFullNameRegister.getEditText().getText().toString(),
                                Integer.parseInt(tiUserAgeRegister.getEditText().getText().toString()),
                                tiUsernameRegister.getEditText().getText().toString(),
                                tiPasswordRegister.getEditText().getText().toString()
                        ));
                        Toast.makeText(requireContext(), "Registrado correctamente", Toast.LENGTH_LONG).show();
                        mainActivity.updateConfig();
                        mainActivity.onBackPressed();
                    }
                } else {
                    cbConfirmData.setError("Debes aceptar los términos y condiciones y elegir tu género");
                }
            }
        });*/

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkPolicy = cbConfirmData.isChecked();

                int userAge;
                if (tiUserAgeRegister.getEditText().getText().toString().equals("")) {
                    userAge = -1;
                } else {
                    userAge = Integer.parseInt(tiUserAgeRegister.getEditText().getText().toString());
                }

                if (checkPolicy) {
                    if (tiFullNameRegister.getEditText().getText().toString().isEmpty() ||
                            tiUsernameRegister.getEditText().getText().toString().isEmpty() ||
                            tiPasswordRegister.getEditText().getText().toString().isEmpty() ||
                            tiPasswordConf.getEditText().getText().toString().isEmpty() ||
                            tiUserAgeRegister.getEditText().getText().toString().isEmpty()
                    ) {
                        Toast.makeText(requireContext(), "Rellena todos los campos", Toast.LENGTH_LONG).show();
                    } else if (mainActivity.checkIfUserExists(tiUsernameRegister.getEditText().getText().toString())) {
                        Toast.makeText(requireContext(), "El usuario ya existe", Toast.LENGTH_LONG).show();
                    } else if (tiPasswordRegister.equals(tiPasswordConf)) {
                        tiPasswordRegister.setError("Las contraseñas no coinciden");
                    } else if (userAge < 18 || userAge > 100) {
                        tiUserAgeRegister.setError("La edad tiene que estar comprendida entre 18 y 100 años");
                    } else {
                        tiFullNameRegister.setError(null);
                        tiUsernameRegister.setError(null);
                        tiPasswordRegister.setError(null);
                        tiPasswordConf.setError(null);
                        tiUserAgeRegister.setError(null);

                        peticionesApi.registro(
                                tiFullNameRegister.getEditText().getText().toString(),
                                tiUsernameRegister.getEditText().getText().toString(),
                                tiPasswordRegister.getEditText().getText().toString(),
                                tiUserAgeRegister.getEditText().getText().toString()
                        ).enqueue(new Callback<ResponseRegister>() {
                            @Override
                            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {
                                Toast.makeText(getContext(), response.body().value, Toast.LENGTH_LONG).show();
                                mainActivity.changeScreen(4, false);
                            }

                            @Override
                            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                                Toast.makeText(getContext(), "Usuario ya registrado, pruebe de nuevo", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } else {
                    cbConfirmData.setError("Debes aceptar los términos y condiciones");
                }
            }
        });

        return view;
    }
}
