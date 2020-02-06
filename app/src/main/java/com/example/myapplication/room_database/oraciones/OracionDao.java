package com.example.myapplication.room_database.oraciones;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface OracionDao {

    @Query("SELECT * FROM oraciones")
    LiveData<List<Oracion>> getOracionesList();


    @Query("DELETE FROM sonidos")
    void borrarTodos();

    @Insert
    void agregarOracion(Oracion oracion);

    @Delete
    void eliminarOracion(Oracion oracion);


}
