package com.example.myapplication.Cotrollers;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.example.myapplication.R;

import java.io.IOException;

public class ReproductorDeAudioController {

    public void startSpundNoNoise(String filename, Context context) {
        AssetFileDescriptor afd = null;
        try {
            afd = context.getResources().getAssets().openFd(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaPlayer player = new MediaPlayer();
        try {
            assert afd != null;
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }



    public void startSoundWithNoise(String filename, int noise, Context context){
        AssetFileDescriptor afd = null;
        try {
            afd = context.getResources().getAssets().openFd(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaPlayer mpSound = new MediaPlayer();
        MediaPlayer mpRuido = MediaPlayer.create(context, R.raw.ruido_personas);
        mpRuido.start();
        try {
            assert afd != null;
            mpSound.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mpSound.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mpSound.start();
        try {
            Thread.sleep(mpSound.getDuration()+200);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        mpRuido.stop();

    }
}
