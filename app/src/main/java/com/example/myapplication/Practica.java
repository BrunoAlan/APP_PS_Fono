package com.example.myapplication;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.datos.ListaSonidos;
import com.example.myapplication.datos.Sonido;

import java.util.ArrayList;

public class Practica extends AppCompatActivity {

    private ArrayList<Sonido> listDatos;
    private AdapterDatos adapterDatos;
    private RecyclerView recycler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practica);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Práctica");

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        listDatos = new ListaSonidos().allSounds();
        AdapterDatos adapter = new AdapterDatos(listDatos);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),
                        listDatos.get(recycler.getChildAdapterPosition(v)).getRuta());
                mp.start();
            }
        });
        recycler.setAdapter(adapter);

    }
}
