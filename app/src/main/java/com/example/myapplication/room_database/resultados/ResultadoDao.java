package com.example.myapplication.room_database.resultados;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ResultadoDao {

    @Query("SELECT * FROM  resultados order by id DESC"   )
    LiveData<List<Resultado>> getAllResultados();

    @Query("DELETE FROM resultados")
    void borrarTodos();

    @Insert
    void agregarResultado(Resultado resultado);

    @Delete
    void eliminarResultado(Resultado resultado);

}
