package com.example.myapplication.room_database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class SoundViewModel extends AndroidViewModel {
    private SoundRepository repository;
    private LiveData<List<Sound>> allSounds;
    private LiveData<List<Sound>> diasSounds;
    private LiveData<List<Sound>> numerosSounds;


    public SoundViewModel(@NonNull Application application) {
        super(application);
        repository = new SoundRepository(application);
        allSounds = repository.getAllSounds();
        diasSounds = repository.getDiasSounds();
        numerosSounds = repository.getNumerosSounds();
    }

    public void agregarSonido(Sound sound) {
        repository.agregarSonido(sound);
    }

    public void eliminarSonido(Sound sound) {
        repository.eliminarSonido(sound);
    }

    public LiveData<List<Sound>> getAllSounds() {

        return allSounds;
    }

    public LiveData<List<Sound>> getDiasSounds() {
        return diasSounds;
    }

    public LiveData<List<Sound>> getNumerosSounds() {
        return numerosSounds;
    }
}
