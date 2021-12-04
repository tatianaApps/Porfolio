package com.example.coolerlogincalculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.coolerlogincalculator.dialogs.DialogInfo;
import com.example.coolerlogincalculator.models.ConfigModel;
import com.example.coolerlogincalculator.models.UserModel;
import com.example.coolerlogincalculator.view.FragmentCalculator;
import com.example.coolerlogincalculator.view.FragmentConf;
import com.example.coolerlogincalculator.view.FragmentHome;
import com.example.coolerlogincalculator.view.FragmentLogin;
import com.example.coolerlogincalculator.view.FragmentMeme;
import com.example.coolerlogincalculator.view.FragmentMetal;
import com.example.coolerlogincalculator.view.FragmentRecover;
import com.example.coolerlogincalculator.view.FragmentRegister;
import com.example.coolerlogincalculator.view.FragmentTrap;
import com.google.gson.Gson;

import java.io.File;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    final String KEY_PREFERENCES_CONFIG = "config";
    final String KEY_PREFERENCES_DB = "baseApp";

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;

    public ConfigModel appConfig;

    FragmentLogin loginFragment;
    FragmentRegister registerFragment;
    FragmentCalculator calculatorFragment;
    FragmentMeme memeFragment;
    FragmentHome homeFragment;
    FragmentRecover recoverFragment;
    FragmentConf confFragment;
    FragmentTrap trapFragment;
    FragmentMetal metalFragment;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        calculatorFragment = new FragmentCalculator();
        memeFragment = new FragmentMeme();
        homeFragment = new FragmentHome();
        recoverFragment = new FragmentRecover();
        confFragment = new FragmentConf();
        trapFragment = new FragmentTrap();
        metalFragment = new FragmentMetal();

        listView = findViewById(R.id.lvOptions);

        drawerLayout = findViewById(R.id.dlContainer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.close_menu, R.string.close_menu);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                drawerLayout.closeDrawers();
                DialogInfo dialogInfo = new DialogInfo();
                if (i == 0) {
                    changeScreen(4, false);
                } else if (i == 1) {
                    changeScreen(2, false);
                } else if (i == 2) {
                    changeScreen(6, false);
                } else if (i == 3) {
                    changeScreen(7, false);
                } else if (i == 4) {
                    changeScreen(8, false);
                } else if (i == 5) {
                    dialogInfo.show(getSupportFragmentManager(), "Info");
                }
            }
        });

        fragmentManager = getSupportFragmentManager();
        if (appConfig.rememberUser) {
            changeScreen(4, false);
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
                fragmentTransaction.replace(R.id.flFragments, calculatorFragment, "calculator");
                break;
            case 3:
                fragmentTransaction.replace(R.id.flFragments, memeFragment, "meme");
                break;
            case 4:
                fragmentTransaction.replace(R.id.flFragments, homeFragment, "home");
                break;
            case 5:
                fragmentTransaction.replace(R.id.flFragments, recoverFragment, "recover");
                break;
            case 6:
                fragmentTransaction.replace(R.id.flFragments, confFragment, "configuration");
                break;
            case 7:
                fragmentTransaction.replace(R.id.flFragments, trapFragment, "trap");
                break;
            case 8:
                fragmentTransaction.replace(R.id.flFragments, metalFragment, "metal");
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

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
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
}