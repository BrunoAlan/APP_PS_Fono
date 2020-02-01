package com.example.myapplication.logica;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Cotrollers.ReproductorDeAudioController;
import com.example.myapplication.R;
import com.example.myapplication.SoundsAdapter;
import com.example.myapplication.datos.Constantes;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundViewModel;

import java.util.List;

public class Practica extends AppCompatActivity implements LifecycleOwner {
    ReproductorDeAudioController rp = new ReproductorDeAudioController();
    SoundViewModel soundViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practica);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Práctica");

        AutoCompleteTextView mSpinner = findViewById(R.id.spinner);
        ArrayAdapter<String> mAdapterFilter = new ArrayAdapter<>(getApplicationContext(),R.layout.dropdown_menu_popup_item,Constantes.SUBCATEGORIAS);
        mSpinner.setAdapter(mAdapterFilter);
        soundViewModel = ViewModelProviders.of(this).get(SoundViewModel.class);


        RecyclerView recycler = findViewById(R.id.recyclerSonidos);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        final SoundsAdapter adapter = new SoundsAdapter();
        recycler.setAdapter(adapter);

        soundViewModel.getAllSounds().observe(Practica.this, new Observer<List<Sound>>() {
            @Override
            public void onChanged(List<Sound> sounds) {
                adapter.submitList(sounds);
            }

        });



        mSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String categoria = parent.getItemAtPosition(position).toString();
                switch (categoria) {
                    case (Constantes.MESES):
                        soundViewModel.getMesesSounds().observe(Practica.this, new Observer<List<Sound>>() {
                            @Override
                            public void onChanged(List<Sound> sounds) {
                                adapter.submitList(sounds);
                            }
                        });
                        break;

                    case (Constantes.DIAS_SEMANA):
                        soundViewModel.getDiasSounds().observe(Practica.this, new Observer<List<Sound>>() {
                                    @Override
                                    public void onChanged(List<Sound> sounds) {
                                        adapter.submitList(sounds);
                                        
                                    }
                                }
                        );
                        break;

                    case (Constantes.COLORES):
                        soundViewModel.getColoresSounds().observe(Practica.this, new Observer<List<Sound>>() {
                            @Override
                            public void onChanged(List<Sound> sounds) {
                                adapter.submitList(sounds);
                            }
                        });
                        break;

                    case (Constantes.NUMEROS):
                        soundViewModel.getNumerosSounds().observe(Practica.this, new Observer<List<Sound>>() {
                            @Override
                            public void onChanged(List<Sound> sounds) {
                                adapter.submitList(sounds);
                            }
                        });
                        break;


                }
            }
        });


        adapter.setOnItemClickListener(new SoundsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Sound sound) {
                if(sound.getPersonalizado().equals(Constantes.PERSONALIZADO)){
                    rp.playSonidoPersonalizado(sound.getRuta_sonido());
                }else {
                    Toast.makeText(Practica.this, sound.getNombre_sonido(), Toast.LENGTH_SHORT).show();
                    rp.startSoundNoNoise(sound.getRuta_sonido(), Practica.this);

                }
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