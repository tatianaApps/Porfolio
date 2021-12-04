package com.example.townsspain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class FraseResultadoActivity extends AppCompatActivity {

    TextView tvResultPhrase;
    ImageView ivRandomImage, ivRandomImage2;
    int[] images = new int[]{R.drawable.meme1, R.drawable.meme2, R.drawable.meme3, R.drawable.meme4, R.drawable.meme5, R.drawable.meme6, R.drawable.meme7, R.drawable.meme8, R.drawable.meme9, R.drawable.meme10};
    HashMap<Character, String> listaNombres = new HashMap();
    HashMap<String, String> listaMeses = new HashMap();
    HashMap<String, String> listaDias = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frase_resultado);
        listaHash();

        tvResultPhrase = findViewById(R.id.tvResultPhrase);
        ivRandomImage = findViewById(R.id.ivRandomImage);
        ivRandomImage2 = findViewById(R.id.ivRandomImage2);
        String nombreRecuperado = getIntent().getStringExtra("nombre");
        String diaNacimiento = getIntent().getStringExtra("diaNacimiento");
        String mesNacimiento = getIntent().getStringExtra("mesNacimiento");

        tvResultPhrase.setText("Hola " + obtenerLetra(nombreRecuperado) + " " + obtenerMes(mesNacimiento) + " " + obtenerDia(diaNacimiento));

        // Obtener una imagen random desde el 0 al length -1
        int imageId = (int) (Math.random() * images.length);
        int imageId2 = (int) (Math.random() * images.length);

        ivRandomImage.setBackgroundResource(images[imageId]);
        ivRandomImage2.setBackgroundResource(images[imageId2]);
    }

    private void listaHash() {
        listaNombres.put('A', "Peña");
        listaNombres.put('B', "Villa");
        listaNombres.put('C', "Arena");
        listaNombres.put('D', "Sancho");
        listaNombres.put('E', "Puebla");
        listaNombres.put('F', "Villar");
        listaNombres.put('G', "Valde");
        listaNombres.put('H', "Vega");
        listaNombres.put('I', "Tabla");
        listaNombres.put('J', "Quinta");
        listaNombres.put('K', "Castro");
        listaNombres.put('L', "Nava");
        listaNombres.put('M', "Aldea");
        listaNombres.put('N', "Venta");
        listaNombres.put('O', "Torre");
        listaNombres.put('P', "Fuente");
        listaNombres.put('Q', "Valde");
        listaNombres.put('R', "Casa");
        listaNombres.put('S', "Puebla");
        listaNombres.put('T', "Poza");
        listaNombres.put('U', "Guada");
        listaNombres.put('V', "Moral");
        listaNombres.put('W', "Venta");
        listaNombres.put('X', "Mota");
        listaNombres.put('Y', "Soto");
        listaNombres.put('Z', "Casa");

        listaMeses.put("01", "Nueva");
        listaMeses.put("02", "Vieja");
        listaMeses.put("03", "Alta");
        listaMeses.put("04", "Baja");
        listaMeses.put("05", "Grande");
        listaMeses.put("06", "Llana");
        listaMeses.put("07", "García");
        listaMeses.put("08", "Muriel");
        listaMeses.put("09", "Peral");
        listaMeses.put("10", "Martín");
        listaMeses.put("11", "Castín");
        listaMeses.put("12", "Rabano");

        listaDias.put("1", "de las Torres");
        listaDias.put("2", "de Arriba");
        listaDias.put("3", "de Abajo");
        listaDias.put("4", "de los Infantes");
        listaDias.put("5", "de la Orden");
        listaDias.put("6", "de San Juan");
        listaDias.put("7", "de Calatrava");
        listaDias.put("8", "del Vino");
        listaDias.put("9", "de San Pedro");
        listaDias.put("10", "del Campos");
        listaDias.put("11", "de la Sierra");
        listaDias.put("12", "del Conde");
        listaDias.put("13", "de las Cañas");
        listaDias.put("14", "del Cuervo");
        listaDias.put("15", "del Monte");
        listaDias.put("16", "de Valdeiglesias");
        listaDias.put("17", "de la Encomienda");
        listaDias.put("18", "del Pino");
        listaDias.put("19", "de Carrascosa");
        listaDias.put("20", "del Hoyo");
        listaDias.put("21", "de Matajudíos");
        listaDias.put("22", "de Ríoseco");
        listaDias.put("23", "de la Peña");
        listaDias.put("24", "del Espinar");
        listaDias.put("25", "de Tajuña");
        listaDias.put("26", "del Rey");
        listaDias.put("27", "del Palancar");
        listaDias.put("28", "de la Cuesta");
        listaDias.put("29", "de Bracamonte");
        listaDias.put("30", "de los Caballeros");
        listaDias.put("31", "del Caudillo");
    }

    private String obtenerLetra(String nombre) {
        return listaNombres.get(nombre.charAt(0));
    }

    private String obtenerMes(String mesNacimiento) {
        return listaMeses.get(mesNacimiento);
    }

    private String obtenerDia(String diaNacimiento) {
        return listaDias.get(diaNacimiento);
    }
}