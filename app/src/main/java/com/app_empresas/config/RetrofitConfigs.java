package com.app_empresas.config;

import android.util.Log;

import com.app_empresas.BuildConfig;
import com.app_empresas.api.IDataService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfigs {

    public static String access_token = "";
    public static String uid = "";
    public static String client = "";
    public static String baseUrl = "https://empresas.ioasys.com.br";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public IDataService serviceAppEmpresas(){
        return retrofit.create(IDataService.class);
    }
}
