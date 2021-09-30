package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.myapplication.common.entities.OptionAnswer;
import com.example.myapplication.databinding.ActivityDetalleResultadoBinding;
import java.util.ArrayList;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class DetalleResultado extends AppCompatActivity {

    Boolean volverMenu;

    TextView textViewFecha;
    TextView textViewEjercicio;
    TextView textViewCategoria;
    TextView textViewRuido;
    TextView textViewIntensidad;
    TextView textViewErrores;
    TextView textViewResultados;

    private ArrayList<OptionAnswer> mOptionAnswerList = new ArrayList<>();
    private ActivityDetalleResultadoBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDetalleResultadoBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        final Toolbar toolbar = findViewById(R.id.toolbar_detalle_resultado);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String fecha = getIntent().getStringExtra("fecha");
        String ejercicio = getIntent().getStringExtra("ejercicio");
        String categoria = getIntent().getStringExtra("categoria");
        String ruido = getIntent().getStringExtra("ruido");
        String intensidad = getIntent().getStringExtra("intensidad");
        String resultado = getIntent().getStringExtra("resultado");

        volverMenu = getIntent().getBooleanExtra("volverMenu", true);

        mOptionAnswerList = (ArrayList<OptionAnswer>) getIntent().getSerializableExtra(getString(R.string.error_resume));

        textViewFecha = findViewById(R.id.tvFecha);
        textViewEjercicio = findViewById(R.id.tvEjercicio);
        textViewCategoria = findViewById(R.id.tvCategoria);
        textViewRuido = findViewById(R.id.tvRuido);
        textViewIntensidad = findViewById(R.id.tvIntensidad);
        textViewResultados = findViewById(R.id.tvResultado);

        textViewFecha.setText(fecha);
        textViewEjercicio.setText(ejercicio);
        textViewCategoria.setText(categoria);
        textViewRuido.setText(ruido);
        textViewIntensidad.setText(intensidad);
        textViewResultados.setText(resultado + "/10");

        setupDataTable();

    }

    private void setupDataTable() {
        DataTableHeader header = new DataTableHeader.Builder()
                .item("Error 1", 1)
                .item("Error 2", 1)
                .item("Respuesta", 1)
                .build();

        ArrayList<DataTableRow> rows = new ArrayList<>();
        for (OptionAnswer optionAnswer : mOptionAnswerList) {
            ArrayList<String> valueList = new ArrayList<>();
            if (optionAnswer.getErrorQuiantity()==0) {
                valueList.add("--");
                valueList.add("--");
            }else valueList.addAll(optionAnswer.getErrorList());
            if (optionAnswer.getErrorQuiantity()==1)
                valueList.add("--");
            valueList.add(optionAnswer.getCorrectAnswer());
            DataTableRow row = new DataTableRow.Builder()
                    .value(valueList.get(0))
                    .value(valueList.get(1))
                    .value(valueList.get(2))
                    .build();

            rows.add(row);
        }

        mBinding.errorDataTable.setHeader(header);
        mBinding.errorDataTable.setRows(rows);
        mBinding.errorDataTable.inflate(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        if (volverMenu) {
            finish();
            Intent intent = new Intent(DetalleResultado.this, MainActivity.class);
            startActivity(intent);
        } else {
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
