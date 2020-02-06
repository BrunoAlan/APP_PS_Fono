package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.room_database.palabras.SoundViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarSonido extends AppCompatActivity {
    String outputFile;
    String date;
    Button grabar, pausar, play, aceptar;
    SoundViewModel soundViewModel;
    private MediaRecorder mAudioRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_sonido);

        soundViewModel = ViewModelProviders.of(this).get(SoundViewModel.class);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int with = displayMetrics.widthPixels;
        int hight = displayMetrics.heightPixels;
        getWindow().setLayout((int) (with * .8), (int) (hight * .7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);

        grabar = findViewById(R.id.btn_grabar);
        pausar = findViewById(R.id.btn_parar);
        play = findViewById(R.id.btn_play);
        aceptar = findViewById(R.id.aceptar);

        pausar.setEnabled(false);
        play.setEnabled(false);
        aceptar.setEnabled(false);
        checkFolder();
        date = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
        Log.d("fecha", date);
        final String nombreFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/FonoApp_Sonidos/" + date + ".3gp";
        Log.d("nombre file", nombreFile);
        //outputFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/FonoApp_Sonidos/archivo.3gp";


        grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAudioRecorder = new MediaRecorder();
                mAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                mAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                mAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                mAudioRecorder.setAudioEncodingBitRate(128000);
                mAudioRecorder.setAudioSamplingRate(96000);
                mAudioRecorder.setOutputFile(nombreFile);
                try {
                    mAudioRecorder.prepare();
                    mAudioRecorder.start();

                } catch (IOException | IllegalStateException e) {
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
                aceptar.setEnabled(true);
                Toast.makeText(AgregarSonido.this, "Audio grabado correctamente", Toast.LENGTH_SHORT).show();

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mMediaPlayer = new MediaPlayer();
                try {
                    mMediaPlayer.setDataSource(nombreFile);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Resultado", nombreFile);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }


    public void checkFolder() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/FonoApp_Sonidos/";
        File dir = new File(path);
        boolean isDirectoryCreated = dir.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = dir.mkdir();
        }
        if (isDirectoryCreated) {
            // do something\
            Log.d("Folder", "Already Created");
        }
    }
}
