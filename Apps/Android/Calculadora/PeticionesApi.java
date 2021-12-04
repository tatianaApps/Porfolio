package com.example.coolerlogincalculator;

import com.example.coolerlogincalculator.response.ResponseBands;
import com.example.coolerlogincalculator.response.ResponseLogin;
import com.example.coolerlogincalculator.response.ResponseRegister;
import com.example.coolerlogincalculator.response.ResponseTrapItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PeticionesApi {

    @GET("trapi")
    Call<List<ResponseTrapItem>> obtenerArtistas();

    @GET("bands")
    Call<ResponseBands> obtenerMetal();

    @FormUrlEncoded
    @POST("/hola/registro.php")
    Call<ResponseRegister> registro(@Field("fullname") String fullname, @Field("username") String username, @Field("password") String password, @Field("userAge") String userAge);

    @FormUrlEncoded
    @POST("/hola/login.php")
    Call<ResponseLogin> login(@Field("user") String user, @Field("pass") String pass, @Field("rememberUser") boolean rememberUser);


}
