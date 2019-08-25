package com.example.myapplication.datos;

import android.content.Intent;

public class Sonido {

    private String nombre = null;
    private int ruta = 0;

    public Sonido(String nombre, int ruta) {
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
    }



}
