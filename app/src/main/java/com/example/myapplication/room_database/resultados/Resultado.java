package com.example.myapplication.room_database.resultados;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "resultados")
public class Resultado {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "fecha")
    private String fecha;
    @ColumnInfo(name = "tipo_ejercicio")
    private String tipo_ejercicio;
    @ColumnInfo(name = "categoria")
    private String categoria;
    @ColumnInfo(name = "ruido")
    private String ruido;
    @ColumnInfo(name = "intensidad")
    private String intensidad;
    @ColumnInfo(name = "errores")
    private String errores;
    @ColumnInfo(name = "resultado")
    private String resultado;

    public Resultado(String fecha, String tipo_ejercicio, String categoria, String ruido, String intensidad, String errores, String resultado) {
        this.fecha = fecha;
        this.tipo_ejercicio = tipo_ejercicio;
        this.categoria = categoria;
        this.ruido = ruido;
        this.intensidad = intensidad;
        this.errores = errores;
        this.resultado = resultado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_ejercicio() {
        return tipo_ejercicio;
    }

    public void setTipo_ejercicio(String tipo_ejercicio) {
        this.tipo_ejercicio = tipo_ejercicio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getRuido() {
        return ruido;
    }

    public void setRuido(String ruido) {
        this.ruido = ruido;
    }

    public String getIntensidad() {
        return intensidad;
    }

    public void setIntensidad(String intensidad) {
        this.intensidad = intensidad;
    }

    public String getErrores() {
        return errores;
    }

    public void setErrores(String errores) {
        this.errores = errores;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}
