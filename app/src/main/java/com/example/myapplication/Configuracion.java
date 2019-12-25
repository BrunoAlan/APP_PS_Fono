package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.datos.TipoSonido;
import com.example.myapplication.logica.Ejercicio_Completar;
import com.example.myapplication.logica.Ejercicio_Tres_Opciones;

public class Configuracion extends AppCompatActivity {

    String[] tipoDato = new TipoSonido().getTipoSonido();
    String[] tipoDataPalabra = new TipoSonido().getTipoDatoPalabra();
    String[] ruido = {"Ambulancia", "Tráfico", "Multitud de gente", "Recreo de niños"};
    String[] tipoEjercicio = {"Identificar entre 3 opciones", "Identificar entre 5 opciones", "Escribir lo que oyó"};

    AutoCompleteTextView spinnerTipoDato, spinnerTipoDatoPalabra, spinnerNoise, spinnerTipoEjercicio;

    String confSubDato;
    String confTipoEjercio;
    String confRuido;

    ArrayAdapter<String> adapterTipoDato, adapterRuido, adapterSubTipoDato, adapterEjercicio;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        final Toolbar toolbar = findViewById(R.id.toolbar_configuracion);
        toolbar.setTitle("Configuracion");

        spinnerTipoDato = findViewById(R.id.tipoDato);
        spinnerTipoDatoPalabra = findViewById(R.id.tipoDatoPalabra);
        spinnerTipoEjercicio = findViewById(R.id.tipoEjercicio);
        spinnerNoise = findViewById(R.id.ruido);

        adapterTipoDato = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, tipoDato);
        adapterSubTipoDato = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, tipoDataPalabra);
        adapterEjercicio = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, tipoEjercicio);
        adapterRuido = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, ruido);

        spinnerTipoDato.setAdapter(adapterTipoDato);
        spinnerTipoEjercicio.setAdapter(adapterEjercicio);
        spinnerNoise.setAdapter(adapterRuido);

        spinnerTipoDato.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tipo = parent.getItemAtPosition(position).toString();
                switch (tipo) {
                    case "Palabra":
                        spinnerTipoDatoPalabra.setAdapter(adapterSubTipoDato);
                        break;
                }
            }
        });

        spinnerTipoDatoPalabra.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcion = parent.getItemAtPosition(position).toString();
                confSubDato = opcion;
            }
        });

        spinnerTipoEjercicio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcion = parent.getItemAtPosition(position).toString();
                confTipoEjercio = opcion;
            }
        });

        spinnerNoise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String opcion = parent.getItemAtPosition(position).toString();
                confRuido = opcion;
            }
        });

        //RUIDO

        final Button btn = findViewById(R.id.btn_ejercicio);
        final TextView tvIntensidad = findViewById(R.id.tvIntensidad);
        final SeekBar seekBarIntensidad = findViewById(R.id.seekBar);

        final Switch switchRuido = findViewById(R.id.switchRuido);
        switchRuido.setChecked(false);

        switchRuido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    spinnerNoise.setVisibility(View.VISIBLE);
                    tvIntensidad.setVisibility(View.VISIBLE);
                    seekBarIntensidad.setVisibility(View.VISIBLE);
                } else {
                    spinnerNoise.setVisibility(View.INVISIBLE);
                    tvIntensidad.setVisibility(View.INVISIBLE);
                    seekBarIntensidad.setVisibility(View.INVISIBLE);
                }
            }
        });
        //FINALIZADA LA CONFIGURACIÓN INICIO LA EJERCITACIÓN

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (confTipoEjercio) {
                    case "Identificar entre 3 opciones":
                        Intent intent = new Intent(getApplicationContext(), Ejercicio_Tres_Opciones.class);
                        if (!switchRuido.isChecked()) {
                            confRuido = "Sin Ruido";
                        }
                        intent.putExtra("subDato", confSubDato);
                        intent.putExtra("tipoRuido", confRuido);
                        startActivity(intent);
                        break;
                    case "Escribir lo que oyó":
                        Intent intent2 = new Intent(getApplicationContext(), Ejercicio_Completar.class);
                        if (!switchRuido.isChecked()) {
                            confRuido = "Sin Ruido";
                        }
                        intent2.putExtra("subDato", confSubDato);
                        intent2.putExtra("tipoRuido", confRuido);
                        startActivity(intent2);
                }
            }
        });
    }
}
