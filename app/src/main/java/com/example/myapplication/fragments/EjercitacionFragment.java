package com.example.myapplication.fragments;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.Configuracion;
import com.example.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EjercitacionFragment extends Fragment implements View.OnClickListener {


    public EjercitacionFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ejercitacion, container, false);

        Button btnEjercitacion = view.findViewById(R.id.button_ejercitacion);
        btnEjercitacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Configuracion.class);
                startActivity(intent);
            }
        });
        return view;

    }

    @Override
    public void onClick(View v) {

    }
}