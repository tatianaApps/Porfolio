package com.example.complete_app.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.complete_app.MainActivity;

public class DialogCamera extends DialogFragment {

    MainActivity mainActivity;

    final int CODE_CAMERA = 1;
    final int CODE_GALLERY = 2;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mainActivity = (MainActivity) requireActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Selecciona tu foto de perfil");

        builder.setPositiveButton("Cámara", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mainActivity.startActivityForResult(openCamera, CODE_CAMERA);
            }
        });

        builder.setNeutralButton("Galería", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent openGallery = new Intent(Intent.ACTION_PICK);
                openGallery.setType("image/*");
                mainActivity.startActivityForResult(openGallery, CODE_GALLERY);
            }
        });

        return builder.create();
    }
}
