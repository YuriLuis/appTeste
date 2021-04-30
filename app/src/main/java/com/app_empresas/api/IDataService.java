package com.app_empresas.api;

import android.util.JsonReader;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IDataService {

    @FormUrlEncoded
    @POST("/api/v1/users/auth/sign_in")
    Call<Void> tryLoginRequest(@Field("email") String email,
                               @Field("password") String password);
    @GET("/api/v1/enterprises")
    Call<JsonObject> getEntreprises(@Header("access-token") String acessToken,
                                    @Header("uid") String uid,
                                    @Header("client") String client

    );

    @GET("{parametro}")
    Call<JsonObject> getPhotos(@Path("parametro") String parametro

    );
}
