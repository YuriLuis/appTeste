package com.app_empresas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;

import com.app_empresas.R;
import com.app_empresas.config.RetrofitConfigs;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText textEmail, textPassword;
    private Button buttonEntrar;
    private TextView usuarioInvalido;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        clickButtonEntrar();
    }

    private void initComponents(){
        textEmail = findViewById(R.id.textEmail);
        textPassword = findViewById(R.id.textPassword);
        buttonEntrar = findViewById(R.id.buttonEntrar);
        usuarioInvalido = findViewById(R.id.textCrendencias);
        usuarioInvalido.setVisibility(View.INVISIBLE);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void clickButtonEntrar(){
        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(campoNaoValido()){
                    progressBar.setVisibility(View.VISIBLE);
                    postLoginResquest();
                }
            }
        });
    }
    private void postLoginResquest() {
        Call<Void> call = new RetrofitConfigs().serviceAppEmpresas().tryLoginRequest(
                textEmail.getText().toString(), textPassword.getText().toString()
        );

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("response", response.headers().get("client"));
                    RetrofitConfigs.access_token = response.headers().get("access-token");
                    RetrofitConfigs.uid = response.headers().get("uid");
                    RetrofitConfigs.client = response.headers().get("client");

                    if(!RetrofitConfigs.access_token.isEmpty() &&
                            !RetrofitConfigs.uid.isEmpty() &&
                            !RetrofitConfigs.client.isEmpty()){
                        abrirTelaMain();
                    }
                }else {
                    usuarioInvalido.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                call.toString();
            }
        });
    }

    private void abrirTelaMain(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    private boolean campoNaoValido(){
        String email = textEmail.getText().toString();
        String password = textPassword.getText().toString();
        if(campoEhVazio(email)){
            textEmail.setError("Campo Obrigatório!");
            return false;
        }

        if(campoEhVazio(password)){
            textPassword.setError("Campo Obrigatório!");
            return false;
        }

        return true;
    }

    private boolean campoEhVazio(String text){
        return text.isEmpty();
    }
}