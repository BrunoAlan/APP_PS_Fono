package com.example.myapplication.room_database.oraciones;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Oracion {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "oracion_completa")
    private String oracion_completa;
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

