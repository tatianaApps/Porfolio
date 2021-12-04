package com.example.complete_app.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableResource;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.complete_app.BitmapUtils;
import com.example.complete_app.MainActivity;
import com.example.complete_app.R;
import com.example.complete_app.dialogs.DialogCamera;
import com.example.complete_app.models.ConfigModel;
import com.google.gson.Gson;

public class FragmentHome extends Fragment {

    TextView tvHomeName, tvHomeAge;
    Button btnCamera;
    public ImageView ivhome;
    MainActivity mainActivity;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mainActivity = (MainActivity) requireActivity();
        tvHomeName = view.findViewById(R.id.tvHomeName);
        tvHomeAge = view.findViewById(R.id.tvHomeAge);
        ivhome = view.findViewById(R.id.ivHome);
        btnCamera = view.findViewById(R.id.btnCamera);

        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().show();
        }

        sharedPreferences = mainActivity.getSharedPreferences(mainActivity.KEY_PREFERENCES_DB, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();

        String photoSaved = sharedPreferences.getString(mainActivity.appConfig.currentUser.username, "");
        if (photoSaved.isEmpty()) {
            Glide.with(this.getContext())
                    .load(R.drawable.ribon)
                    .into(ivhome);
        } else {
            Bitmap savedPhoto = BitmapUtils.convertToBitmap(photoSaved);
            Glide.with(this.getContext())
                    .load(savedPhoto)
                    .into(ivhome);
        }

        tvHomeName.setText(mainActivity.appConfig.currentUser.name);
        tvHomeAge.setText("de " + mainActivity.appConfig.currentUser.age + " años");
        mainActivity.updateConfig();

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCamera dialogCamera = new DialogCamera();
                dialogCamera.show(mainActivity.getSupportFragmentManager(), "Camera");
            }
        });

        return view;
    }
}
