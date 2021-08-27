package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.evrencoskun.tableview.TableView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DetalleResultado extends AppCompatActivity {

    Boolean volverMenu;

    TextView textViewFecha;
    TextView textViewEjercicio;
    TextView textViewCategoria;
    TextView textViewRuido;
    TextView textViewIntensidad;
    TextView textViewErrores;
    TextView textViewResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_resultado);

        final Toolbar toolbar = findViewById(R.id.toolbar_detalle_resultado);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String fecha = getIntent().getStringExtra("fecha");
        String ejercicio = getIntent().getStringExtra("ejercicio");
        String categoria = getIntent().getStringExtra("categoria");
        String ruido = getIntent().getStringExtra("ruido");
        String intensidad = getIntent().getStringExtra("intensidad");
        String errores = getIntent().getStringExtra("errores");
        String resultado = getIntent().getStringExtra("resultado");
        ArrayList<String> wAnswersArray = getIntent().getStringArrayListExtra("wAnswers");
        ArrayList<String> cAnswersArray = getIntent().getStringArrayListExtra("cAnswers");
        ArrayList<String> cAnswersSoundPathArray = getIntent().getStringArrayListExtra("cAnswersSoundPathArray");
        volverMenu = getIntent().getBooleanExtra("volverMenu", true);

        Log.d("resultado pasado", fecha + ejercicio + categoria + ruido + intensidad + errores + resultado);


        textViewFecha = findViewById(R.id.tvFecha);
        textViewEjercicio = findViewById(R.id.tvEjercicio);
        textViewCategoria = findViewById(R.id.tvCategoria);
        textViewRuido = findViewById(R.id.tvRuido);
        textViewIntensidad = findViewById(R.id.tvIntensidad);
        textViewErrores = findViewById(R.id.tvErrores);
        textViewResultados = findViewById(R.id.tvResultado);

        textViewFecha.setText(fecha);
        textViewEjercicio.setText(ejercicio);
        textViewCategoria.setText(categoria);
        textViewRuido.setText(ruido);
        textViewIntensidad.setText(intensidad);
        textViewErrores.setText(errores);
        textViewResultados.setText(resultado+"/10");

        TableView tableView = findViewById(R.id.tlWrongAnswers);
       // tableView.setrow

    }

    @Override
    public void onBackPressed() {
        if (volverMenu) {
            finish();
            Intent intent = new Intent(DetalleResultado.this, MainActivity.class);
            startActivity(intent);
        }else{
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
