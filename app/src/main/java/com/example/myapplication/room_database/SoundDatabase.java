package com.example.myapplication.room_database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.datos.Constantes;

@Database(entities = {Sound.class}, exportSchema = false,version = 1)
public abstract class SoundDatabase  extends RoomDatabase {
    private static final String DB_NAME = "app_db";
    private static SoundDatabase instance;
    public abstract SoundDao soundDao();

    public static synchronized SoundDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),SoundDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new LlenarDBAsyncTasck(instance).execute();
        }
    };

    private static class LlenarDBAsyncTasck extends AsyncTask<Void,Void,Void>{
        private SoundDao soundDao;
        private LlenarDBAsyncTasck(SoundDatabase db){
            soundDao = db.soundDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            soundDao.agregarSonido(new Sound("Lunes",Constantes.DIAS_SEMANA,"lunes.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Martes",Constantes.DIAS_SEMANA,"martes.mp3",Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Miércoles",Constantes.DIAS_SEMANA,"miercoles.mp3",Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Jueves",Constantes.DIAS_SEMANA,"jueves.mp3",Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Viernes",Constantes.DIAS_SEMANA,"viernes.mp3",Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Sábado",Constantes.DIAS_SEMANA,"sabado.mp3",Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Domingo",Constantes.DIAS_SEMANA,"domingo.mp3",Constantes.NO_PERSONALIZADO));

            soundDao.agregarSonido(new Sound("Uno",Constantes.NUMEROS,"uno.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Dos",Constantes.NUMEROS,"dos.mp3",Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Tres",Constantes.NUMEROS,"tres.mp3",Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Cuatro",Constantes.NUMEROS,"cuatro.mp3",Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Cinco",Constantes.NUMEROS,"cinco.mp3",Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Seis",Constantes.NUMEROS,"seis.mp3",Constantes.NO_PERSONALIZADO));

            return null;
        }
    }
}
