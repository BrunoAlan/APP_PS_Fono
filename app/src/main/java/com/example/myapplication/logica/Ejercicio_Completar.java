package com.example.myapplication.logica;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication.Cotrollers.ReproductorDeAudioController;
import com.example.myapplication.R;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio_Completar extends AppCompatActivity {
    ReproductorDeAudioController reproductorDeAudioController = new ReproductorDeAudioController();
    List<Sound> asd;
    int random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_completar);


        SoundRepository sr = new SoundRepository(getApplication());
        sr.getAllSounds().observe(this, new Observer<List<Sound>>() {
            @Override
            public void onChanged(List<Sound> sounds) {
                asd = sounds;
                int a = asd.size();

                setup(obtenerNumero());
            }
        });

    }

    void setup(final int rand) {
        ImageButton btnPlay = findViewById(R.id.imageButton);
        final Button aceptar = findViewById(R.id.aceptar);
        final EditText etNombre = findViewById(R.id.campo_nombre);
        final int random = rand;
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproductorDeAudioController.startSoundNoNoise(asd.get(rand).getRuta_sonido(), getApplicationContext());
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNombre.getText().toString().toLowerCase().equals(asd.get(random).getNombre_sonido().toLowerCase()))   {
                    Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    setup(obtenerNumero());
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                }

            }
        });    }


    int obtenerNumero() {
        return ThreadLocalRandom.current().nextInt(0, asd.size());
    }
}
