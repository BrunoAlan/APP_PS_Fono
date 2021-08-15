package com.example.myapplication.common.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;

public class UtilsCommon {
    public static void displayAlertMessage(View root, String titulo, String cuerpo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext(), R.style.DialogFragmentTheme)
                .setTitle(titulo)
                .setMessage(cuerpo)
                .setPositiveButton(R.string.common_label_ok, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(root.getResources().getColor(R.color.design_default_color_primary, null));

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
