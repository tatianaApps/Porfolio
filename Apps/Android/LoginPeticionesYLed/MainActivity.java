package com.example.complete_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableResource;
import com.example.complete_app.dialogs.DialogInfo;
import com.example.complete_app.models.ConfigModel;
import com.example.complete_app.models.UserModel;
import com.example.complete_app.view.FragmentHome;
import com.example.complete_app.view.FragmentLED;
import com.example.complete_app.view.FragmentLogin;
import com.example.complete_app.view.FragmentRegister;
import com.example.complete_app.view.FragmentSensor;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static com.example.complete_app.BitmapUtils.convertToString;

public class MainActivity extends AppCompatActivity {

    final String KEY_PREFERENCES_CONFIG = "config";
    public static final String KEY_PREFERENCES_DB = "baseApp";

    final int CODE_PERMISSIONS = 0;
    final int CODE_CAMERA = 1;
    final int CODE_GALLERY = 2;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;

    public ConfigModel appConfig;

    FragmentLogin loginFragment;
    FragmentRegister registerFragment;
    FragmentHome homeFragment;
    FragmentLED ledFragment;
    FragmentSensor sensorFragment;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    ListView listView;

    PeticionesApi peticionesApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{CAMERA, READ_EXTERNAL_STORAGE}, CODE_PERMISSIONS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.154")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        peticionesApi = retrofit.create(PeticionesApi.class);

        sharedPreferences = getSharedPreferences(KEY_PREFERENCES_DB, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();

        String configSaved = sharedPreferences.getString(KEY_PREFERENCES_CONFIG, "");
        if (configSaved.isEmpty()) {
            appConfig = new ConfigModel();
        } else {
            appConfig = gson.fromJson(configSaved, ConfigModel.class);
        }

        loginFragment = new FragmentLogin();
        registerFragment = new FragmentRegister();
        homeFragment = new FragmentHome();
        ledFragment = new FragmentLED();
        sensorFragment = new FragmentSensor();

        listView = findViewById(R.id.lvOptions);

        drawerLayout = findViewById(R.id.dlContainer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_menu, R.string.close_menu);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                drawerLayout.closeDrawers();
                DialogInfo dialogInfo = new DialogInfo();
                if (i == 0) {
                    changeScreen(2, false);
                } else if (i == 1) {
                    changeScreen(3, false);
                } else if (i == 2) {
                    changeScreen(4, false);
                } else if (i == 3) {
                    dialogInfo.show(getSupportFragmentManager(), "Info");
                }
            }
        });

        fragmentManager = getSupportFragmentManager();
        if (appConfig.rememberUser) {
            changeScreen(2, false);
        } else {
            changeScreen(0, false);
        }
    }

    public void changeScreen(int selection) {
        changeScreen(selection, true);
    }

    public void changeScreen(int selection, boolean addToBack) {
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (selection) {
            case 0:
                fragmentTransaction.replace(R.id.flFragments, loginFragment, "login");
                break;
            case 1:
                fragmentTransaction.replace(R.id.flFragments, registerFragment, "register");
                break;
            case 2:
                fragmentTransaction.replace(R.id.flFragments, homeFragment, "home");
                break;
            case 3:
                fragmentTransaction.replace(R.id.flFragments, ledFragment, "led");
                break;
            case 4:
                fragmentTransaction.replace(R.id.flFragments, sensorFragment, "sensor");
                break;
        }

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (addToBack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    public boolean checkIfUserExists(String username) {
        boolean value = false;
        for (int i = 0; i < appConfig.users.size(); i++) {
            if (appConfig.users.get(i).username.equals(username)) {
                value = true;
                break;
            }
        }
        return value;
    }

    public boolean checkIfCorrectLogin(String username, String password, boolean isRemember) {
        boolean value = false;
        for (int i = 0; i < appConfig.users.size(); i++) {
            if (appConfig.users.get(i).username.equals(username) && appConfig.users.get(i).password.equals(password)) {
                value = true;
                appConfig.currentUser = appConfig.users.get(i);
                appConfig.rememberUser = isRemember;
                updateConfig();
                break;
            }
        }
        return value;
    }

    public void registerUser(UserModel newUser) {
        appConfig.users.add(newUser);
        updateConfig();
    }

    public void updateConfig() {
        editor.putString(KEY_PREFERENCES_CONFIG, gson.toJson(appConfig));
        editor.apply();
    }

    public void updatePhotoConfig(String user, String photoUser) {
        editor.putString(user, photoUser);
        editor.apply();
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODE_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(getApplicationContext(), "Cámara rechazada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Cámara aceptada", Toast.LENGTH_SHORT).show();
            }

            if (grantResults[1] == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(getApplicationContext(), "Galería rechazada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Galería aceptada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA:
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    Glide.with(this.getApplicationContext())
                            .load(bitmap)
                            .into(homeFragment.ivhome);
                    updatePhotoConfig(appConfig.currentUser.username, BitmapUtils.convertToString(bitmap));
                    updateConfig();
                    break;
                case CODE_GALLERY:
                    try {
                        Uri path = data.getData();
                        InputStream stream = getContentResolver().openInputStream(path);
                        Bitmap bitmap2 = BitmapFactory.decodeStream(stream);
                        Glide.with(this.getApplicationContext())
                                .load(bitmap2)
                                .into(homeFragment.ivhome);
                        updatePhotoConfig(appConfig.currentUser.username, BitmapUtils.convertToString(bitmap2));
                        updateConfig();
                    } catch (FileNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    break;
            }
        }

    }
}