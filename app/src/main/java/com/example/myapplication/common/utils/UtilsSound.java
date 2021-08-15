package com.example.myapplication.common.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;

import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

public class UtilsSound {
    /**
     * Al tocar un botón de respuesta sonará un ruido que variará de acuerdo a si la respuesta fue o no correcta
     * @param view
     * @param isCorrectAnswer
     */
    public static void announceAnswerSound(View view, boolean isCorrectAnswer) {
        Uri soundUri = null;
        MediaPlayer mediaPlayer = null;
        if (isCorrectAnswer)
            soundUri = Uri.parse("android.resource://" + view.getContext().getPackageName() + "/" + R.raw.correct_answer);
         else
            soundUri = Uri.parse("android.resource://" + view.getContext().getPackageName() + "/" + R.raw.incorrect_answer);
        mediaPlayer = MediaPlayer.create(view.getContext(), soundUri);
        mediaPlayer.start();
    }
}
