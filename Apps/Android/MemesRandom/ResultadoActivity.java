package com.example.memesrandom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.HashMap;

public class ResultadoActivity extends AppCompatActivity {

    ImageView ivMeme;

    //HashMap general
    HashMap<Integer, Integer> memes = new HashMap();

    //Array por categoría
    int[] categoriaAnimales = new int[]{R.drawable.animales1, R.drawable.animales2, R.drawable.animales3, R.drawable.animales4};
    int[] categoriaAnime = new int[]{R.drawable.anime1, R.drawable.anime2, R.drawable.anime3, R.drawable.anime4};
    int[] categoriaMarvel = new int[]{R.drawable.marvel1, R.drawable.marvel2, R.drawable.marvel3, R.drawable.marvel4};
    int[] categoriaSarcasmo = new int[]{R.drawable.sarcasmo1, R.drawable.sarcasmo2, R.drawable.sarcasmo3, R.drawable.sarcasmo4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        ivMeme = findViewById(R.id.ivMeme);
        categoriasHash();

        String categorias = getIntent().getStringExtra("categorias");

        if (categorias.equals("1")) {
            int imageId = (int) (Math.random() * categoriaAnimales.length);
            ivMeme.setBackgroundResource(categoriaAnimales[imageId]);
        } else if (categorias.equals("2")) {
            int imageId2 = (int) (Math.random() * categoriaAnime.length);
            ivMeme.setBackgroundResource(categoriaAnime[imageId2]);
        } else if (categorias.equals("3")) {
            int imageId3 = (int) (Math.random() * categoriaMarvel.length);
            ivMeme.setBackgroundResource(categoriaMarvel[imageId3]);
        } else if (categorias.equals("4")) {
            int imageId4 = (int) (Math.random() * categoriaSarcasmo.length);
            ivMeme.setBackgroundResource(categoriaSarcasmo[imageId4]);
        } else if (categorias.equals("5")) {
            int imageRandom = (int) (Math.random() * memes.size());
            ivMeme.setBackgroundResource(memes.get(imageRandom));
        }
    }

    private void categoriasHash() {
        memes.put(1, R.drawable.animales1);
        memes.put(2, R.drawable.animales2);
        memes.put(3, R.drawable.animales3);
        memes.put(4, R.drawable.animales4);

        memes.put(5, R.drawable.anime1);
        memes.put(6, R.drawable.anime2);
        memes.put(7, R.drawable.anime3);
        memes.put(8, R.drawable.anime4);

        memes.put(9, R.drawable.marvel1);
        memes.put(10, R.drawable.marvel2);
        memes.put(11, R.drawable.marvel3);
        memes.put(12, R.drawable.marvel4);

        memes.put(13, R.drawable.sarcasmo1);
        memes.put(14, R.drawable.sarcasmo2);
        memes.put(15, R.drawable.sarcasmo3);
        memes.put(16, R.drawable.sarcasmo4);
    }
}