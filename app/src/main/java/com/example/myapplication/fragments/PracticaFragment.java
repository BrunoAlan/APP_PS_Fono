package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.logica.Practica;

public class PracticaFragment extends Fragment implements View.OnClickListener {
    public PracticaFragment() {

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practica, container, false);
        Button btnFonema = view.findViewById(R.id.button_practicar);
        btnFonema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Practica.class);
                intent.putExtra("tipoEjercicio", "diasSemana");
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
