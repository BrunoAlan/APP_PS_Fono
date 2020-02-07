package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.SoundsAdapter;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AdminitrarSonidos extends AppCompatActivity {



    private SoundViewModel soundViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminitrar_sonidos);

        FloatingActionButton agregarSonido = findViewById(R.id.fab_agregarSonido);
        RecyclerView mRecyclerView = findViewById(R.id.recyclerAdministrar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        final SoundsAdapter soundsAdapter = new SoundsAdapter();
        mRecyclerView.setAdapter(soundsAdapter);
        soundViewModel = ViewModelProviders.of(this).get(SoundViewModel.class);
        soundViewModel.getAllSounds().observe(this, new Observer<List<Sound>>() {
            @Override
            public void onChanged(List<Sound> sounds) {
                soundsAdapter.submitList(sounds);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (soundsAdapter.getSoundAt(viewHolder.getAdapterPosition()).getPersonalizado().equals("dfksldfhñslfa"/*Constantes.NO_PERSONALIZADO*/ )) {
                    Toast.makeText(AdminitrarSonidos.this, "Ese sonido no se puede eliminar", Toast.LENGTH_SHORT).show();
                    soundsAdapter.notifyDataSetChanged();
                } else {
                    soundViewModel.eliminarSonido(soundsAdapter.getSoundAt(viewHolder.getAdapterPosition()));
                }
            }
        }).attachToRecyclerView(mRecyclerView);

        soundsAdapter.setOnItemClickListener( new SoundsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Sound sound) {
                Toast.makeText(AdminitrarSonidos.this, sound.getNombre_sonido(), Toast.LENGTH_SHORT).show();
            }
        });

        agregarSonido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ConfiguracionAgregarSonido.class);
                startActivity(intent);

            }
        });

    }

}
