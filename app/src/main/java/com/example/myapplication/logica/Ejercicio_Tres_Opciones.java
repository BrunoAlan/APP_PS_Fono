package com.example.myapplication.logica;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication.Cotrollers.ReproductorDeAudioController;
import com.example.myapplication.R;
import com.example.myapplication.datos.Constantes;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundRepository;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Ejercicio_Tres_Opciones extends AppCompatActivity {
    public SoundRepository sr;
    List<Sound> listaSonidos;
    int puntos;
    int cantEjercicios;
    int opcionCorrecta;
    String ruido;
    float intensidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_tres_opciones);
        puntos = 0;
        cantEjercicios = 0;
        String subdato = getIntent().getStringExtra("subDato");
        ruido = getIntent().getStringExtra("tipoRuido");
        intensidad = getIntent().getFloatExtra("intensidad", .1f);
        System.out.println(ruido);


        sr = new SoundRepository(getApplication());
        switch (subdato){
            case Constantes.DIAS_SEMANA:
                sr.getDiasSounds().observe(this, new Observer<List<Sound>>() {
                    @Override
                    public void onChanged(List<Sound> sounds) {
                        listaSonidos= sounds;
                        setup();
                    }
                });
                break;
            case Constantes.NUMEROS:
                sr.getNumerosSounds().observe(this, new Observer<List<Sound>>() {
                    @Override
                    public void onChanged(List<Sound> sounds) {
                        listaSonidos = sounds;
                        setup();
                    }
                });
                break;
            case Constantes.MESES:
                sr.getMesesSounds().observe(this, new Observer<List<Sound>>() {
                    @Override
                    public void onChanged(List<Sound> sounds) {
                        listaSonidos = sounds;
                        setup();
                    }
                });
                break;
        }
    }

    void setup() {
        final MaterialButton btn1 = findViewById(R.id.opcion1);
        final MaterialButton btn2 = findViewById(R.id.opcion2);
        final MaterialButton btn3 = findViewById(R.id.opcion3);
        final TextView puntaje = findViewById(R.id.puntaje);
        final ImageButton btnPlay = findViewById(R.id.imageButton);

        final ArrayList tresOpciones = obtenerNumero();//genero 3 numeros aleatorios para poner los dias en los botones
        setTexts(btn1, btn2, btn3, tresOpciones);
        System.out.println("randoms asd" + tresOpciones);
        Random rand = new Random();
        opcionCorrecta = rand.nextInt(tresOpciones.size());
        System.out.println(opcionCorrecta);
        System.out.println("randoms filtrado " + tresOpciones.get(opcionCorrecta));


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activarSonido()) {
                    ReproductorDeAudioController rp = new ReproductorDeAudioController();
                    rp.startSoundWithNoise(listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getRuta_sonido(), R.raw.ruido_personas, intensidad, getApplicationContext());
                } else {
                    ReproductorDeAudioController rp = new ReproductorDeAudioController();
                    rp.startSoundNoNoise(listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getRuta_sonido(), getApplicationContext());
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantEjercicios++;
                if (listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre_sonido() == btn1.getText()) {
                    Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_SHORT).show();
                    modificarPuntaje(puntaje);
                    setup();
                } else {
                    btn1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation));
                    Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantEjercicios++;
                if (listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre_sonido() == btn2.getText()) {
                    Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_SHORT).show();
                    modificarPuntaje(puntaje);
                    setup();
                } else {
                    btn2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation));
                    Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantEjercicios++;
                System.out.println(btn3.getText() + listaSonidos.get(opcionCorrecta).getNombre_sonido());
                if (listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre_sonido() == btn3.getText()) {
                    Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_SHORT).show();
                    modificarPuntaje(puntaje);
                    setup();
                } else {
                    btn3.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation));
                    Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    void setTexts(MaterialButton btn1, MaterialButton btn2, MaterialButton btn3, ArrayList tresOpciones) {
        btn1.setText(listaSonidos.get((Integer) tresOpciones.get(0)).getNombre_sonido());
        btn2.setText(listaSonidos.get((Integer) tresOpciones.get(1)).getNombre_sonido());
        btn3.setText(listaSonidos.get((Integer) tresOpciones.get(2)).getNombre_sonido());
    }


    ArrayList obtenerNumero() {
        ArrayList randoms = new ArrayList<>();
        for (int i = 0; i < listaSonidos.size(); i++) {
            randoms.add(i);
        }
        Collections.shuffle(randoms);
        ArrayList tresOpciones = new ArrayList();
        tresOpciones.add(randoms.get(0));
        tresOpciones.add(randoms.get(1));
        tresOpciones.add(randoms.get(2));
        System.out.println("randoms obtener numero" + randoms);
        return tresOpciones;
    }

    void modificarPuntaje(TextView puntaje) {
        puntos++;
        String points = Integer.toString(puntos);
        puntaje.setText(points);
    }

    boolean activarSonido() {
        if (ruido.equals("Sin Ruido")) {
            return false;
        } else {
            return true;
        }
    }

}
