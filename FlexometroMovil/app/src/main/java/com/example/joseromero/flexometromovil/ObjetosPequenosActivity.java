package com.example.joseromero.flexometromovil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
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

import java.text.DecimalFormat;

public class ObjetosPequenosActivity extends /*AppCompat*/Activity {

    ImageView guiahorizontal, guiavertical;
    TextView tvHeight, tvWidth;
    ImageView drawingImageView, drawingImageView2, drawingImageView3;

    //Botones de guardar y calibrar
    ImageView btnSave, btnSettings;

    private Context myContext;
    private float widthMeasured, heightMeasured;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_objetos_pequenos);

        myContext = this;
        widthMeasured = 0.0f;
        heightMeasured = 0.0f;

        btnSave = (ImageView) findViewById(R.id.btnSave);
        btnSettings = (ImageView) findViewById(R.id.btnSettings);

        //rulerView = new RulerView(this);
        //rulerView.setBackgroundColor(Color.WHITE);
        //setContentView(rulerView);
        guiahorizontal = (ImageView) findViewById(R.id.btnguiahorizontal);
        guiavertical = (ImageView) findViewById(R.id.btnguiavertical);
        tvHeight = (TextView) findViewById(R.id.tvHeight);
        tvWidth = (TextView) findViewById(R.id.tvWidth);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        final float screenHeightPixels = metrics.heightPixels;
        final float screenWidthPixels = metrics.widthPixels;

        System.out.println("height pixels:" + screenHeightPixels);
        //float screenHeightMM = 147.0f;
        //final float screenWidthMM = 83.5f;

        //float screenHeightMM = 154.0f;
        //final float screenWidthMM = 90.0f;

        float widthExtra = getIntent().getFloatExtra("screenWidth", 90.0f);
        float heightExtra = getIntent().getFloatExtra("screenHeight", 154.0f);

        //float screenHeightMM = 121.5f;
        //final float screenWidthMM = 68.7f;

        float screenHeightMM = heightExtra;
        final float screenWidthMM = widthExtra;

        final float pixelsPerMM = screenHeightPixels / screenHeightMM;
        System.out.println("pixels per mm:" + pixelsPerMM);

        //float heightPixels = pixelsPerMM * 53.98f;
        float heightPixels = pixelsPerMM * 100.0f;

        ////////
        drawingImageView = (ImageView) this.findViewById(R.id.DrawingImageView);
        Bitmap bitmap = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawingImageView.setImageBitmap(bitmap);

        //Dibujando regla vertical
        int i=0, number = 0;
        for(float y=0; y<screenHeightPixels; y=y+pixelsPerMM){
            // Line
            Paint paint = new Paint();
            paint.setColor(Color.rgb(255, 153, 51));
            paint.setStrokeWidth(1);
            float startx = screenWidthPixels;
            float starty = y;
            float endx = screenWidthPixels-20;

            if(i%5 == 0){
                endx = screenWidthPixels-30;
            }
            if(i%10 == 0){
                endx = screenWidthPixels-60;

                Paint paint2 = new Paint();
                Canvas canvas2 = new Canvas(bitmap);
                //paint2.setColor(Color.WHITE);
                //paint2.setStyle(Paint.Style.FILL);
                //canvas.drawPaint(paint);

                paint2.setColor(Color.BLACK);
                paint2.setTextSize(25);
                canvas2.rotate(90, screenWidthPixels-80, y);
                canvas2.drawText("" + number, screenWidthPixels-80, y, paint2);
                number++;
            }

            float endy = y;
            canvas.drawLine(startx, starty, endx, endy, paint);
            i++;
        }

        //Dibujando regla horizontal
        i=0;
        number = 0;
        //for(float y=0; y<screenHeightPixels; y=y+pixelsPerMM){
        for(float x=screenWidthPixels; x>0; x=x-pixelsPerMM){

            // Line
            Paint paint = new Paint();
            paint.setColor(Color.rgb(255, 153, 51));
            paint.setStrokeWidth(1);
            float startx = x;
            float starty = 0;

            float endy = 20;

            if(i%5 == 0){
                endy = 30;
            }
            if(i%10 == 0){
                endy = 60;

                Paint paint3 = new Paint();
                Canvas canvas2 = new Canvas(bitmap);
                //paint2.setColor(Color.WHITE);
                //paint2.setStyle(Paint.Style.FILL);
                //canvas.drawPaint(paint);

                paint3.setColor(Color.BLACK);
                paint3.setTextSize(25);
                //canvas2.rotate(90, screenWidthPixels-40, y);
                canvas2.drawText("" + number, x, 35, paint3);
                number++;
            }

            float endx = x;
            canvas.drawLine(startx, starty, endx, endy, paint);
            i++;
        }

        //Dibujando referencias
        Paint paint3 = new Paint();
        //Canvas canvas2 = new Canvas(bitmap);
        //paint2.setColor(Color.WHITE);
        //paint2.setStyle(Paint.Style.FILL);
        //canvas.drawPaint(paint);

        paint3.setColor(Color.RED);
        paint3.setStrokeWidth(15);
        canvas.drawLine(screenWidthPixels-60, 0, screenWidthPixels, 0, paint3);
        canvas.drawLine(screenWidthPixels, 0, screenWidthPixels, 60, paint3);

        ////////

        // Dibujando guia vertical
        drawingImageView2 = (ImageView) this.findViewById(R.id.DrawingImageView2);
        final Bitmap bitmap2 = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap2);
        drawingImageView2.setImageBitmap(bitmap2);

        final Paint paint5 = new Paint();
        final Canvas canvas5 = new Canvas(bitmap2);

        paint5.setColor(Color.BLUE);
        paint5.setStrokeWidth(5);

        canvas5.drawLine(30, 0, 30, screenHeightPixels, paint5);


        // Dibujando guia horizontal
        drawingImageView3 = (ImageView) this.findViewById(R.id.DrawingImageView3);
        final Bitmap bitmap3 = Bitmap.createBitmap((int) getWindowManager()
                .getDefaultDisplay().getWidth(), (int) getWindowManager()
                .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas3 = new Canvas(bitmap3);
        drawingImageView3.setImageBitmap(bitmap3);

        final Paint paint4 = new Paint();
        final Canvas canvas4 = new Canvas(bitmap3);

        paint4.setColor(Color.GRAY);
        paint4.setStrokeWidth(5);

        canvas4.drawLine(0, 30, screenWidthPixels, 30, paint4);



        guiavertical.setOnTouchListener(new View.OnTouchListener() {
            int prevX, prevY;

            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                final RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) v.getLayoutParams();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        float measureX = (screenWidthMM - ((int) event.getRawX() / pixelsPerMM)) / 10;
                        DecimalFormat df = new DecimalFormat("0.00");

                        /////
                        Paint paint2 = new Paint();
                        Canvas canvas2 = new Canvas(bitmap2);
                        canvas2.drawColor(0, PorterDuff.Mode.CLEAR);

                        paint2.setColor(Color.BLUE);
                        paint2.setStrokeWidth(5);

                        canvas2.drawLine((int) event.getRawX(), 0, (int) event.getRawX(), screenHeightPixels, paint2);
                        /////

                        widthMeasured = Float.parseFloat(df.format(measureX));

                        tvWidth.setText("Width: " + df.format(measureX) + " cm");

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
                            float measureY = ((int) event.getRawY() / pixelsPerMM)/10;
                            DecimalFormat df = new DecimalFormat("0.00");

                            /////
                            Paint paint2 = new Paint();
                            Canvas canvas2 = new Canvas(bitmap3);
                            canvas2.drawColor(0, PorterDuff.Mode.CLEAR);

                            paint2.setColor(Color.GRAY);
                            paint2.setStrokeWidth(5);

                            canvas2.drawLine(0, (int) event.getRawY(), screenWidthPixels,(int) event.getRawY(), paint2);
                            ////

                            heightMeasured = Float.parseFloat(df.format(measureY));

                            tvHeight.setText("Height: " + df.format(measureY) + " cm");
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


        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, SettingsActivity.class);
                myContext.startActivity(intent);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext, NuevaMedicionActivity.class);
                intent.putExtra("widthMeasured", widthMeasured);
                intent.putExtra("heightMeasured", heightMeasured);
                myContext.startActivity(intent);
            }
        });

    }
}
