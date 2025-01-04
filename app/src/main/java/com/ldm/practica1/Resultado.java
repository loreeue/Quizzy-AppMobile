package com.ldm.practica1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Resultado extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        //inicializo el toolbar
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //recojo los puntos finales
        int puntosFinales = getIntent().getIntExtra("puntos", 0);
        TextView puntosTextView = findViewById(R.id.puntos);
        puntosTextView.setText("Puntos totales: " + puntosFinales);
    }

    public void volver_a_empezar(View v) {
        Intent siguiente = new Intent(this, MainActivity.class);
        startActivity(siguiente);
    }
}