package com.example.joseromero.flexometromovil;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PorFotoActivity extends Activity {

    private static final int CAMERA_REQUEST = 1888;
    private ImageView fotoTomada;
    Button photoButton;
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

        photoButton = (Button) this.findViewById(R.id.btnFoto);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(cameraIntent, CAMERA_REQUEST);

                /*Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(intent, 1);*/

                try {
                    //SimpleDateFormat sdfPic = new SimpleDateFormat(DATE_FORMAT);
                    //currentDateandTime = sdfPic.format(new Date()).replace(" ", "");
                    File imagesFolder = new File("FlexImg");
                    imagesFolder.mkdirs();
                    Random generator = new Random();
                    int n = 10000;
                   // n = generator.nextInt(n);
                    String fname = "img" + n + ".jpg";
                    File file = new File(imagesFolder, fname);
                    Uri outputFileUri = Uri.fromFile(file);
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /*super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case CAMERA_REQUEST :
                final int IMAGE_MAX_SIZE = 800;
                try {

                    File imagesFolder = new File("FlexImg");
                    imagesFolder.mkdirs();
                    Random generator = new Random();
                    int n = 10000;
                    //n = generator.nextInt(n);
                    String fname = "img" + n + ".jpg";
                    File file1 = new File(imagesFolder, fname);
                    Uri outputFileUri = Uri.fromFile(file1);

                    // Bitmap bitmap;
                    File file = null;
                    FileInputStream fis;
                    BitmapFactory.Options opts;
                    int resizeScale;
                    Bitmap bmp;
                    file = new File(outputFileUri.getPath());
                    // This bit determines only the width/height of the
                    // bitmap
                    // without loading the contents
                    opts = new BitmapFactory.Options();
                    opts.inJustDecodeBounds = true;
                    fis = new FileInputStream(file);
                    BitmapFactory.decodeStream(fis, null, opts);
                    fis.close();

                    // Find the correct scale value. It should be a power of
                    // 2
                    resizeScale = 1;

                    if (opts.outHeight > IMAGE_MAX_SIZE
                            || opts.outWidth > IMAGE_MAX_SIZE) {
                        resizeScale = (int) Math.pow(2, (int) Math.round(Math.log(IMAGE_MAX_SIZE/ (double) Math.max(opts.outHeight, opts.outWidth)) / Math.log(0.5)));
                    }

                    // Load pre-scaled bitmap
                    opts = new BitmapFactory.Options();
                    opts.inSampleSize = resizeScale;
                    fis = new FileInputStream(file);
                    bmp = BitmapFactory.decodeStream(fis, null, opts);
                    //Bitmap getBitmapSize = BitmapFactory.decodeResource(
                            //getResources(), R.drawable.male);
                    //image.setLayoutParams(new RelativeLayout.LayoutParams(
                    //        200,200));//(width,height);
                    fotoTomada.setImageBitmap(bmp);
                    //image.setRotation(90);
                    fis.close();

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                    //byte[] imageByte = baos.toByteArray();
                    break;
                } catch (FileNotFoundException e) {

                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }*/
        /*//if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            //Bitmap photo = (Bitmap) data.getExtras().get("data");
            //fotoTomada.setImageBitmap(photo);

            if (resultCode == RESULT_OK) {
                if (requestCode == 1) {

                    File f = new File(Environment.getExternalStorageDirectory().toString());

                    for (File temp : f.listFiles()) {
                        if (temp.getName().equals("temp.jpg")) {
                            f = temp;
                            File photo = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
                            break;
                        }
                    }

                    try {
                        Bitmap bitmap;
                        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                        bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(), bitmapOptions);
                        fotoTomada.setImageBitmap(bitmap);
                        String path = android.os.Environment.getExternalStorageDirectory() + File.separator + "Phoenix" + File.separator + "default";
                        f.delete();
                        OutputStream outFile = null;
                        File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

                        try {
                            outFile = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                            outFile.flush();
                            outFile.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        //}*/
    }
}
