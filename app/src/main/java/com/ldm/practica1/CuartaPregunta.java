package com.ldm.practica1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class CuartaPregunta extends AppCompatActivity {
    int puntos;
    Spinner spinner;
    List<String> paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuarta_pregunta);

        //inicializo el toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //puntos activity anterior
        puntos = getIntent().getIntExtra("puntos", 0);

        //añado los países a la lista
        spinner = findViewById(R.id.spinnerCountries);

        paises = new ArrayList<>();
        paises.add("Canadá");
        paises.add("Estados Unidos");
        paises.add("Rusia");
        paises.add("China");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilo_letra, paises);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //muestro si el resultado es correcto/incorrecto
        Button corregirButton = findViewById(R.id.btn_corregir);
        corregirButton.setOnClickListener(v -> {
            String paisSeleccionado = spinner.getSelectedItem().toString();

            if (paisSeleccionado.equals("Rusia"))
                mostrarAlertDialogCorrecto();
            else
                mostrarAlertDialogIncorrecto();
        });

        //cuento los puntos y paso a la siguiente activity
        findViewById(R.id.btn_sig).setOnClickListener(v -> {
            String paisSeleccionado = spinner.getSelectedItem().toString();

            if (paisSeleccionado.equals("Rusia"))
                puntos += 3;
            else
                puntos -= 2;

            //paso a la siguiente pregunta con la puntuación
            Intent intent = new Intent(this, QuintaPregunta.class);
            intent.putExtra("puntos", puntos);
            Log.d("CuartaPregunta", "Puntos: " + puntos);
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

    public void siguiente(View v) {
        Intent siguiente = new Intent(this, QuintaPregunta.class);
        startActivity(siguiente);
    }

    public void volver(View v) {
        Intent volver = new Intent(this, TerceraPregunta.class);
        startActivity(volver);
    }
}