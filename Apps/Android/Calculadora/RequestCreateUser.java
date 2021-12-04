package com.example.coolerlogincalculator.request;

public class RequestCreateUser {
    public String fullname;
    public String username;
    public String password;
    public String passwordConf;
    public int userAge;

    public RequestCreateUser(String fullname, String username, String password, String passwordConf, int userAge){
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.passwordConf = passwordConf;
        this.userAge = userAge;

    }

}
