package com.example.myapplication.datos;
import com.example.myapplication.R;
import java.util.ArrayList;

public class ListaSonidos {

    public ArrayList listaDias() {
        ArrayList<Sonido> dias = new ArrayList<>();
        dias = new ArrayList<Sonido>(0);
        dias.add(new Sonido("Lunes", R.raw.lunes));
        dias.add(new Sonido("Martes", R.raw.martes));
        dias.add(new Sonido("Miércoles", R.raw.miercoles));
        dias.add(new Sonido("Jueves", R.raw.jueves));
        dias.add(new Sonido("Viernes", R.raw.viernes));
        dias.add(new Sonido("Sábado", R.raw.sabado));
        dias.add(new Sonido("Domingo", R.raw.domingo));
        return dias;
    }


    public ArrayList<Sonido> listaNumeros(){
       ArrayList<Sonido> numeros = new ArrayList<Sonido>(0);
        numeros.add(new Sonido("uno",R.raw.uno));
        numeros.add(new Sonido("dos",R.raw.dos));
        numeros.add(new Sonido("tres", R.raw.tres));
        numeros.add(new Sonido("cuatro",R.raw.cuatro));
        numeros.add(new Sonido("cinco",R.raw.cinco));
        return numeros;
    }

}

