package com.example.myapplication;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.datos.ListaSonidos;
import com.example.myapplication.datos.Sonido;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Numeros_completar extends AppCompatActivity {

    ArrayList<Sonido> numeros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numeros);
        setTitle("Números");

        //cargarNumeros();
        numeros = new ListaSonidos().listaNumeros();
        setup(obtenerNumero());
    }

    void setup( int rand){
        ImageButton btnPlay = findViewById(R.id.imageButton);
        Button aceptar = findViewById(R.id.aceptar);
        final EditText etNombre = findViewById(R.id.campo_nombre);
        final int random= rand;
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp = MediaPlayer.create(getApplicationContext(), numeros.get(random).getRuta());
                mp.start();
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNombre.getText().toString().equals(numeros.get(random).getNombre())){
                    Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    setup(obtenerNumero());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Incorrecto",Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                }

            }
        });
        }


    int obtenerNumero(){
        int random = ThreadLocalRandom.current().nextInt(0, numeros.size());
        return random;
    }
}
