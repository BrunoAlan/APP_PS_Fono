package com.example.myapplication.datos;
import com.example.myapplication.R;
import java.util.ArrayList;

public class ListaSonidos {

    public ArrayList listaDias() {
        ArrayList<Sonido> dias;
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
        numeros.add(new Sonido("Uno",R.raw.uno));
        numeros.add(new Sonido("Dos",R.raw.dos));
        numeros.add(new Sonido("Tres", R.raw.tres));
        numeros.add(new Sonido("Cuatro",R.raw.cuatro));
        numeros.add(new Sonido("Cinco",R.raw.cinco));
        return numeros;
    }

    public ArrayList<Sonido> allSounds(){
        ArrayList<Sonido> arr1 = listaDias();
        ArrayList<Sonido> arr2 = listaNumeros();
        arr2.addAll(arr1);
        return arr2;
    }

}

