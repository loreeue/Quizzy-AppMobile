package com.ldm.practica1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SegundaPregunta extends AppCompatActivity {
    int puntos;
    EditText respuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_pregunta);

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
        respuesta = findViewById(R.id.editTextText);
        ImageButton validarButton = findViewById(R.id.btn_sig);
        validarButton.setOnClickListener(v -> {
            boolean acierto = verificarRespuesta();
            if (acierto)
                puntos += 3;
            else
                puntos -= 2;

            //paso a la siguiente pregunta con la puntuación
            Intent intent = new Intent(this, TerceraPregunta.class);
            intent.putExtra("puntos", puntos);
            Log.d("SegundaPregunta", "Puntos: " + puntos);
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
        String respuesta2 = respuesta.getText().toString().trim().toUpperCase();
        return respuesta2.equals("B");
    }

    public void siguiente(View v) {
        Intent siguiente = new Intent(this, TerceraPregunta.class);
        startActivity(siguiente);
    }

    public void volver(View v) {
        Intent volver = new Intent(this, PrimeraPregunta.class);
        startActivity(volver);
    }
}