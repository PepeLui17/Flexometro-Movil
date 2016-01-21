package com.example.joseromero.flexometromovil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class NuevaMedicionActivity extends Activity {

    Button btnGuardar;
    TextView btnCancelar;
    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_medicion);

        myContext = this;

        float widthExtra = getIntent().getFloatExtra("widthMeasured", 0.0f);
        float heightExtra = getIntent().getFloatExtra("heightMeasured", 0.0f);

        ((EditText) findViewById(R.id.txtWidth)).setText(""+widthExtra);
        ((EditText) findViewById(R.id.txtHeight)).setText(""+heightExtra);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnCancelar = (TextView) findViewById(R.id.btnCancelar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = ((EditText) findViewById(R.id.txtName)).getText().toString();
                String widthText = ((EditText) findViewById(R.id.txtWidth)).getText().toString();
                String heightText = ((EditText) findViewById(R.id.txtHeight)).getText().toString();

                float width = Float.parseFloat(widthText);
                float height = Float.parseFloat(heightText);

                Medicion medicion = new Medicion(nameText, width, height);
                addMedicionToFile(medicion);

                Toast.makeText(myContext, "Medición guardada", Toast.LENGTH_SHORT).show();

                finish();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void addMedicionToFile(Medicion medicion)
    {
        try {
            FileOutputStream fileout=openFileOutput("mediciones.txt", MODE_APPEND);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(medicion.getNombre() + "," + medicion.getAncho() + "," + medicion.getAlto() + "\n");
            outputWriter.close();
            //Toast.makeText(getBaseContext(), "File saved successfully!",Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            //Toast.makeText(getBaseContext(), "ERROR: " + e.toString(),Toast.LENGTH_LONG).show();

        }
    }
}
