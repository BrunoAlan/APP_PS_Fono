package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapters.ResultadosAdapter;
import com.example.myapplication.room_database.resultados.Resultado;
import com.example.myapplication.room_database.resultados.ResultadoViewModel;

import java.util.List;

public class AdministrarResultados extends AppCompatActivity {

    private ResultadoViewModel resultadoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar_resultados);


        final Toolbar toolbar = findViewById(R.id.toolbar_administrar_resultados);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        RecyclerView mRecyclerView = findViewById(R.id.recyclerAdministrarResultados);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        final ResultadosAdapter resultadosAdapter = new ResultadosAdapter();
        mRecyclerView.setAdapter(resultadosAdapter);
        resultadoViewModel = ViewModelProviders.of(this).get(ResultadoViewModel.class);
        resultadoViewModel.getAllResultadoos().observe(this, new Observer<List<Resultado>>() {
            @Override
            public void onChanged(List<Resultado> resultados) {
                resultadosAdapter.submitList(resultados);
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
                    resultadoViewModel.eliminarResultado(resultadosAdapter.getResultadoAt(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(mRecyclerView);

        resultadosAdapter.setOnItemClickListener(new ResultadosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Resultado resultado) {
                //Toast.makeText(AdministrarResultados.this, resultado.getId()+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DetalleResultado.class);
                intent.putExtra("fecha", resultado.getFecha());
                intent.putExtra("ejercicio", resultado.getTipo_ejercicio());
                intent.putExtra("categoria", resultado.getCategoria());
                intent.putExtra("ruido", resultado.getRuido());
                intent.putExtra("intensidad", resultado.getIntensidad());
                intent.putExtra("errores", resultado.getErrores());
                intent.putExtra("resultado", resultado.getResultado());
                intent.putExtra("volverMenu",false);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
