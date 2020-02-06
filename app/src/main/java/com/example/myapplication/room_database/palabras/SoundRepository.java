package com.example.myapplication.room_database.palabras;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.myapplication.room_database.SoundDatabase;

import java.util.List;


public class SoundRepository {
    private SoundDao soundDao;
    private LiveData<List<Sound>> allSounds;
    private LiveData<List<Sound>> diasSounds;
    private LiveData<List<Sound>> numerosSounds;
    private LiveData<List<Sound>> mesesSounds;
    private LiveData<List<Sound>> coloresSounds;
    private LiveData<List<Sound>> ruidosSounds;
    private LiveData<List<Sound>> rutaRuido;
    private LiveData<List<Sound>> oracionesSounds;


    public SoundRepository(Application application) {
        SoundDatabase database = SoundDatabase.getInstance(application);
        soundDao = database.soundDao();
        allSounds = soundDao.getSoundList();
        diasSounds = soundDao.getListOfDias();
        numerosSounds = soundDao.getListOfNumeros();
        mesesSounds = soundDao.getListOfMeses();
        coloresSounds = soundDao.getListOfColores();
        ruidosSounds = soundDao.getListOfRuido();
        oracionesSounds = soundDao.getListOfOraciones();


    }

    public void agregarSonido(Sound sound) {
        new agregarSonidoAsynkTask(soundDao).execute(sound);
    }

    public void eliminarSonido(Sound sound) {
        new eliminarSonidoAsynkTask(soundDao).execute(sound);
    }

    public LiveData<List<Sound>> getAllSounds() {
        return allSounds;
    }

    public LiveData<List<Sound>> getDiasSounds() {
        return diasSounds;
    }

    public LiveData<List<Sound>> getMesesSounds() {
        return mesesSounds;
    }

    public LiveData<List<Sound>> getNumerosSounds() {
        return numerosSounds;
    }

    public LiveData<List<Sound>> getColoresSounds() {
        return coloresSounds;
    }

    public LiveData<List<Sound>> getOracionesSounds() {
        return oracionesSounds;
    }

    public LiveData<List<Sound>> getRuidosSounds() {
        return ruidosSounds;
    }

    public LiveData<List<Sound>> getRutaSonido(String nombreSonido) {
        return soundDao.getRutaSonido(nombreSonido);
    }




    private static class eliminarSonidoAsynkTask extends AsyncTask<Sound, Void, Void> {
        private SoundDao soundDao;

        private eliminarSonidoAsynkTask(SoundDao soundDao) {
            this.soundDao = soundDao;
        }

        @Override
        protected Void doInBackground(Sound... sounds) {
            soundDao.eliminarSonido(sounds[0]);
            return null;
        }
    }


    private static class agregarSonidoAsynkTask extends AsyncTask<Sound, Void, Void> {
        private SoundDao soundDao;

        private agregarSonidoAsynkTask(SoundDao soundDao) {
            this.soundDao = soundDao;
        }

        @Override
        protected Void doInBackground(Sound... sounds) {
            soundDao.agregarSonido(sounds[0]);
            return null;
        }
    }

}
