package com.example.coolerlogincalculator.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coolerlogincalculator.MainActivity;
import com.example.coolerlogincalculator.R;

import java.util.ArrayList;

public class FragmentMeme extends Fragment {

    MainActivity mainActivity;
    ImageView ivMemes;
    int[] images = new int[]{R.drawable.meme1, R.drawable.meme2, R.drawable.meme3, R.drawable.meme4};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meme, container, false);

        mainActivity = (MainActivity) requireActivity();

        ivMemes = view.findViewById(R.id.ivMemes);

        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().hide();
        }

        int imageId = (int) (Math.random() * images.length);
        ivMemes.setBackgroundResource(images[imageId]);

        return view;
    }
}
