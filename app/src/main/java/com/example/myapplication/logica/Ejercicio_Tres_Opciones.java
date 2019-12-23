package com.example.myapplication.logica;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication.R;
import com.example.myapplication.Cotrollers.ReproductorDeAudioController;
import com.example.myapplication.room_database.Sound;
import com.example.myapplication.room_database.SoundRepository;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Ejercicio_Tres_Opciones extends AppCompatActivity {
    List<Sound> listaSonidos;
    int puntos;
    int cantEjercicios;
    int opcionCorrecta;
    String ruido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_tres_opciones);
        setTitle("Días de la Semana");
        puntos = 0;
        cantEjercicios = 0;
        String subdato = getIntent().getStringExtra("subDato");
        ruido = getIntent().getStringExtra("tipoRuido");
        System.out.println(ruido);

        if (subdato.equals("Numeros")) {
            SoundRepository sr = new SoundRepository(getApplication());
            listaSonidos = sr.getDiasSounds().getValue();
        }
        if (subdato.equals("Dias de la Semana")) {
            SoundRepository sr = new SoundRepository(getApplication());
            sr.getDiasSounds().observe(this, new Observer<List<Sound>>() {
                @Override
                public void onChanged(List<Sound> sounds) {
                    listaSonidos = sounds;
                    setup();
                }
            });
        }
    }

    void setup() {
        if (cantEjercicios < 5) {
            final MaterialButton btn1 = findViewById(R.id.opcion1);
            final MaterialButton btn2 = findViewById(R.id.opcion2);
            final MaterialButton btn3 = findViewById(R.id.opcion3);
            final TextView puntaje = findViewById(R.id.puntaje);
            ImageButton btnPlay = findViewById(R.id.imageButton);

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
                       rp.startSoundWithNoise(listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getRuta_sonido(),R.raw.ruido_personas,getApplicationContext());
                    } else {
                        ReproductorDeAudioController rp = new ReproductorDeAudioController();
                        rp.startSpundNoNoise(listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getRuta_sonido(), getApplicationContext());
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
                        Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        } else {
            Toast.makeText(getApplicationContext(), "asd", Toast.LENGTH_LONG).show();
        }

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


    private void startSound(String filename) {
        AssetFileDescriptor afd = null;
        try {
            afd = getResources().getAssets().openFd(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaPlayer player = new MediaPlayer();
        try {
            assert afd != null;
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }


}
