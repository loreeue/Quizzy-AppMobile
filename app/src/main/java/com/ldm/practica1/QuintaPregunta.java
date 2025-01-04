package com.ldm.practica1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class QuintaPregunta extends AppCompatActivity {
    int puntos;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quinta_pregunta);

        radioGroup = findViewById(R.id.respuestas);

        //inicializo el toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //puntos activity anterior
        puntos = getIntent().getIntExtra("puntos", 0);

        //muestro si el resultado es correcto/incorrecto
        Button corregirButton = findViewById(R.id.btn_corregir);
        corregirButton.setOnClickListener(v -> {
            boolean acierto = verificarRespuesta();
            if (acierto)
                mostrarAlertDialogCorrecto();
            else
                mostrarAlertDialogIncorrecto();
        });

        //cuento los puntos y paso a la siguiente activity
        ImageButton validarButton = findViewById(R.id.btn_sig);
        validarButton.setOnClickListener(v -> {
            boolean acierto = verificarRespuesta();
            if (acierto)
                puntos += 3;
            else
                puntos -= 2;

            //paso a la siguiente pregunta con la puntuación
            Intent intent = new Intent(this, Resultado.class);
            intent.putExtra("puntos", puntos);
            Log.d("QuintaPregunta", "Puntos: " + puntos);
            startActivity(intent);
            finish();
        });
    }

    private void mostrarAlertDialogIncorrecto() {
        new AlertDialog.Builder(this)
                .setTitle("¡Incorrecto!")
                .setMessage("Lo siento, pierdes 2 puntos.")
                .setPositiveButton("Seguir", (dialog, which) -> dialog.dismiss())
                .setNegativeButton("Volver a empezar", (dialog, which) -> {
                    Intent intent = new Intent(this, PrimeraPregunta.class);
                    startActivity(intent);
                    finish();  //finaliza la actividad actual para que no se vuelva a la pantalla anterior
                })
                .show();
    }

    private void mostrarAlertDialogCorrecto() {
        new AlertDialog.Builder(this)
                .setTitle("¡Correcto!")
                .setMessage("Has respondido correctamente y sumas 3 puntos.")
                .setPositiveButton("Aceptar", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private boolean verificarRespuesta() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        return selectedId == R.id.respuesta2;
    }

    public void siguiente(View v) {
        Intent siguiente = new Intent(this, Resultado.class);
        startActivity(siguiente);
    }

    public void volver(View v) {
        Intent volver = new Intent(this, CuartaPregunta.class);
        startActivity(volver);
    }
}