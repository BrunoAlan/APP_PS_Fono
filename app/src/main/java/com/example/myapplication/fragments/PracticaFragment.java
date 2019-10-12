package com.example.myapplication.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.DiasSemana;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PracticaFragment extends Fragment implements View.OnClickListener{


    public PracticaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_practica, container, false);
        Button btnFonema = view.findViewById(R.id.button_practicar);
        btnFonema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"palabra",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DiasSemana.class);
                startActivity(intent);
            }
        });
        return view;






    }

    @Override
    public void onClick(View v) {

    }
}