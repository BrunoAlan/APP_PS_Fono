package com.example.myapplication.datos;
import com.example.myapplication.R;
import java.util.ArrayList;

public class ListaSonidos {

    public ArrayList listaDias() {
        ArrayList<Sonido> dias;
        dias = new ArrayList<Sonido>(0);
        dias.add(new Sonido("Lunes","Días", R.raw.lunes));
        dias.add(new Sonido("Martes","Días", R.raw.martes));
        dias.add(new Sonido("Miércoles","Días", R.raw.miercoles));
        dias.add(new Sonido("Jueves","Días", R.raw.jueves));
        dias.add(new Sonido("Viernes","Días", R.raw.viernes));
        dias.add(new Sonido("Sábado","Días", R.raw.sabado));
        dias.add(new Sonido("Domingo","Días", R.raw.domingo));
        return dias;
    }


    public ArrayList<Sonido> listaNumeros(){
       ArrayList<Sonido> numeros = new ArrayList<Sonido>(0);
        numeros.add(new Sonido("Uno", "Números", R.raw.uno));
        numeros.add(new Sonido("Dos","Números",R.raw.dos));
        numeros.add(new Sonido("Tres","Números", R.raw.tres));
        numeros.add(new Sonido("Cuatro","Números",R.raw.cuatro));
        numeros.add(new Sonido("Cinco","Números",R.raw.cinco));
        return numeros;
    }

    public ArrayList<Sonido> allSounds(){
        ArrayList<Sonido> arr1 = listaDias();
        ArrayList<Sonido> arr2 = listaNumeros();
        arr2.addAll(arr1);
        return arr2;
    }

    public ArrayList<Sonido> ruidos(){
        ArrayList<Sonido> ruidos = new ArrayList<>();
        ruidos.add(new Sonido("Multitud de gente", "Ruidos",R.raw.ruido_personas));
        ruidos.add(new Sonido("Tráfico intenso","Ruidos", R.raw.ruido_trafico));
        ruidos.add(new Sonido("Recreo de niños","Ruidos", R.raw.ruido_recreo));
        ruidos.add(new Sonido("Ambulancia","Ruidos", R.raw.ruido_ambulancia));
        return ruidos;
    }
}

