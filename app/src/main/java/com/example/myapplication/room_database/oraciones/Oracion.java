package com.example.myapplication.room_database.oraciones;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "oraciones")
public class Oracion {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "oracion_completa")
    private String oracion_completa;

    public Oracion(String oracion_completa, String oracion_consigna, String respuesta_completar, String ruta_sonido_completo, String ruta_sonido_parcial, String personalizado) {
        this.oracion_completa = oracion_completa;
        this.oracion_consigna = oracion_consigna;
        this.respuesta_completar = respuesta_completar;
        this.ruta_sonido_completo = ruta_sonido_completo;
        this.ruta_sonido_parcial = ruta_sonido_parcial;
        this.personalizado = personalizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOracion_completa() {
        return oracion_completa;
    }

    public void setOracion_completa(String oracion_completa) {
        this.oracion_completa = oracion_completa;
    }

    public String getOracion_consigna() {
        return oracion_consigna;
    }

    public void setOracion_consigna(String oracion_consigna) {
        this.oracion_consigna = oracion_consigna;
    }

    public String getRespuesta_completar() {
        return respuesta_completar;
    }

    public void setRespuesta_completar(String respuesta_completar) {
        this.respuesta_completar = respuesta_completar;
    }

    public String getRuta_sonido_completo() {
        return ruta_sonido_completo;
    }

    public void setRuta_sonido_completo(String ruta_sonido_completo) {
        this.ruta_sonido_completo = ruta_sonido_completo;
    }

    public String getRuta_sonido_parcial() {
        return ruta_sonido_parcial;
    }

    public void setRuta_sonido_parcial(String ruta_sonido_parcial) {
        this.ruta_sonido_parcial = ruta_sonido_parcial;
    }

    public String getPersonalizado() {
        return personalizado;
    }

    public void setPersonalizado(String personalizado) {
        this.personalizado = personalizado;
    }

    @ColumnInfo(name = "oracion_consiga")
    private String oracion_consigna;
    @ColumnInfo(name = "respuesta_completar")
    private String respuesta_completar;
    @ColumnInfo(name = "ruta_sonido_completo")
    private String ruta_sonido_completo;
    @ColumnInfo(name = "ruta_sonido_parcial")
    private String ruta_sonido_parcial;
    @ColumnInfo(name = "personalizado")
    private String personalizado;
}

