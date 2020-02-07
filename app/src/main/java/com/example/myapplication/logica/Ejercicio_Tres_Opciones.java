package com.example.myapplication.logica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication.Cotrollers.ReproductorDeAudioController;
import com.example.myapplication.DetalleResultado;
import com.example.myapplication.R;
import com.example.myapplication.datos.Constantes;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundRepository;
import com.example.myapplication.room_database.resultados.Resultado;
import com.example.myapplication.room_database.resultados.ResultadoRepository;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Ejercicio_Tres_Opciones extends AppCompatActivity {
    public SoundRepository sr;
    List<Sound> listaSonidos;
    int puntajeCorrecto, puntajeIncorrecto, repeticiones;
    int opcionCorrecta;
    String ruido;
    String modo;
    String subdato;
    String errores = "";
    float intensidad;
    double intensidadPorcentual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_tres_opciones);
        puntajeCorrecto = 0;
        puntajeIncorrecto = 0;
        repeticiones = 0;


        //DAtos pasados desde la confifuracion
        subdato = getIntent().getStringExtra("subDato");
        ruido = getIntent().getStringExtra("tipoRuido");
        intensidad = getIntent().getFloatExtra("intensidad", .1f);
        modo = getIntent().getStringExtra("modo");
        intensidadPorcentual = Math.floor(intensidad * 100);

        sr = new SoundRepository(getApplication());
        switch (subdato) {
            case Constantes.DIAS_SEMANA:
                sr.getDiasSounds().observe(this, new Observer<List<Sound>>() {
                    @Override
                    public void onChanged(List<Sound> sounds) {
                        listaSonidos = sounds;
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

            case Constantes.COLORES:
                sr.getColoresSounds().observe(this, new Observer<List<Sound>>() {
                    @Override
                    public void onChanged(List<Sound> sounds) {
                        listaSonidos = sounds;
                        setup();
                    }
                });
                break;

            case Constantes.ORACIONES:
                sr.getOracionesSounds().observe(this, new Observer<List<Sound>>() {
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
        final TextView tvPuntajeCorrecto = findViewById(R.id.puntaje);
        final TextView tvPuntajeIncorrecto = findViewById(R.id.puntajeIncorrecto);
        final ImageButton btnPlay = findViewById(R.id.imageButton);

        final ArrayList tresOpciones = obtenerNumero();//genero 3 numeros aleatorios para poner los dias en los botones
        setTexts(btn1, btn2, btn3, tresOpciones);
        System.out.println("randoms asd" + tresOpciones);
        Random rand = new Random();
        opcionCorrecta = rand.nextInt(tresOpciones.size());


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activarSonido()) {
                    sr.getRutaSonido(ruido).observe(Ejercicio_Tres_Opciones.this, new Observer<List<Sound>>() {
                        @Override
                        public void onChanged(List<Sound> sounds) {
                            ReproductorDeAudioController rp = new ReproductorDeAudioController();
                            rp.startSoundWithNoise(listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getRuta_sonido(), sounds.get(0).getRuta_sonido(), intensidad, getApplicationContext());
                        }
                    });

                } else {
                    ReproductorDeAudioController rp = new ReproductorDeAudioController();
                    rp.startSoundNoNoise(listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getRuta_sonido(), getApplicationContext());
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre_sonido() == btn1.getText()) {
                    modificarPuntaje(tvPuntajeCorrecto);
                    btn1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
                    setup();
                } else {
                    errores = errores + listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre_sonido() + "\n";
                    modificarPuntaje(tvPuntajeIncorrecto);
                    btn1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation));
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre_sonido() == btn2.getText()) {
                    modificarPuntaje(tvPuntajeCorrecto);
                    btn2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
                    setup();
                } else {
                    errores = errores + listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre_sonido() + "\n";
                    modificarPuntaje(tvPuntajeIncorrecto);
                    btn2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation));

                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(btn3.getText() + listaSonidos.get(opcionCorrecta).getNombre_sonido());
                if (listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre_sonido() == btn3.getText()) {
                    modificarPuntaje(tvPuntajeCorrecto);
                    btn3.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
                    setup();
                } else {
                    errores = errores + listaSonidos.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre_sonido() + "\n";
                    modificarPuntaje(tvPuntajeIncorrecto);
                    btn3.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation));
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

        if (modo.equals(Constantes.EVALUACION) && finEjercicio()) {
            //Toast.makeText(Ejercicio_Tres_Opciones.this, "Puntaje Correcto" + puntajeCorrecto + " Puntaje Incorrecto " + puntajeIncorrecto, Toast.LENGTH_SHORT).show();
            Date date = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String today = formatter.format(date);
            ResultadoRepository resultadoRepository = new ResultadoRepository(getApplication());
            Resultado resultado = new Resultado(today, Constantes.IDENTIFICAR_TRES_OPCIONES, subdato, ruido, intensidadPorcentual + "%", errores, puntajeCorrecto + "");
            resultadoRepository.agregarResultado(resultado);
            Intent intent = new Intent(getApplicationContext(), DetalleResultado.class);
            intent.putExtra("fecha", today);
            intent.putExtra("ejercicio", Constantes.IDENTIFICAR_TRES_OPCIONES);
            intent.putExtra("categoria", subdato);
            intent.putExtra("ruido", ruido);
            intent.putExtra("intensidad", intensidadPorcentual + "%");
            intent.putExtra("errores", errores);
            intent.putExtra("resultado", puntajeCorrecto + "");
            startActivity(intent);
        }
    }

    boolean activarSonido() {
        return !ruido.equals("Sin Ruido");
    }

    boolean finEjercicio() {
        repeticiones++;
        return (repeticiones == 10);
    }

}
