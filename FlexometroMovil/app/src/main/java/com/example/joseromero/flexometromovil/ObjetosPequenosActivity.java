package com.example.joseromero.flexometromovil;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ObjetosPequenosActivity extends Activity {

    ImageView guiahorizontal, guiavertical;
    TextView tvHeight, tvWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_objetos_pequenos);

        guiahorizontal = (ImageView) findViewById(R.id.btnguiahorizontal);
        guiavertical = (ImageView) findViewById(R.id.btnguiavertical);
        tvHeight = (TextView) findViewById(R.id.tvHeight);
        tvWidth = (TextView) findViewById(R.id.tvWidth);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        float screenHeightPixels = metrics.heightPixels;
        //float screenHeightMM = 131.0f;
        float screenHeightMM = 147.0f;
        final float screenWidthMM = 83.5f;

        final float pixelsPerMM = screenHeightPixels / screenHeightMM;

        //float heightPixels = pixelsPerMM * 53.98f;
        float heightPixels = pixelsPerMM * 100.0f;

        guiavertical.setOnTouchListener(new View.OnTouchListener() {
            int prevX, prevY;

            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                final RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) v.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        //System.out.println("getx:"+((int) event.getX()));
                        //System.out.println("getyPrecission:"+((int) event.getYPrecision()));

                        //if ((int) event.getRawY() > 244 && (int) event.getRawY() < 1770) {
                        //System.out.println("getx:" + ((int) event.getRawX()));
                        tvWidth.setText("Width: "+(screenWidthMM-((int) event.getRawX()/pixelsPerMM)));

                        par.rightMargin += (int) event.getRawY() - prevY;
                        prevY = (int) event.getRawY();
                        par.leftMargin += (int) event.getRawX() - prevX;
                        prevX = (int) event.getRawX();
                        v.setLayoutParams(par);

                        //}
                        return true;


                    }
                    case MotionEvent.ACTION_UP: {
                        //if ((int) event.getRawY() > 244 && (int) event.getRawY() < 1770) {
                        par.leftMargin += (int) event.getRawY() - prevY;
                        par.rightMargin += (int) event.getRawX() - prevX;
                        v.setLayoutParams(par);

                        //}
                        return true;

                    }
                    case MotionEvent.ACTION_DOWN: {
                        //if((int) event.getRawY()>244){
                        prevX = (int) event.getRawX();
                        prevY = (int) event.getRawY();
                        par.topMargin = -2 * v.getHeight();
                        par.rightMargin = -2 * v.getWidth();
                        v.setLayoutParams(par);
                        return true;
                        //}


                    }
                }
                return false;
            }
        });


        guiahorizontal.setOnTouchListener(new View.OnTouchListener() {
            int prevX, prevY;

            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                final RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) v.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        //System.out.println("gety:"+((int) event.getY()));
                        //System.out.println("getyPrecission:"+((int) event.getYPrecision()));

                        if ((int) event.getRawY() < 1770) {

                            //System.out.println("gety:" + ((int) event.getRawY()));
                            //tvHeight.setText("y: "+((int) event.getRawY()));
                            tvHeight.setText("Height: "+((int) event.getRawY()/pixelsPerMM));
                            par.topMargin += (int) event.getRawY() - prevY;
                            prevY = (int) event.getRawY();
                            par.leftMargin += (int) event.getRawX() - prevX;
                            prevX = (int) event.getRawX();
                            v.setLayoutParams(par);

                        }
                        return true;


                    }
                    case MotionEvent.ACTION_UP: {
                        if ((int) event.getRawY() < 1770) {
                            par.topMargin += (int) event.getRawY() - prevY;
                            par.leftMargin += (int) event.getRawX() - prevX;
                            v.setLayoutParams(par);

                        }
                        return true;

                    }
                    case MotionEvent.ACTION_DOWN: {
                        //if((int) event.getRawY()>244){
                        prevX = (int) event.getRawX();
                        prevY = (int) event.getRawY();
                        par.bottomMargin = -2 * v.getHeight();
                        par.rightMargin = -2 * v.getWidth();
                        v.setLayoutParams(par);
                        return true;
                        //}


                    }
                }
                return false;
            }
        });

        /*

        DisplayMetrics metrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(metrics);
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        System.out.println("heightpixels:"+metrics.heightPixels);

        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();
        System.out.println("heightdisplay:"+height);

        float screenHeightPixels = metrics.heightPixels;
        //float screenHeightMM = 131.0f;
        float screenHeightMM = 147.0f;

        float pixelsPerMM = screenHeightPixels / screenHeightMM;

        //float heightPixels = pixelsPerMM * 53.98f;
        float heightPixels = pixelsPerMM * 100.0f;
        */

        /*
        btn.setText("Hola");
        btn.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btn.getLayoutParams().width=400;
        btn.getLayoutParams().height= (int) heightPixels;
        */
    }
}
