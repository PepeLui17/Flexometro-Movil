package com.example.joseromero.flexometromovil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ObjetoReferenciaActivity extends Activity {
    Button btnTomarFoto;
    TextView btnSalir;
    Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objeto_referencia);

        myContext = this;

        btnTomarFoto = (Button) findViewById(R.id.btnTomarFoto);
        btnSalir = (TextView) findViewById(R.id.btnSalir);

        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String widthText = ((EditText) findViewById(R.id.txtWidth)).getText().toString();
                String heightText = ((EditText) findViewById(R.id.txtHeight)).getText().toString();

                float width = Float.parseFloat(widthText);
                float height = Float.parseFloat(heightText);

                Intent intent = new Intent(myContext, PorFotoActivity.class);
                intent.putExtra("objectWidth", width);
                intent.putExtra("objectHeight", height);
                myContext.startActivity(intent);
                finish();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
