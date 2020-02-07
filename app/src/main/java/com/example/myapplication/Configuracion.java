package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import androidx.lifecycle.Observer;

import com.example.myapplication.datos.Constantes;
import com.example.myapplication.logica.Ejercicio_Cinco_Opciones;
import com.example.myapplication.logica.Ejercicio_Completar;
import com.example.myapplication.logica.Ejercicio_Completar_Frase_NO_Opciones;
import com.example.myapplication.logica.Ejercicio_Tres_Opciones;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundRepository;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class Configuracion extends AppCompatActivity {

    AutoCompleteTextView spinnerCategoria, spinnerSubCategoria, spinnerRuido, spinnerTipoEjercicio;
    TextInputLayout textInputCategoria, textInputSubcategoria, textInputTipoEjercicio, textInputRuido;
    String confSubDato;
    String confTipoEjercio;
    String confRuido;
    String modo;
    float confIntensidad = 0;

    ArrayAdapter<String> adapterCategoria, adapterRuido, adapterSubCategoria, adapterEjercicio;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        final Toolbar toolbar = findViewById(R.id.toolbar_configuracion);
        toolbar.setTitle("Configuración");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinnerCategoria = findViewById(R.id.tipoDato);
        spinnerSubCategoria = findViewById(R.id.tipoDatoPalabra);
        spinnerTipoEjercicio = findViewById(R.id.tipoEjercicio);
        spinnerRuido = findViewById(R.id.ruido);
        textInputRuido = findViewById(R.id.textInput_ruido);
        textInputCategoria = findViewById(R.id.textInput_categoria);
        textInputSubcategoria = findViewById(R.id.textInput_subcategoria);
        textInputTipoEjercicio = findViewById(R.id.textInput_tipoEjercicio);
        adapterCategoria = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, Constantes.CATEGORIAS);
        adapterSubCategoria = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, Constantes.SUBCATEGORIAS_PALABRAS);
        adapterEjercicio = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, Constantes.TIPOS_EJERCICIOS);
        spinnerCategoria.setAdapter(adapterCategoria);
        spinnerTipoEjercicio.setAdapter(adapterEjercicio);

        modo = getIntent().getStringExtra("Modo");
        Log.d("modo ejercitacion",modo);



        final ArrayList<String> ruidosList = new ArrayList<>();
        adapterRuido = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, ruidosList);
        spinnerRuido.setAdapter(adapterRuido);

        SoundRepository sr = new SoundRepository(getApplication());
        sr.getRuidosSounds().observe(this, new Observer<List<Sound>>() {
            @Override
            public void onChanged(List<Sound> sounds) {
                for(int i = 0; i< sounds.size(); i++){
                    ruidosList.add(sounds.get(i).getNombre_sonido());
                }
                adapterRuido.notifyDataSetChanged();

            }
        });



        spinnerCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tipo = parent.getItemAtPosition(position).toString();
                switch (tipo) {

                    //FONEMA, PALABRA, ORACIONES, CANCIONES, INSTRUMENTOS, ESTILOS_MUSICALES, VOCES_FAMILIARES
                    case (Constantes.PALABRA):
                        spinnerSubCategoria.setAdapter(adapterSubCategoria);
                        break;

                    case (Constantes.ORACIONES):
                        adapterEjercicio = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, Constantes.TIPOS_EJERCICIOS_ORACIONES);
                        spinnerTipoEjercicio.setAdapter(adapterEjercicio);
                        spinnerSubCategoria.dismissDropDown();
                        confSubDato= Constantes.ORACIONES;
                        break;

                }
            }
        });

        spinnerSubCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confSubDato = parent.getItemAtPosition(position).toString();
            }
        });

        spinnerTipoEjercicio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confTipoEjercio = parent.getItemAtPosition(position).toString();
            }
        });

        spinnerRuido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                confRuido = parent.getItemAtPosition(position).toString();
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
                    textInputRuido.setVisibility(View.VISIBLE);
                    spinnerRuido.setVisibility(View.VISIBLE);
                    tvIntensidad.setVisibility(View.VISIBLE);
                    seekBarIntensidad.setVisibility(View.VISIBLE);
                } else {
                    textInputRuido.setVisibility(View.INVISIBLE);
                    spinnerRuido.setVisibility(View.INVISIBLE);
                    tvIntensidad.setVisibility(View.INVISIBLE);
                    seekBarIntensidad.setVisibility(View.INVISIBLE);
                }
            }
        });

        seekBarIntensidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private float getConverted(int intVal) {
                float floatVal = 0;
                floatVal = .1f * intVal;
                return floatVal;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Toast.makeText(Configuracion.this, "Valor"+getConverted(progress), Toast.LENGTH_SHORT).show();
                confIntensidad = getConverted(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //FINALIZADA LA CONFIGURACIÓN INICIO LA EJERCITACIÓN

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    switch (confTipoEjercio) {
                        case Constantes.IDENTIFICAR_TRES_OPCIONES:
                            Intent intent = new Intent(getApplicationContext(), Ejercicio_Tres_Opciones.class);
                            if (!switchRuido.isChecked()) {
                                confRuido = "Sin Ruido";
                            }
                            intent.putExtra("subDato", confSubDato);
                            intent.putExtra("tipoRuido", confRuido);
                            intent.putExtra("intensidad",confIntensidad);
                            intent.putExtra("modo",modo);
                            startActivity(intent);
                            break;

                        case Constantes.IDENTIFICAR_CINCO_OPCIONES:
                            Intent intent3 = new Intent(getApplicationContext(), Ejercicio_Cinco_Opciones.class);
                            if (!switchRuido.isChecked()) {
                                confRuido = "Sin Ruido";
                            }
                            intent3.putExtra("subDato", confSubDato);
                            intent3.putExtra("tipoRuido", confRuido);
                            intent3.putExtra("intensidad",confIntensidad);
                            intent3.putExtra("modo",modo);
                            startActivity(intent3);
                            break;
                        case Constantes.ESCRIBIR_LO_QUE_OYO:
                            Intent intent2 = new Intent(getApplicationContext(), Ejercicio_Completar.class);
                            if (!switchRuido.isChecked()) {
                                confRuido = "Sin Ruido";
                            }
                            intent2.putExtra("subDato", confSubDato);
                            intent2.putExtra("tipoRuido", confRuido);
                            intent2.putExtra("intensidad",confIntensidad);
                            intent2.putExtra("modo",modo);
                            startActivity(intent2);
                            break;


                        case Constantes.COMPLETAR_ORACION_SIN_OPCIONES:
                            Intent intent4 = new Intent(getApplicationContext(), Ejercicio_Completar_Frase_NO_Opciones.class);
                            if (!switchRuido.isChecked()) {
                                confRuido = "Sin Ruido";
                            }
                            intent4.putExtra("subDato", confSubDato);
                            intent4.putExtra("tipoRuido", confRuido);
                            intent4.putExtra("intensidad",confIntensidad);
                            intent4.putExtra("modo",modo);
                            startActivity(intent4);
                            break;
                    }
                }

            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private boolean validate() {
        boolean isValid = true;
        if (spinnerCategoria.getText().toString().isEmpty()) {
            textInputCategoria.setError("Seleccione una categoría");
            isValid = false;
        } else {
            textInputCategoria.setErrorEnabled(false);
        }

        if (spinnerSubCategoria.getText().toString().isEmpty() && !spinnerCategoria.getText().toString().equals(Constantes.ORACIONES)) {
            textInputSubcategoria.setError("Seleccione una subcategoría");
        } else {
            textInputSubcategoria.setErrorEnabled(false);
        }

        if (spinnerTipoEjercicio.getText().toString().isEmpty()) {
            textInputTipoEjercicio.setError("Seleccione un ejercicio");
            isValid = false;
        } else {
            textInputTipoEjercicio.setErrorEnabled(false);
        }

        return isValid;
    }
}
