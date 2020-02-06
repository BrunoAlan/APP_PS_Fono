package com.example.myapplication.Cotrollers;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class ReproductorDeAudioController {

    public void startSoundNoNoise(String filename, Context context) {
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



    public void startSoundWithNoise(String filename, String rutaRuido,float intensidad, Context context){
        AssetFileDescriptor afd = null;
        try {
            afd = context.getResources().getAssets().openFd(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AssetFileDescriptor afdRuido = null;
        try {
            afdRuido = context.getResources().getAssets().openFd(rutaRuido);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaPlayer mpSound = new MediaPlayer();
        MediaPlayer mpRuido = new MediaPlayer();


        try {
            assert afdRuido != null;
            mpRuido.setDataSource(afdRuido.getFileDescriptor(),afdRuido.getStartOffset(),afdRuido.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mpRuido.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mpRuido.setVolume(intensidad,intensidad);
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


    public void playSonidoPersonalizado(String ruta){
        MediaPlayer mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(ruta);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startSoundOraciones(String filename, Context context) {
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
        int duration = player.getDuration();
        player.start();
        try {
            Thread.sleep(duration/2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        player.stop();
    }




    public void startSoundOracionesNoise(String filename, String rutaRuido,float intensidad, Context context){
        AssetFileDescriptor afd = null;
        try {
            afd = context.getResources().getAssets().openFd(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AssetFileDescriptor afdRuido = null;
        try {
            afdRuido = context.getResources().getAssets().openFd(rutaRuido);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MediaPlayer mpSound = new MediaPlayer();
        MediaPlayer mpRuido = new MediaPlayer();


        try {
            assert afdRuido != null;
            mpRuido.setDataSource(afdRuido.getFileDescriptor(),afdRuido.getStartOffset(),afdRuido.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mpRuido.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mpRuido.setVolume(intensidad,intensidad);
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
        int duration = mpSound.getDuration();
        mpSound.start();
        try {
            Thread.sleep(duration/2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        mpRuido.stop();
        mpSound.stop();

    }


}
