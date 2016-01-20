package com.example.joseromero.flexometromovil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PorFotoActivity extends Activity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView fotoTomada;

    ImageView guiaReferencia1, guiaReferencia2, guiaHorizontal1, guiaHorizontal2;
    ImageView drawingImageView1, drawingImageView2;

    int refY1=0, refY2=0, guia1=0, guia2=0;
    //Button photoButton;
    //Uri outputFileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_por_foto);
        fotoTomada = (ImageView)this.findViewById(R.id.fotoTomada);



        /////







        /////


        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            super.onActivityResult(requestCode, resultCode, data);

            Uri imageUri = data.getData();
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                fotoTomada.setImageBitmap(bitmap);
                fotoTomada.setRotation(90);
                FrameLayout.LayoutParams paramsFoto = new FrameLayout.LayoutParams(2000, 2000);
                //RelativeLayout.LayoutParams paramsFoto = new RelativeLayout.LayoutParams(2000, 2000);

                paramsFoto.leftMargin = -450;
                //paramsFoto.topMargin = paramsButtonPanel.topMargin - getPixelsFromDp(55);
                fotoTomada.setLayoutParams(paramsFoto);//(width,height);

                /////
                guiaReferencia1 = (ImageView) findViewById(R.id.btnguiaReferencia1);
                guiaReferencia2 = (ImageView) findViewById(R.id.btnguiaReferencia2);
                guiaHorizontal1 = (ImageView) findViewById(R.id.btnguiahorizontal1);
                guiaHorizontal2 = (ImageView) findViewById(R.id.btnguiahorizontal2);


                ////////
                drawingImageView1 = (ImageView) this.findViewById(R.id.DrawingImageView);
                final Bitmap bitmap1 = Bitmap.createBitmap((int) getWindowManager()
                        .getDefaultDisplay().getWidth(), (int) getWindowManager()
                        .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap1);
                drawingImageView1.setImageBitmap(bitmap1);

                ////////
                drawingImageView2 = (ImageView) this.findViewById(R.id.DrawingImageView2);
                final Bitmap bitmap2 = Bitmap.createBitmap((int) getWindowManager()
                        .getDefaultDisplay().getWidth(), (int) getWindowManager()
                        .getDefaultDisplay().getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas3 = new Canvas(bitmap2);
                drawingImageView2.setImageBitmap(bitmap2);


                //DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

                float screenHeightPixels = metrics.heightPixels;
                final float screenWidthPixels = metrics.widthPixels;


                final float medidaReferenciaHeight = 53.98f;
                final int[] pixelsReferenciaHeight = {0};


                guiaReferencia1.setOnTouchListener(new View.OnTouchListener() {
                    int prevX, prevY;

                    @Override
                    public boolean onTouch(final View v, final MotionEvent event) {
                        //final RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        final FrameLayout.LayoutParams par = (FrameLayout.LayoutParams) v.getLayoutParams();
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_MOVE: {
                                //System.out.println("gety:"+((int) event.getY()));
                                //System.out.println("getyPrecission:"+((int) event.getYPrecision()));

                                if ((int) event.getRawY() < 1770) {

                                    //System.out.println("gety:" + ((int) event.getRawY()));
                                    //tvHeight.setText("y: "+((int) event.getRawY()));
                                    //float measureY = ((int) event.getRawY() / pixelsPerMM);
                                    //DecimalFormat df = new DecimalFormat("0.0");

                                    //tvHeight.setText("Height: " + df.format(measureY));

                                    refY1 = (int) event.getRawY();

                                    if(refY2!=0){
                                        Paint paint2 = new Paint();
                                        Canvas canvas2 = new Canvas(bitmap1);
                                        canvas2.drawColor(0, PorterDuff.Mode.CLEAR);

                                        //paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

                                        paint2.setColor(Color.BLACK);
                                        paint2.setStrokeWidth(5);
                                        paint2.setTextSize(40);

                                        int number = Math.abs(refY1-refY2);
                                        pixelsReferenciaHeight[0] = Math.abs(refY1-refY2);

                                        int posy = refY1+number/2;
                                        //canvas2.drawText("" + number, screenWidthPixels - 50, posy, paint2);
                                        canvas2.drawText("" + medidaReferenciaHeight+" mm", screenWidthPixels - 70, posy, paint2);
                                    }


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



                guiaReferencia2.setOnTouchListener(new View.OnTouchListener() {
                    int prevX, prevY;

                    @Override
                    public boolean onTouch(final View v, final MotionEvent event) {
                        //final RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        final FrameLayout.LayoutParams par = (FrameLayout.LayoutParams) v.getLayoutParams();
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_MOVE: {
                                //System.out.println("gety:"+((int) event.getY()));
                                //System.out.println("getyPrecission:"+((int) event.getYPrecision()));

                                if ((int) event.getRawY() < 1770) {

                                    //System.out.println("gety:" + ((int) event.getRawY()));
                                    //tvHeight.setText("y: "+((int) event.getRawY()));
                                    //float measureY = ((int) event.getRawY() / pixelsPerMM);
                                    //DecimalFormat df = new DecimalFormat("0.0");

                                    //tvHeight.setText("Height: " + df.format(measureY));

                                    refY2 = (int) event.getRawY();

                                    if(refY1!=0){
                                        Paint paint2 = new Paint();
                                        Canvas canvas2 = new Canvas(bitmap1);
                                        canvas2.drawColor(0, PorterDuff.Mode.CLEAR);

                                        //paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

                                        paint2.setColor(Color.BLACK);
                                        paint2.setStrokeWidth(5);
                                        paint2.setTextSize(40);

                                        int number = Math.abs(refY1-refY2);
                                        pixelsReferenciaHeight[0] = Math.abs(refY1-refY2);

                                        int posy = refY1+number/2;
                                        //canvas2.drawText("" + number, screenWidthPixels - 50, posy, paint2);
                                        canvas2.drawText("" + medidaReferenciaHeight+" mm", screenWidthPixels - 70, posy, paint2);
                                    }

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



                guiaHorizontal1.setOnTouchListener(new View.OnTouchListener() {
                    int prevX, prevY;

                    @Override
                    public boolean onTouch(final View v, final MotionEvent event) {
                        //final RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        final FrameLayout.LayoutParams par = (FrameLayout.LayoutParams) v.getLayoutParams();
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_MOVE: {
                                //System.out.println("gety:"+((int) event.getY()));
                                //System.out.println("getyPrecission:"+((int) event.getYPrecision()));

                                if ((int) event.getRawY() < 1770) {

                                    //System.out.println("gety:" + ((int) event.getRawY()));
                                    //tvHeight.setText("y: "+((int) event.getRawY()));
                                    //float measureY = ((int) event.getRawY() / pixelsPerMM);
                                    //DecimalFormat df = new DecimalFormat("0.0");

                                    //tvHeight.setText("Height: " + df.format(measureY));

                                    guia1 = (int) event.getRawY();
                                    //System.out.println("guia1: "+ guia1);

                                    if(guia2!=0){
                                        Paint paint = new Paint();
                                        Canvas canvas = new Canvas(bitmap2);
                                        canvas.drawColor(0, PorterDuff.Mode.CLEAR);

                                        //System.out.println("guia2: "+ guia2);
                                        //paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

                                        paint.setColor(Color.BLACK);
                                        paint.setStrokeWidth(5);
                                        paint.setTextSize(40);

                                        int number = Math.abs(guia1-guia2);

                                        DecimalFormat df = new DecimalFormat("0.0");

                                        float medidaReal = (medidaReferenciaHeight* number)/pixelsReferenciaHeight[0];

                                        int posy = guia1+number/2;
                                        //canvas2.drawText("" + number, screenWidthPixels - 80, y, paint2);
                                        canvas.drawText("" + df.format(medidaReal)+" mm", 50, posy, paint);
                                    }



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



                guiaHorizontal2.setOnTouchListener(new View.OnTouchListener() {
                    int prevX, prevY;

                    @Override
                    public boolean onTouch(final View v, final MotionEvent event) {
                        //final RelativeLayout.LayoutParams par = (RelativeLayout.LayoutParams) v.getLayoutParams();
                        final FrameLayout.LayoutParams par = (FrameLayout.LayoutParams) v.getLayoutParams();
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_MOVE: {

                                if ((int) event.getRawY() < 1770) {

                                    //System.out.println("gety:" + ((int) event.getRawY()));
                                    //tvHeight.setText("y: "+((int) event.getRawY()));
                                    //float measureY = ((int) event.getRawY() / pixelsPerMM);
                                    //DecimalFormat df = new DecimalFormat("0.0");

                                    guia2 = (int) event.getRawY();

                                    if(guia1!=0){
                                        Paint paint = new Paint();
                                        Canvas canvas = new Canvas(bitmap2);
                                        canvas.drawColor(0, PorterDuff.Mode.CLEAR);

                                        //System.out.println("guia2: "+ guia2);
                                        //paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

                                        paint.setColor(Color.BLACK);
                                        paint.setStrokeWidth(5);
                                        paint.setTextSize(40);

                                        int number = Math.abs(guia1-guia2);

                                        DecimalFormat df = new DecimalFormat("0.0");

                                        float medidaReal = (medidaReferenciaHeight* number)/pixelsReferenciaHeight[0];

                                        int posy = guia1+number/2;
                                        //canvas2.drawText("" + number, screenWidthPixels - 80, y, paint2);
                                        canvas.drawText("" + df.format(medidaReal) + " mm", 50, posy, paint);
                                    }

                                    //tvHeight.setText("Height: " + df.format(measureY));
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

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
