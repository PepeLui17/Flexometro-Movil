package com.example.joseromero.flexometromovil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private FrameLayout mainLayout;
    private LinearLayout overlayMask;
    private Context myContext;
    Button btnMediciones;
    //private boolean overlayVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_main);

        myContext = this;
        btnMediciones = (Button) findViewById(R.id.btnMediciones);
        mainLayout = (FrameLayout) findViewById(R.id.mainLayout);
        overlayMask = (LinearLayout) findViewById(R.id.overlayMask);

        final Button btnMedir = (Button) findViewById(R.id.btnMedir);
        btnMedir.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = layoutInflater.inflate(R.layout.popup_medir, null);

                final PopupWindow popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                Button btnObjetosPequenos = (Button) popupView.findViewById(R.id.btnObjetosPequenos);
                Button btnPorFoto = (Button) popupView.findViewById(R.id.btnPorFoto);
                //Button btnPorMovimiento = (Button) popupView.findViewById(R.id.btnPorMovimiento);

                btnObjetosPequenos.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(myContext, ObjetosPequenosActivity.class);
                        popupWindow.dismiss();
                        overlayMask.setVisibility(View.INVISIBLE);
                        myContext.startActivity(intent);
                    }
                });

                btnPorFoto.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(myContext, PorFotoActivity.class);
                        popupWindow.dismiss();
                        overlayMask.setVisibility(View.INVISIBLE);
                        myContext.startActivity(intent);
                    }
                });

                /*btnPorMovimiento.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(myContext, PorMovimientoActivity.class);
                        popupWindow.dismiss();
                        overlayMask.setVisibility(View.INVISIBLE);
                        myContext.startActivity(intent);
                    }
                });*/

                popupWindow.showAtLocation(mainLayout, Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);

                overlayMask.setVisibility(View.VISIBLE);

                overlayMask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        overlayMask.setVisibility(View.INVISIBLE);
                    }
                });

            }
        });

        btnMediciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, MisMedicionesActivity.class);
                myContext.startActivity(intent);
            }
        });
/*
        //RelativeLayout layout = (RelativeLayout) findViewById(R.id.rootLayout);

        Button btn = (Button) findViewById(R.id.btn1);

        DisplayMetrics metrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        //float px = 53.98f * metrics.ydpi * (1.0f/25.4f);
        float mmPixel = metrics.ydpi * (1.0f/25.4f);
        float px = mmPixel/53.98f;

        //float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM, 54, getResources().getDisplayMetrics());
        btn.setText("Hola");
        btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        //btn.setWidth(400);
        //btn.setWidth(400);

        int pxI = (int) px;
        btn.getLayoutParams().width=400;
        btn.getLayoutParams().height= pxI;

        Toast.makeText(this, "px float: " + px + "  ; px int: " + pxI, Toast.LENGTH_LONG);
        Log.i("TTT", "px float: " + px + "  ; px int: " + pxI);
*/
    }
}
