package com.example.joseromero.flexometromovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class ObjetosPequenosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetos_pequenos);


        Button btn = (Button) findViewById(R.id.btn1);

        DisplayMetrics metrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(metrics);
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        float screenHeightPixels = metrics.heightPixels;
        //float screenHeightMM = 131.0f;
        float screenHeightMM = 147.0f;

        float pixelsPerMM = screenHeightPixels / screenHeightMM;

        float heightPixels = pixelsPerMM * 53.98f;

        btn.setText("Hola");
        btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btn.getLayoutParams().width=400;
        btn.getLayoutParams().height= (int) heightPixels;
    }
}
