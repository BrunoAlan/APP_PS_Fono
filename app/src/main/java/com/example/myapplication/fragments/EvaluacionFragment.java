package com.example.myapplication.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Configuracion;
import com.example.myapplication.R;
import com.example.myapplication.datos.Constantes;


/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluacionFragment extends Fragment {


    public EvaluacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluacion, container, false);
        Button btnEvaluacion = view.findViewById(R.id.button_practicar);
        btnEvaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Configuracion.class);
                intent.putExtra("Modo", Constantes.EVALUACION);
                startActivity(intent);
            }
        });
        return view;
    }

}
