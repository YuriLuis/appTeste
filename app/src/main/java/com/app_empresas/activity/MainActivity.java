package com.app_empresas.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app_empresas.R;
import com.app_empresas.adapter.AdapterEnterprise;
import com.app_empresas.config.RetrofitConfigs;
import com.app_empresas.model.Enterprise;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Enterprise> listEnterprises = new ArrayList<>();
    private AdapterEnterprise adapterEnterprise;
    private RecyclerView recyclerEnterprises;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // postLoginResquest();
        getTest();
        initComponents();
    }

    private void initComponents(){
        recyclerEnterprises = findViewById(R.id.recyclerViewEnterprise);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    private void getTest() {
        Call<JsonObject> call = new RetrofitConfigs().serviceAppEmpresas().getEntreprises(
                RetrofitConfigs.access_token, RetrofitConfigs.uid, RetrofitConfigs.client);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    JsonObject enterprises;
                    Log.d("GET" , response.body().toString());
                    enterprises = response.body();
                    enterprises.getClass();
                    JsonArray nomeEmpresa = enterprises.getAsJsonArray("enterprises");
                    for(int i = 0; i < nomeEmpresa.size();i++){
                        JsonObject empresa = (JsonObject) nomeEmpresa.get(i);
                        int id = empresa.get("id").getAsInt();
                        String enterprise_name = String.valueOf(empresa.get("enterprise_name").getAsString());
                        String description = String.valueOf(empresa.get("description").getAsString());
                        String type = empresa.getAsJsonObject("enterprise_type").get("enterprise_type_name").getAsString();
                        boolean own_enterprise = empresa.get("own_enterprise").getAsBoolean();
                        String city = empresa.get("city").getAsString();
                        JsonElement imagem = empresa.get("photo");
                        Enterprise enterprise = new Enterprise(id, enterprise_name, description, type, own_enterprise, city, imagem.getAsString());
                        listEnterprises.add(enterprise);
                    }
                    configRecyclerView();
                    Log.d("GET" , response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                call.toString();
            }
        });
    }

    private void configRecyclerView(){
        adapterEnterprise = new AdapterEnterprise(listEnterprises, MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerEnterprises.setLayoutManager(layoutManager);
        recyclerEnterprises.setHasFixedSize(true);
        recyclerEnterprises.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerEnterprises.setAdapter(adapterEnterprise);
    }

}