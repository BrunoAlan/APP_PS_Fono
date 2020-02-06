package com.example.myapplication.logica;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication.Cotrollers.ReproductorDeAudioController;
import com.example.myapplication.R;
import com.example.myapplication.datos.Constantes;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundRepository;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio_Completar extends AppCompatActivity {
    ReproductorDeAudioController reproductorDeAudioController = new ReproductorDeAudioController();
    List<Sound> listaSonidos;
    int puntajeCorrecto, puntajeIncorrecto;
    String ruido;
    float intensidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_completar);

        //Datos pasados desde configuracion
        String subdato = getIntent().getStringExtra("subDato");
        ruido = getIntent().getStringExtra("tipoRuido");
        intensidad = getIntent().getFloatExtra("intensidad", .1f);
        SoundRepository sr = new SoundRepository(getApplication());
        switch (subdato) {
            case Constantes.DIAS_SEMANA:
                sr.getDiasSounds().observe(this, new Observer<List<Sound>>() {
                    @Override
                    public void onChanged(List<Sound> sounds) {
                        listaSonidos = sounds;
                        setup(obtenerNumero());
                    }
                });
                break;
            case Constantes.NUMEROS:
                sr.getNumerosSounds().observe(this, new Observer<List<Sound>>() {
                    @Override
                    public void onChanged(List<Sound> sounds) {
                        listaSonidos = sounds;
                        setup(obtenerNumero());
                    }
                });
                break;
            case Constantes.MESES:
                sr.getMesesSounds().observe(this, new Observer<List<Sound>>() {
                    @Override
                    public void onChanged(List<Sound> sounds) {
                        listaSonidos = sounds;
                        setup(obtenerNumero());
                        ;
                    }
                });
                break;

            case Constantes.COLORES:
                sr.getColoresSounds().observe(this, new Observer<List<Sound>>() {
                    @Override
                    public void onChanged(List<Sound> sounds) {
                        listaSonidos = sounds;
                        setup(obtenerNumero());
                    }
                });
                break;
        }


    }

    void setup(final int rand) {
        ImageButton btnPlay = findViewById(R.id.imageButton);
        final Button aceptar = findViewById(R.id.aceptar);
        final EditText etNombre = findViewById(R.id.campo_nombre);
        final TextView tvPuntajeCorrecto = findViewById(R.id.puntaje);
        final TextView tvPuntajeIncorrecto = findViewById(R.id.puntajeIncorrecto);
        final int random = rand;
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reproductorDeAudioController.startSoundNoNoise(listaSonidos.get(rand).getRuta_sonido(), getApplicationContext());
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNombre.getText().toString().toLowerCase().equals(listaSonidos.get(random).getNombre_sonido().toLowerCase())) {
                    modificarPuntaje(tvPuntajeCorrecto);
                    aceptar.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
                    etNombre.setText("");
                    setup(obtenerNumero());
                } else {
                    if (!etNombre.getText().toString().isEmpty()) {
                        aceptar.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation));
                        modificarPuntaje(tvPuntajeIncorrecto);
                        etNombre.setText("");
                    }
                }
            }
        });
    }


    void modificarPuntaje(TextView puntaje) {
        String points = null;
        if (puntaje.getId() == R.id.puntaje) {
            puntajeCorrecto++;
            points = Integer.toString(puntajeCorrecto);
        }
        if (puntaje.getId() == R.id.puntajeIncorrecto) {
            puntajeIncorrecto++;
            points = Integer.toString(puntajeIncorrecto);
        }
        puntaje.setText(points);
    }


    int obtenerNumero() {
        return ThreadLocalRandom.current().nextInt(0, listaSonidos.size());
    }
}
