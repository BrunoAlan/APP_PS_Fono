package com.example.myapplication.room_database.resultados;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ResultadoViewModel extends AndroidViewModel {
    private ResultadoRepository resultadoRepository;
    private LiveData<List<Resultado>> allResultadoos;

    public ResultadoViewModel(@NonNull Application application) {
        super(application);
        resultadoRepository = new ResultadoRepository(application);
        allResultadoos = resultadoRepository.getAllResultados();
    }


    public void agregarResultado(Resultado resultado){resultadoRepository.agregarResultado(resultado);}

    public void eliminarResultado(Resultado resultado){resultadoRepository.eliminarResultado(resultado);}

    public LiveData<List<Resultado>> getAllResultadoos() {return allResultadoos;}
}
