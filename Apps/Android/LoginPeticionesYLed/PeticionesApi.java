package com.example.complete_app;

import com.example.complete_app.response.ResponseLED;
import com.example.complete_app.response.ResponseLedStatus;
import com.example.complete_app.response.ResponseLogin;
import com.example.complete_app.response.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PeticionesApi {

    @FormUrlEncoded
    @POST("/hola/login.php")
    Call<ResponseLogin> login(@Field("user") String user, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("/hola/registro.php")
    Call<ResponseRegister> register(@Field("username") String username, @Field("password") String password, @Field("passConf") String passConf, @Field("name") String name, @Field("userAge") String userAge);

    @FormUrlEncoded
    @POST("/hola/led_on_off.php")
    Call<ResponseLED> led(@Field("status") String status);

    @FormUrlEncoded
    @POST("/hola/comprobar_led.php")
    Call<ResponseLedStatus> statusLed(@Field("status") String status);
}
