package com.example.myapplication.logica;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.example.myapplication.R;
import com.example.myapplication.datos.Sonido;
import com.example.myapplication.room_database.Sound;
import com.example.myapplication.room_database.SoundRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio_Completar extends AppCompatActivity {

    List<Sound> asd;
    ArrayList<Sonido> numeros;
    int random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio_completar);
        setTitle("Números");
        //numeros = new ListaSonidos().listaNumeros();
        SoundRepository sr = new SoundRepository(getApplication());
        sr.getAllSounds().observe(this, new Observer<List<Sound>>() {
            @Override
            public void onChanged(List<Sound> sounds) {
                asd = sounds;
                int a = asd.size();
                String size = Integer.toString(a);
                Log.d("Tamaño", size);
                setup(obtenerNumero());
            }
        });

    }

    void setup(int rand) {
        ImageButton btnPlay = findViewById(R.id.imageButton);
        final Button aceptar = findViewById(R.id.aceptar);
        final EditText etNombre = findViewById(R.id.campo_nombre);
        final int random = rand;
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSound(asd.get(random).getRuta_sonido());
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNombre.getText().toString().equals(asd.get(random).getNombre_sonido())) {
                    Toast.makeText(getApplicationContext(), "Correcto", Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    setup(obtenerNumero());
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrecto", Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                }

            }
        });
    }


    int obtenerNumero() {
        int random = ThreadLocalRandom.current().nextInt(0, asd.size());
        return random;
    }


    private void startSound(String filename) {
        AssetFileDescriptor afd = null;
        try {
            afd = getResources().getAssets().openFd(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaPlayer player = new MediaPlayer();
        try {
            assert afd != null;
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }
}
