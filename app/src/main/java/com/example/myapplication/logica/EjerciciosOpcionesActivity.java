package com.example.myapplication.logica;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.common.adapters.OnOptionClickListener;
import com.example.myapplication.common.adapters.OptionsAdapter;
import com.example.myapplication.common.entities.OptionAnswer;
import com.example.myapplication.controllers.ReproductorDeAudioController;
import com.example.myapplication.DetalleResultado;
import com.example.myapplication.R;
import com.example.myapplication.common.utils.UtilsCommon;
import com.example.myapplication.common.utils.UtilsSound;
import com.example.myapplication.databinding.ActivityEjerciciosOpcionesBinding;
import com.example.myapplication.datos.Constantes;
import com.example.myapplication.room_database.palabras.Sound;
import com.example.myapplication.room_database.palabras.SoundRepository;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class EjerciciosOpcionesActivity extends AppCompatActivity implements OnOptionClickListener {
    public SoundRepository sr;
    private List<Sound> listaSonidos;
    private int puntajeCorrecto;
    private int puntajeIncorrecto;
    private int repeticiones;
    private int incorrectCounterStage = 0;
    private int optionsQuantity = 0;
    private final int maxRepetitions = 10;
    private int opcionCorrecta;
    private final int wrongAnswersLimit = 2;
    private String ruido, subdato, errores = "";
    private final ArrayList<OptionAnswer> mOptionAnswersList = new ArrayList<>();
    private final ArrayList<String> mOptions = new ArrayList<>();
    private ArrayList<Integer> optionList;
    private OptionsAdapter mAdapter;
    private ReproductorDeAudioController mReproductorDeAudioController;
    private ActivityEjerciciosOpcionesBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityEjerciciosOpcionesBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        puntajeCorrecto = 0;
        puntajeIncorrecto = 0;
        repeticiones = 0;

        //Datos pasados desde la configuración
        subdato = getIntent().getStringExtra("subDato");
        ruido = getIntent().getStringExtra("tipoRuido");

        float intensidad = getIntent().getFloatExtra("intensidad", .1f);
        mReproductorDeAudioController = ReproductorDeAudioController.getmInstance();
        mReproductorDeAudioController.setIntensidad(intensidad);

        mBinding.btnSettings.setOnClickListener(v -> UtilsCommon.displayNoiseSettingsAlert(mBinding.getRoot()));
        sr = new SoundRepository(getApplication());
        switch (subdato) {
            case Constantes.DIAS_SEMANA:
                sr.getDiasSounds().observe(this, sounds -> {
                    listaSonidos = sounds;
                    setup();
                });
                break;
            case Constantes.NUMEROS:
                sr.getNumerosSounds().observe(this, sounds -> {
                    listaSonidos = sounds;
                    setup();
                });
                break;
            case Constantes.MESES:
                sr.getMesesSounds().observe(this, sounds -> {
                    listaSonidos = sounds;
                    setup();
                });
                break;

            case Constantes.COLORES:
                sr.getColoresSounds().observe(this, sounds -> {
                    listaSonidos = sounds;
                    setup();
                });
                break;
        }

    }

    void setup() {
        mOptions.clear();
        String tipoEjercicio = getIntent().getStringExtra(getString(R.string.tipo_ejercicio));
        if (tipoEjercicio.equals(Constantes.J_IDENTIFICAR_TRES_OPCIONES))
            optionsQuantity = 3;
        else if (tipoEjercicio.equals(Constantes.J_IDENTIFICAR_CINCO_OPCIONES))
            optionsQuantity = 5;
        else  if (tipoEjercicio.equals(Constantes.J_TODA_LA_CATEGORIA))
            optionsQuantity = listaSonidos.size();
        optionList = obtenerNumero(optionsQuantity);
        setTexts(optionList);
        mAdapter = new OptionsAdapter(mOptions, this);
        mBinding.recyclerView.setAdapter(mAdapter);
        Random rand = new Random();
        opcionCorrecta = rand.nextInt(optionList.size());
        OptionAnswer optionAnswer = new OptionAnswer();
        optionAnswer.setCorrectAnswer(listaSonidos.get(optionList.get(opcionCorrecta)).getNombre_sonido());
        mOptionAnswersList.add(optionAnswer);


        mBinding.btnPlay.setOnClickListener(v -> {
            if (activarSonido()) {
                sr.getRutaSonido(ruido).observe(this, sounds -> {
                    ReproductorDeAudioController rp = new ReproductorDeAudioController();
                    rp.startSoundWithNoise(
                            listaSonidos.get(optionList.get(opcionCorrecta)).getRuta_sonido(),
                            sounds.get(0).getRuta_sonido(),
                            mReproductorDeAudioController.getIntensidad(),
                            getApplicationContext()
                    );
                });
            } else {
                ReproductorDeAudioController rp = new ReproductorDeAudioController();
                rp.startSoundNoNoise(listaSonidos.get(optionList.get(opcionCorrecta)).getRuta_sonido(), getApplicationContext());
            }
        });

    }

    void setTexts(ArrayList<Integer> optionList) {
        for (int i = 0; i < optionList.size(); i++)
            mOptions.add(listaSonidos.get(optionList.get(i)).getNombre_sonido());
    }

    private ArrayList<Integer> obtenerNumero(int optionQuantity) {
        ArrayList<Integer> randoms = new ArrayList<>();
        for (int i = 0; i < listaSonidos.size(); i++) randoms.add(i);
        Collections.shuffle(randoms);
        ArrayList<Integer> optionsList = new ArrayList<>();
        for (int i = 0; i < optionQuantity; i++)
            optionsList.add(randoms.get(i));
        return optionsList;
    }

    void modificarPuntaje(Boolean isCorrect) {
        String points;

        if (isCorrect) {
            puntajeCorrecto++;
            incorrectCounterStage = 0;
            points = Integer.toString(puntajeCorrecto);
            UtilsSound.announceAnswerSound(mBinding.getRoot(), true);
            int correctAnswerRes = getRandomCorrectAnswerText();
            UtilsCommon.showSnackbar(mBinding.getRoot(), getString(correctAnswerRes));
            mBinding.tvPuntajeCorrecto.setText(points);
        } else {
            puntajeIncorrecto++;
            incorrectCounterStage++;
            points = Integer.toString(puntajeIncorrecto);
            UtilsSound.announceAnswerSound(mBinding.getRoot(), false);
            if (incorrectCounterStage >= wrongAnswersLimit) {
                incorrectCounterStage = 0;
                UtilsCommon.displayAlertMessage(mBinding.getRoot(),
                        "¡Te has equivocado más de " + wrongAnswersLimit + " veces!",
                        "La respuesta correcta era: \"" + listaSonidos.get(optionList.get(opcionCorrecta)).getNombre_sonido() + "\""
                                + "\nPasemos al siguiente.");
                ReproductorDeAudioController.getmInstance().startSoundNoNoise(listaSonidos.get(optionList.get(opcionCorrecta)).getRuta_sonido(), getApplicationContext());
                setup();
            } else {
                int incorrectAnswerRes = getRandomIncorrectAnswerText();
                UtilsCommon.showSnackbar(mBinding.getRoot(), getString(incorrectAnswerRes));
            }
            mBinding.tvPuntajeIncorrecto.setText(points);
        }


        if (finEjercicio()) {
            Date date = Calendar.getInstance().getTime();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String today = formatter.format(date);
            Intent intent = new Intent(getApplicationContext(), DetalleResultado.class);
            intent.putExtra("fecha", today);
            intent.putExtra("ejercicio", Constantes.J_IDENTIFICAR_CINCO_OPCIONES);
            intent.putExtra("categoria", subdato);
            intent.putExtra("ruido", ruido);
            intent.putExtra("intensidad", mReproductorDeAudioController.getIntensidadPorcentual() + "%");
            intent.putExtra(getString(R.string.error_resume), mOptionAnswersList);
            intent.putExtra("resultado", puntajeCorrecto + "");
            startActivity(intent);
        }
    }

    private int getRandomIncorrectAnswerText() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.string.incorrect1);
        arrayList.add(R.string.incorrect2);
        arrayList.add(R.string.incorrect3);
        Random rand = new Random();
        int index = rand.nextInt(arrayList.size());
        return arrayList.get(index);
    }

    private int getRandomCorrectAnswerText() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.string.correct1);
        arrayList.add(R.string.correct2);
        arrayList.add(R.string.correct3);
        arrayList.add(R.string.correct4);
        arrayList.add(R.string.correct5);
        arrayList.add(R.string.correct6);
        arrayList.add(R.string.correct7);
        arrayList.add(R.string.correct8);
        Random rand = new Random();
        int index = rand.nextInt(arrayList.size());
        return arrayList.get(index);
    }

    boolean activarSonido() {
        return !ruido.equals("Sin Ruido");
    }

    boolean finEjercicio() {
        repeticiones++;
        return (repeticiones == maxRepetitions);
    }

    @Override
    public void onClickListener(MaterialButton btnOption, int index) {
        if (listaSonidos.get(optionList.get(opcionCorrecta)).getNombre_sonido() == btnOption.getText()) {
            modificarPuntaje(true);
            btnOption.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
            setup();
        } else {
            OptionAnswer optionAnswer = mOptionAnswersList.get(mOptionAnswersList.size() - 1);
            optionAnswer.addError(btnOption.getText().toString());
            mOptionAnswersList.set(mOptionAnswersList.size() - 1, optionAnswer);
            errores = errores + listaSonidos.get(optionList.get(opcionCorrecta)).getNombre_sonido() + "\n";
            modificarPuntaje(false);
            btnOption.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake_animation));
        }
    }
}