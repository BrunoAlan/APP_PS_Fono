package com.example.myapplication.room_database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.myapplication.datos.Constantes;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundDao;
import com.example.myapplication.room_database.resultados.Resultado;
import com.example.myapplication.room_database.resultados.ResultadoDao;

@Database(entities = {Sound.class,  Resultado.class}, exportSchema = false, version = 1)
public abstract class SoundDatabase extends RoomDatabase {
    private static final String DB_NAME = "app_db";
    private static SoundDatabase instance;
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new LlenarDBAsyncTasck(instance).execute();
        }
    };

    public static synchronized SoundDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), SoundDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    public abstract SoundDao soundDao();

    public abstract ResultadoDao resultadoDao();

    private static class LlenarDBAsyncTasck extends AsyncTask<Void, Void, Void> {
        private SoundDao soundDao;
        private ResultadoDao resultadoDao;

        private LlenarDBAsyncTasck(SoundDatabase db) {
            soundDao = db.soundDao();
            resultadoDao = db.resultadoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Días de la semana
            soundDao.agregarSonido(new Sound("Lunes", Constantes.DIAS_SEMANA, "lunes.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Martes", Constantes.DIAS_SEMANA, "martes.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Miércoles", Constantes.DIAS_SEMANA, "miercoles.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Jueves", Constantes.DIAS_SEMANA, "jueves.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Viernes", Constantes.DIAS_SEMANA, "viernes.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Sábado", Constantes.DIAS_SEMANA, "sabado.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Domingo", Constantes.DIAS_SEMANA, "domingo.mp3", Constantes.NO_PERSONALIZADO));

            //Números
            soundDao.agregarSonido(new Sound("Uno", Constantes.NUMEROS, "uno.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Dos", Constantes.NUMEROS, "dos.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Tres", Constantes.NUMEROS, "tres.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Cuatro", Constantes.NUMEROS, "cuatro.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Cinco", Constantes.NUMEROS, "cinco.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Seis", Constantes.NUMEROS, "seis.mp3", Constantes.NO_PERSONALIZADO));

            //Meses
            soundDao.agregarSonido(new Sound("Enero", Constantes.MESES, "enero.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Febrero", Constantes.MESES, "febrero.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Marzo", Constantes.MESES, "marzo.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Abril", Constantes.MESES, "abril.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Mayo", Constantes.MESES, "mayo.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Junio", Constantes.MESES, "junio.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Julio", Constantes.MESES, "julio.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Agosto", Constantes.MESES, "agosto.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Septiembre", Constantes.MESES, "septiembre.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Octubre", Constantes.MESES, "octubre.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Noviembre", Constantes.MESES, "noviembre.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Diciembre", Constantes.MESES, "diciembre.mp3", Constantes.NO_PERSONALIZADO));

            //Colores
            soundDao.agregarSonido(new Sound("Negro", Constantes.COLORES, "negro.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Azul", Constantes.COLORES, "azul.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Blanco", Constantes.COLORES, "blanco.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Marrón", Constantes.COLORES, "marron.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Amarillo", Constantes.COLORES, "amarillo.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Rojo", Constantes.COLORES, "rojo.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Rosa", Constantes.COLORES, "rosa.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Verde", Constantes.COLORES, "verde.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Violeta", Constantes.COLORES, "violeta.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Naranja", Constantes.COLORES, "naranja.mp3", Constantes.NO_PERSONALIZADO));


            //Ruidos
            soundDao.agregarSonido(new Sound("Multitud de personas", Constantes.RUIDO, "ruido_personas.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Recreo de niños", Constantes.RUIDO, "ruido_recreo.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Sirena Ambulancia", Constantes.RUIDO, "ruido_ambulancia.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("Tráfico Intenso", Constantes.RUIDO, "ruido_trafico.mp3", Constantes.NO_PERSONALIZADO));

            soundDao.agregarSonido(new Sound("Al que madruga, Dios lo ayuda", Constantes.ORACIONES, "al que madruga.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("En boca cerrada no entran moscas", Constantes.ORACIONES, "en boca cerrada.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("No por mucho madrugar, se amanece más temprano", Constantes.ORACIONES, "no por mucho madrugar.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("No hay mal que por bien no venga", Constantes.ORACIONES, "no hay mal que.mp3", Constantes.NO_PERSONALIZADO));
            soundDao.agregarSonido(new Sound("En casa de herrero, cuchillo de palo", Constantes.ORACIONES, "en casa de herrero.mp3", Constantes.NO_PERSONALIZADO));

            return null;
        }
    }
}
