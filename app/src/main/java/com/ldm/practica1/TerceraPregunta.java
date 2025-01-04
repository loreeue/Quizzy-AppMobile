package com.ldm.practica1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;

public class TerceraPregunta extends AppCompatActivity {
    int puntos;
    ListView lista;
    List<String> autores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercera_pregunta);

        //inicializo el toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //puntos activity anterior
        puntos = getIntent().getIntExtra("puntos", 0);

        //añado los autores a la lista
        lista = findViewById(R.id.listViewAuthors);

        autores = new ArrayList<>();
        autores.add("Miguel de Unamuno");
        autores.add("Gabriel García Márquez");
        autores.add("Miguel de Cervantes");
        autores.add("Federico García Lorca");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilo_letra, autores);
        
        lista.setAdapter(adapter);

        //muestro si el resultado es correcto/incorrecto y cuento los puntos
        lista.setOnItemClickListener((adapterView, view, position, id) -> {
            String autorSeleccionado = autores.get(position);

            if (autorSeleccionado.equals("Miguel de Cervantes")) {
                puntos += 3;
                mostrarAlertDialogCorrecto();
            }
            else {
                puntos -= 2;
                mostrarAlertDialogIncorrecto();
            }
        });

        //paso a la siguiente activity
        ImageButton siguienteButton = findViewById(R.id.btn_sig);
        siguienteButton.setOnClickListener(v -> {
            //paso a la siguiente pregunta con la puntuación
            Intent intent = new Intent(this, CuartaPregunta.class);
            intent.putExtra("puntos", puntos);
            Log.d("TerceraPregunta", "Puntos: " + puntos);
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
        Intent siguiente = new Intent(this, CuartaPregunta.class);
        startActivity(siguiente);
    }

    public void volver(View v) {
        Intent volver = new Intent(this, SegundaPregunta.class);
        startActivity(volver);
    }
}