package com.example.myapplication.datos;

public class TipoSonido {
    private String[] tipoSonido = {"Fonema", "Palabra", "Oraciones", "Canciones", "Instrumentos", "Estilos Musicales", "Voces Familiares"};
    private String[] tipoDatoPalabra = {"Animales", "Colores", "Comidas", "Días de la Semana", "Meses", "Nombres", "Números"};

    public String[] getTipoSonido() {
        return tipoSonido;
    }

    public String[] getTipoDatoPalabra() {
        return tipoDatoPalabra;
    }
}

