package com.example.myapplication.common.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.example.myapplication.Controllers.ReproductorDeAudioController;
import com.example.myapplication.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;

public class UtilsCommon {
    public static void displayAlertMessage(View root, String titulo, String cuerpo) {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(root.getContext())
                .setTitle(titulo)
                .setMessage(cuerpo)
                .setPositiveButton(R.string.common_label_ok, null);
        builder.show();
    }

    public static void displayNoiseSettingsAlert(View root) {
        AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext())
                .setView(R.layout.dialog_game_settings);
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                float oldIntensity = ReproductorDeAudioController.getmInstance().getIntensidad();
                alertDialog.findViewById(R.id.btnCancel).setOnClickListener(v -> {
                    ReproductorDeAudioController.getmInstance().setIntensidad(oldIntensity);
                    alertDialog.dismiss();
                });
                alertDialog.findViewById(R.id.btnSave).setOnClickListener(v -> alertDialog.dismiss());
                alertDialog.setOnCancelListener(dialog1 -> {
                    ReproductorDeAudioController.getmInstance().setIntensidad(oldIntensity);
                });
                SeekBar sbNoise = alertDialog.findViewById(R.id.sbNoise);
                sbNoise.setProgress((int) ((ReproductorDeAudioController.getmInstance().getIntensidad()*100)));
                sbNoise.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        ReproductorDeAudioController.getmInstance().setIntensidad(seekBar.getProgress() / 100f);
                    }
                });
            }
        });
        alertDialog.show();
    }

    public static void showSnackbar(View view, String text) {
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setDuration(1000).show();
    }

    public static void showSnackbar(View view, String text, int duration) {
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setDuration(duration).show();
    }
}
