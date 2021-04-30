package com.app_empresas.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app_empresas.R;
import com.app_empresas.model.Enterprise;

import java.util.List;

public class AdapterEnterprise extends RecyclerView.Adapter<AdapterEnterprise.MyViewHolder>  {

    List<Enterprise> enterprises;
    Context context;

    public AdapterEnterprise(List<Enterprise> enterprises, Context context) {
        this.enterprises = enterprises;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_enterprises, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Enterprise enterprise = enterprises.get(position);

        holder.nome.setText(enterprise.getEnterpriseName());
        holder.negocio.setText(enterprise.getEnterprise_type());
        holder.cidade.setText(enterprise.getCity());
        holder.imageView.setImageResource(R.drawable.logo_ioasys);
    }

    @Override
    public int getItemCount() {
        return enterprises.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome, negocio, cidade;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textAdapterNomeEmpresa);
            negocio = itemView.findViewById(R.id.textAdapterNegocio);
            cidade = itemView.findViewById(R.id.textAdapterCidade);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
