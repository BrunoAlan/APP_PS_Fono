package com.example.myapplication;

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
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.datos.Constantes;
import com.google.android.material.textfield.TextInputLayout;

public class Configuracion extends AppCompatActivity {
    String[] ruido = {"Ambulancia", "Tráfico", "Multitud de gente", "Recreo de niños"};


    AutoCompleteTextView spinnerCategoria, spinnerSubCategoria, spinnerRuido, spinnerTipoEjercicio;
    TextInputLayout textInputCategoria, textInputSubcategoria, textInputTipoEjercicio, textInputRuido;
    String confSubDato;
    String confTipoEjercio;
    String confRuido;
    float confIntensidad = 0;

    ArrayAdapter<String> adapterCategoria, adapterRuido, adapterSubCategoria, adapterEjercicio;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        final Toolbar toolbar = findViewById(R.id.toolbar_configuracion);
        toolbar.setTitle("Configuracion");
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
        adapterSubCategoria = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, Constantes.SUBCATEGORIAS);
        adapterEjercicio = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, Constantes.TIPOS_EJERCICIOS);
        adapterRuido = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, ruido);
        spinnerCategoria.setAdapter(adapterCategoria);
        spinnerTipoEjercicio.setAdapter(adapterEjercicio);
        spinnerRuido.setAdapter(adapterRuido);

        spinnerCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tipo = parent.getItemAtPosition(position).toString();
                switch (tipo) {

                    //FONEMA, PALABRA, ORACIONES, CANCIONES, INSTRUMENTOS, ESTILOS_MUSICALES, VOCES_FAMILIARES
                    case (Constantes.PALABRA):
                        spinnerSubCategoria.setAdapter(adapterSubCategoria);
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
                validate();

 /*               switch (confTipoEjercio) {
                    case Constantes.IDENTIFICAR_TRES_OPCIONES:
                        Intent intent = new Intent(getApplicationContext(), Ejercicio_Tres_Opciones.class);
                        if (!switchRuido.isChecked()) {
                            confRuido = "Sin Ruido";
                        }
                        intent.putExtra("subDato", confSubDato);
                        intent.putExtra("tipoRuido", confRuido);
                        intent.putExtra("intensidad",confIntensidad);
                        startActivity(intent);
                        break;
                    case Constantes.ESCRIBIR_LO_QUE_OYO:
                        Intent intent2 = new Intent(getApplicationContext(), Ejercicio_Completar.class);
                        if (!switchRuido.isChecked()) {
                            confRuido = "Sin Ruido";
                        }
                        intent2.putExtra("subDato", confSubDato);
                        intent2.putExtra("tipoRuido", confRuido);
                        intent2.putExtra("intensidad",confIntensidad);
                        startActivity(intent2);
                        break;
                        default:
                            ConstraintLayout ly = findViewById(R.id.layout_config);
                            Snackbar snackbar = Snackbar.make(ly,"asdasd",Snackbar.LENGTH_SHORT);
                            snackbar.show();
                }*/

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void validate() {
        boolean isValid = true;
        if (spinnerCategoria.getText().toString().isEmpty()) {
            textInputCategoria.setError("Seleccione una categoría");
            isValid = false;
        } else {
            textInputCategoria.setErrorEnabled(false);
        }

        if (spinnerSubCategoria.getText().toString().isEmpty()) {
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
        if (isValid) {
            Toast.makeText(this, "Correcto", Toast.LENGTH_SHORT).show();
        }
    }
}
