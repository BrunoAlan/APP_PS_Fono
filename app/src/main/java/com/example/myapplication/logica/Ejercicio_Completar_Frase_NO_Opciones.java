package com.example.myapplication.logica;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
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

public class Ejercicio_Completar_Frase_NO_Opciones extends AppCompatActivity {
    public SoundRepository sr;
    ReproductorDeAudioController reproductorDeAudioController = new ReproductorDeAudioController();
    List<Sound> listaSonidos;
    int random;
    String ruido;
    float intensidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio__completar__frase__no__opciones);


        //DAtos pasados desde la confifuracion

        ruido = getIntent().getStringExtra("tipoRuido");
        intensidad = getIntent().getFloatExtra("intensidad", .1f);
        System.out.println(ruido);


        sr = new SoundRepository(getApplication());
        sr.getOracionesSounds().observe(this, new Observer<List<Sound>>() {
            @Override
            public void onChanged(List<Sound> sounds) {
                listaSonidos = sounds;
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
                if(activarSonido()){
                    sr.getRutaSonido(ruido).observe(Ejercicio_Completar_Frase_NO_Opciones.this, new Observer<List<Sound>>() {
                        @Override
                        public void onChanged(List<Sound> sounds) {
                            reproductorDeAudioController.startSoundOracionesNoise(listaSonidos.get(rand).getRuta_sonido(), sounds.get(0).getRuta_sonido(),intensidad,getApplicationContext());
                        }
                    });
                }else{
                    reproductorDeAudioController.startSoundOraciones(listaSonidos.get(rand).getRuta_sonido(), getApplicationContext());
                }

            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !etNombre.getText().toString().isEmpty() && listaSonidos.get(random).getNombre_sonido().toLowerCase().contains(etNombre.getText().toString().toLowerCase()))   {
                    Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    aceptar.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce));
                    setup(obtenerNumero());
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_SHORT).show();
                    aceptar.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake_animation));
                    etNombre.setText("");
                }
            }
        });
    }

    int obtenerNumero() {
        return ThreadLocalRandom.current().nextInt(0, listaSonidos.size());
    }

    boolean activarSonido() {
        if (ruido.equals("Sin Ruido")) {
            return false;
        } else {
            return true;
        }
    }

}
