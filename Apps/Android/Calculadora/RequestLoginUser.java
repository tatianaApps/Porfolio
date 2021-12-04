package com.example.coolerlogincalculator.request;

public class RequestLoginUser {

    public String user;
    public String pass;

    public RequestLoginUser(String user, String pass){
        this.user = user;
        this.pass = pass;
    }
}
