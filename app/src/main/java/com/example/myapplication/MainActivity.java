package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    setup();
    }

    void setup(){
        Button btnFonema = findViewById(R.id.btnNumeros);
        Button btnPalabra = findViewById(R.id.btnDias);

        btnFonema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getBaseContext(), Numeros_completar.class);
               startActivity(intent);
            }
        });

        btnPalabra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"palabra",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), DiasSemana.class);
                startActivity(intent);
            }
        });

    }


}
