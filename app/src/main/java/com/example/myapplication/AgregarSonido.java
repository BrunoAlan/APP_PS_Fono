package com.example.myapplication;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class AgregarSonido extends AppCompatActivity {
    private MediaRecorder mAudioRecorder;
    String outputFile;
    Button grabar, pausar, play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sonido);

        grabar = findViewById(R.id.btn_grabar);
        pausar = findViewById(R.id.btn_parar);
        play = findViewById(R.id.btn_play);

        pausar.setEnabled(false);
        play.setEnabled(false);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/archivo.3gp";

        Log.d("ruta" , outputFile);
        mAudioRecorder = new MediaRecorder();
        mAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mAudioRecorder.setOutputFile(outputFile);

        grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mAudioRecorder.prepare();
                    mAudioRecorder.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }

                grabar.setEnabled(false);
                pausar.setEnabled(true);
                Toast.makeText(AgregarSonido.this, "Grabando Audio", Toast.LENGTH_SHORT).show();
            }
        });


        pausar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAudioRecorder.stop();
                mAudioRecorder.release();
                mAudioRecorder = null;
                grabar.setEnabled(true);
                pausar.setEnabled(false);
                play.setEnabled(true);
                Toast.makeText(AgregarSonido.this, "Audio grabado correctamente", Toast.LENGTH_SHORT).show();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mMediaPlayer = new MediaPlayer();
                try {
                    mMediaPlayer.setDataSource(outputFile);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
