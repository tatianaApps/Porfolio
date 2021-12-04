package com.example.complete_app.models;

import java.util.ArrayList;
import java.util.List;

public class ConfigModel {

    public List<UserModel> users;
    public boolean rememberUser;
    public UserModel currentUser;

    public ConfigModel() {
        users = new ArrayList<>();
        currentUser = new UserModel();
        rememberUser = false;
    }
}
