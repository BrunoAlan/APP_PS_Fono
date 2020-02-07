package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.datos.Constantes;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundRepository;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ConfiguracionAgregarSonido extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final int RUTA_RESULTANTE =1;

    private static final int RECORD_AND_STORAGE_RQ = 12356;
    ArrayAdapter<String> adapterTipoDatoPalabra;
    Button grabar, guardar;
    EditText textInputEditText;
    TextInputLayout textInputCategorias, textInputNombre;
    AutoCompleteTextView spinnerCategorias;
    String resultado;
    String nombreSonido, categoriaSonido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_agregar_sonido);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setup();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    public void setup() {
        textInputCategorias = findViewById(R.id.textInputCategorias);
        textInputNombre = findViewById(R.id.textInputNombre);
        adapterTipoDatoPalabra = new ArrayAdapter<>(getApplicationContext(), R.layout.dropdown_menu_popup_item, Constantes.FILTRO_PRACTICA);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        spinnerCategorias.setAdapter(adapterTipoDatoPalabra);
        textInputEditText = findViewById(R.id.textInput_nombreSonido);

        grabar = findViewById(R.id.btn_conf_grabar);
        guardar = findViewById(R.id.btn_guardar);
        guardar.setEnabled(false);
        grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecordActiviy();
            }
        });


        spinnerCategorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoriaSonido = parent.getItemAtPosition(position).toString();
            }
        });




        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    SoundRepository sr = new SoundRepository(getApplication());
                    sr.agregarSonido(new Sound(nombreSonido,categoriaSonido,resultado,Constantes.PERSONALIZADO));

                }
            }
        });
    }

    @AfterPermissionGranted(RECORD_AND_STORAGE_RQ)
    public void startRecordActiviy() {
        String[] perms = {Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            Intent intent = new Intent(getApplicationContext(), AgregarSonido.class);
            //startActivity(intent);
            startActivityForResult(intent,RUTA_RESULTANTE);
        } else {
            EasyPermissions.requestPermissions(this,
                    "Se necesita permiso para poder grabar los sonidos personalizados", RECORD_AND_STORAGE_RQ
                    , perms);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private boolean validate() {
        boolean isValid = true;
        if (spinnerCategorias.getText().toString().isEmpty()) {
            textInputCategorias.setError("Seleccione una categoría");
            isValid = false;
        } else {
            textInputCategorias.setErrorEnabled(false);

        }

        if(textInputEditText.getText().toString().isEmpty()){
            textInputNombre.setError("Ingrese un Nombre");
            isValid=false;
        }
        else{
            textInputNombre.setErrorEnabled(false);
            nombreSonido = textInputEditText.getText().toString();
        }
        return isValid;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RUTA_RESULTANTE) {
            if (resultCode == Activity.RESULT_OK) {
                resultado = data.getStringExtra("Resultado");
                guardar.setEnabled(true);
                //Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "No resultado", Toast.LENGTH_SHORT).show();

            }
        }

    }
}
