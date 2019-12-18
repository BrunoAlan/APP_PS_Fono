package com.example.myapplication.logica;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.SoundsAdapter;
import com.example.myapplication.room_database.Sound;
import com.example.myapplication.room_database.SoundViewModel;

import java.io.IOException;
import java.util.List;

public class Practica extends AppCompatActivity  {
    private SoundViewModel soundViewModel;
    MediaPlayer m = new MediaPlayer();

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
                startSound(sound.getRuta_sonido());
            }
        });


        soundViewModel = ViewModelProviders.of(this).get(SoundViewModel.class);
        soundViewModel.getAllSounds().observe(this, new Observer<List<Sound>>() {
            @Override
            public void onChanged(List<Sound> sounds) {
                adapter.setSound(sounds);
            }
        });
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