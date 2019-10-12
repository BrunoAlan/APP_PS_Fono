package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.datos.Sonido;

import java.util.ArrayList;

public class AdapterDatos  extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos>
        implements View.OnClickListener {

    ArrayList<Sonido> listDatos;
    private View.OnClickListener listener;

    public AdapterDatos(ArrayList<Sonido> listDatos) {

         this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.practica_item ,viewGroup,false);
        view.setOnClickListener(this);

        return new ViewHolderDatos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int position) {
        viewHolderDatos.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }

    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView dato;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            dato = (TextView ) itemView.findViewById(R.id.dato);
        }

        public void asignarDatos(Sonido sonido) {
            dato.setText(sonido.getNombre());
        }
    }
}
