package com.example.myapplication.datos;

import android.content.Intent;

public class Sonido {

    private String nombre = null;
    private String categoria = null;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
    }

    private int ruta = 0;

    public Sonido(String nombre, String categoria, int ruta) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.ruta = ruta;
    }

}
