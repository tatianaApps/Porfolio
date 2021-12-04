package com.example.coolerlogincalculator.models;

import com.example.coolerlogincalculator.request.RequestLoginUser;

import java.util.ArrayList;
import java.util.List;

public class ConfigModel {

    public List<UserModel> users;
    public boolean rememberUser;
    public UserModel currentUser; //usuarioActual

    public ConfigModel() {
        users = new ArrayList<>();
    }

}
