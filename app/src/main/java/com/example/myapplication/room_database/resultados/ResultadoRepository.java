package com.example.myapplication.room_database.resultados;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myapplication.room_database.SoundDatabase;

import java.util.List;

public class ResultadoRepository {

    private ResultadoDao resultadoDao;
    private LiveData<List<Resultado>> allResultados;


    public ResultadoRepository(Application application){
        SoundDatabase database = SoundDatabase.getInstance(application);
        resultadoDao = database.resultadoDao();
        allResultados = database.resultadoDao().getAllResultados();
    }


    public void agregarResultado(Resultado resultado){new agregarResultadoAsynkTask(resultadoDao).execute(resultado);}

    public void eliminarResultado(Resultado resultado){new eliminarResultadoAsynkTask(resultadoDao).execute(resultado);}

    public LiveData<List<Resultado>> getAllResultados() {
        return allResultados;
    }


    private static class agregarResultadoAsynkTask extends AsyncTask<Resultado,Void,Void>{
        private ResultadoDao resultadoDao;
        private agregarResultadoAsynkTask(ResultadoDao resultadoDao){
            this.resultadoDao = resultadoDao;
        }
        @Override
        protected Void doInBackground(Resultado... resultados) {
            resultadoDao.agregarResultado(resultados[0]);
            return null;
        }
    }


    private static class eliminarResultadoAsynkTask extends AsyncTask<Resultado,Void,Void>{
        private ResultadoDao resultadoDao;
        private eliminarResultadoAsynkTask(ResultadoDao resultadoDao){
            this.resultadoDao = resultadoDao;
        }
        @Override
        protected Void doInBackground(Resultado... resultados) {
            resultadoDao.eliminarResultado(resultados[0]);
            return null;
        }
    }



}






