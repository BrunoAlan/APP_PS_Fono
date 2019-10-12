package com.example.myapplication;

import android.media.MediaPlayer;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.datos.ListaSonidos;
import com.example.myapplication.datos.Sonido;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;


public class DiasSemana extends AppCompatActivity {
    ArrayList<Sonido> dias;
    int puntos;
    int cantEjercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dias_semana);
        setTitle("Días de la Semana");
        puntos=0;
        cantEjercicios =0;
        dias = new ListaSonidos().listaDias();
        setup();

    }

    void setup() {
        if(cantEjercicios <5){
            final MaterialButton btn1 = findViewById(R.id.opcion1);
            final MaterialButton btn2 = findViewById(R.id.opcion2);
            final MaterialButton btn3 = findViewById(R.id.opcion3);
            final TextView puntaje = findViewById(R.id.puntaje);
            ImageButton btnPlay = findViewById(R.id.imageButton);
            final Switch switchRuido = findViewById(R.id.switchRuido);
            final ArrayList tresOpciones = obtenerNumero();//genero 3 numeros aleatorios para poner los dias en los botones
            setTexts(btn1,btn2,btn3,tresOpciones);
            System.out.println("randoms asd"+tresOpciones);
            final int opcionCorrecta = ThreadLocalRandom.current().nextInt(0, tresOpciones.size());
            System.out.println("randoms filtrado "+tresOpciones.get(opcionCorrecta));


            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(activarRuido(switchRuido)){
                        MediaPlayer mpRuido= MediaPlayer.create(getBaseContext(),R.raw.ruidopersonas);
                        //NIVEL DE RUIDO
                        //BAJO
                        //mpRuido.setVolume((float)0.1,(float)0.1);
                        //MEDIO
                        mpRuido.setVolume((float)0.5,(float)0.5);
                        //ALTO
                        //mpRuido.setVolume((float)1,(float)1);
                        mpRuido.start();
                        MediaPlayer mp = MediaPlayer.create(getBaseContext(),dias.get((Integer) tresOpciones.get(opcionCorrecta)).getRuta());
                        mp.start();

                        //Agrego un delay para que que terminen ambos sonidos al mismo tiempo
                        try {
                            Thread.sleep(mp.getDuration()+200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mpRuido.stop();
                    }else {
                        MediaPlayer mp = MediaPlayer.create(getBaseContext(),dias.get((Integer) tresOpciones.get(opcionCorrecta)).getRuta());
                        mp.start();}


                }
            });

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cantEjercicios++;
                    if(dias.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre()== btn1.getText()){
                        Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT).show();
                        modificarPuntaje(puntaje);
                        setup();
                    }else{
                        Toast.makeText(getApplicationContext(),"Incorrecto",Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cantEjercicios++;
                    if(dias.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre()== btn2.getText()){
                        Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT).show();
                        modificarPuntaje(puntaje);
                        setup();
                    }else{
                        Toast.makeText(getApplicationContext(),"Incorrecto",Toast.LENGTH_SHORT).show();
                    }
                }
            });


            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cantEjercicios++;
                    System.out.println(btn3.getText() + dias.get(opcionCorrecta).getNombre());
                    if(dias.get((Integer) tresOpciones.get(opcionCorrecta)).getNombre()== btn3.getText()){
                        Toast.makeText(getApplicationContext(),"Correcto",Toast.LENGTH_SHORT).show();
                        modificarPuntaje(puntaje);
                        setup();
                    }else{
                        Toast.makeText(getApplicationContext(),"Incorrecto",Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }else{
            Toast.makeText(getApplicationContext(),"asd", Toast.LENGTH_LONG).show();
        }

    }

    void setTexts(MaterialButton btn1,MaterialButton btn2, MaterialButton btn3,ArrayList tresOpciones) {

        btn1.setText(dias.get((Integer) tresOpciones.get(0)).getNombre());
        btn2.setText(dias.get((Integer) tresOpciones.get(1)).getNombre());
        btn3.setText(dias.get((Integer) tresOpciones.get(2)).getNombre());
    }


    ArrayList obtenerNumero(){
        ArrayList randoms = new ArrayList<>();
        for(int i=0; i<dias.size();i++){
            randoms.add(i);
        }
        Collections.shuffle(randoms);
        ArrayList tresOpciones = new ArrayList();
        tresOpciones.add(randoms.get(0));
        tresOpciones.add(randoms.get(1));
        tresOpciones.add(randoms.get(2));
        System.out.println("randoms obtener numero"+randoms);
        return tresOpciones;
    }

    void modificarPuntaje(TextView puntaje){
        puntos++;
        String points = Integer.toString(puntos);
        puntaje.setText(points);
    }

    Boolean activarRuido(Switch switchRuido){
        if (switchRuido.isChecked()){
            return true;
        }else return false;
    }


}
