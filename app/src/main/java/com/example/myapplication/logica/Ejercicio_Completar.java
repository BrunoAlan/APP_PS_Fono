package com.example.myapplication.logica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.myapplication.Controllers.ReproductorDeAudioController;
import com.example.myapplication.DetalleResultado;
import com.example.myapplication.R;
import com.example.myapplication.datos.Constantes;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundRepository;
import com.example.myapplication.room_database.resultados.Resultado;
import com.example.myapplication.room_database.resultados.ResultadoRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio_Completar extends AppCompatActivity {
    ReproductorDeAudioController reproductorDeAudioController;
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
        setContentView(R.layout.ejercicio_completar);

        puntajeCorrecto = 0;
        puntajeIncorrecto = 0;
        repeticiones = 0;

        //Datos pasados desde la configuración
        subdato = getIntent().getStringExtra("subDato");
        ruido = getIntent().getStringExtra("tipoRuido");
        intensidad = getIntent().getFloatExtra("intensidad", .1f);
        modo = getIntent().getStringExtra("modo");
        intensidadPorcentual = Math.floor(intensidad * 100);


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

            case Constantes.ORACIONES:
                sr.getOracionesSounds().observe(this, new Observer<List<Sound>>() {
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
                reproductorDeAudioController = new ReproductorDeAudioController();
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
                    errores = errores + listaSonidos.get(random).getNombre_sonido() + "\n";
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

        if (modo.equals(Constantes.EVALUACION) && finEjercicio()) {
            //Toast.makeText(Ejercicio_Tres_Opciones.this, "Puntaje Correcto" + puntajeCorrecto + " Puntaje Incorrecto " + puntajeIncorrecto, Toast.LENGTH_SHORT).show();
            Date date = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String today = formatter.format(date);
            ResultadoRepository resultadoRepository = new ResultadoRepository(getApplication());
            Resultado resultado = new Resultado(today, Constantes.ESCRIBIR_LO_QUE_OYO, subdato, ruido, intensidadPorcentual + "%", errores, puntajeCorrecto + "");
            resultadoRepository.agregarResultado(resultado);
            Intent intent = new Intent(getApplicationContext(), DetalleResultado.class);
            intent.putExtra("fecha", today);
            intent.putExtra("ejercicio", Constantes.ESCRIBIR_LO_QUE_OYO);
            intent.putExtra("categoria", subdato);
            intent.putExtra("ruido", ruido);
            intent.putExtra("intensidad", intensidadPorcentual + "%");
            intent.putExtra("errores", errores);
            intent.putExtra("resultado", puntajeCorrecto + "");
            startActivity(intent);
        }
    }

    boolean finEjercicio() {
        repeticiones++;
        return (repeticiones == 10);
    }


    int obtenerNumero() {
        return ThreadLocalRandom.current().nextInt(0, listaSonidos.size());
    }
}
