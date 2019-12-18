package com.example.myapplication.room_database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

            soundDao.agregarSonido(new Sound("Lunes","Días","lunes.mp3","no"));
            soundDao.agregarSonido(new Sound("Martes","Días","martes.mp3","no"));
            soundDao.agregarSonido(new Sound("Miércoles","Días","miercoles.mp3","no"));
            soundDao.agregarSonido(new Sound("Jueves","Días","jueves.mp3","no"));
            soundDao.agregarSonido(new Sound("Viernes","Días","viernes.mp3","no"));
            soundDao.agregarSonido(new Sound("Sábado","Días","sabado.mp3","no"));
            soundDao.agregarSonido(new Sound("Domingo","Días","domingo.mp3","no"));
            return null;
        }
    }
}
