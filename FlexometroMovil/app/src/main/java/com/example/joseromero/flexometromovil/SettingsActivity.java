package com.example.joseromero.flexometromovil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class SettingsActivity extends Activity {

    Button btnGuardar;
    TextView btnSalir;
    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        myContext = this;

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnSalir = (TextView) findViewById(R.id.btnSalir);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String widthText = ((EditText) findViewById(R.id.txtWidth)).getText().toString();
                String heightText = ((EditText) findViewById(R.id.txtHeight)).getText().toString();

                float width = Float.parseFloat(widthText);
                float height = Float.parseFloat(heightText);

                Intent intent = new Intent(myContext, ObjetosPequenosActivity.class);
                intent.putExtra("screenWidth", width);
                intent.putExtra("screenHeight", height);
                myContext.startActivity(intent);
                finish();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, ObjetosPequenosActivity.class);
                myContext.startActivity(intent);
                finish();
            }
        });


    }

}
