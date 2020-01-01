package com.example.myapplication.logica;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Cotrollers.ReproductorDeAudioController;
import com.example.myapplication.R;
import com.example.myapplication.SoundsAdapter;
import com.example.myapplication.room_database.Sound;
import com.example.myapplication.room_database.SoundViewModel;

import java.util.List;

public class Practica extends AppCompatActivity {
    ReproductorDeAudioController rp = new ReproductorDeAudioController();
    private SoundViewModel soundViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practica);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Práctica");


        RecyclerView recycler = findViewById(R.id.recyclerSonidos);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        final SoundsAdapter adapter = new SoundsAdapter();
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new SoundsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Sound sound) {
                Toast.makeText(Practica.this, sound.getNombre_sonido(), Toast.LENGTH_SHORT).show();
                rp.startSoundNoNoise(sound.getRuta_sonido(), Practica.this);

            }
        });

        soundViewModel = ViewModelProviders.of(this).get(SoundViewModel.class);
        soundViewModel.getNumerosSounds().observe(this, new Observer<List<Sound>>() {
            @Override
            public void onChanged(List<Sound> sounds) {
                adapter.setSound(sounds);
            }
        });
    }
}





















       /*

        SoundsAdapter adapter = new SoundsAdapter(listDatos);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MediaPlayer mp = MediaPlayer.create(getApplicationContext(),
//                        listDatos.get(recycler.getChildAdapterPosition(v)).getRuta());
               // play();
                startSound(listDatos.get(recycler.getChildAdapterPosition(v)).getRuta_sonido());


            }
        });
        recycler.setAdapter(adapter);
    }






}
*/