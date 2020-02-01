package com.example.myapplication.room_database.palabras;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface SoundDao {


    @Query("SELECT * FROM sonidos")
    LiveData<List<Sound>> getSoundList();


    @Query("SELECT * FROM sonidos WHERE categoria_sonido= 'Números' ")
    LiveData<List<Sound>> getListOfNumeros();

    @Query("SELECT * FROM sonidos WHERE categoria_sonido= 'Días de la Semana'")
    LiveData<List<Sound>> getListOfDias();

    @Query("SELECT * FROM sonidos WHERE categoria_sonido='Meses'")
    LiveData<List<Sound>> getListOfMeses();

    @Query("SELECT * FROM sonidos WHERE categoria_sonido='Colores'")
    LiveData<List<Sound>> getListOfColores();

    @Query("DELETE FROM sonidos")
    void borrarTodos();

    @Insert
    void agregarSonido(Sound sonido);

    @Delete
    void eliminarSonido(Sound sonido);
}




