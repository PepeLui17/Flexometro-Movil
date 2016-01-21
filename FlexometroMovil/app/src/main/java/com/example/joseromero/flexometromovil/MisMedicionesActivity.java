package com.example.joseromero.flexometromovil;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public class MisMedicionesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_mediciones);

        ArrayList<String> mediciones = new ArrayList<String>();

        try {
            FileInputStream fileIn=openFileInput("mediciones.txt");

            if( fileIn != null ) {
                InputStreamReader InputRead= new InputStreamReader(fileIn);
                BufferedReader bufferedReader = new BufferedReader(InputRead);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null )
                {
                    int i = 1;
                    String medicionText = "";

                    StringTokenizer st2 = new StringTokenizer(receiveString, ",");

                    while (st2.hasMoreElements()) {
                        if (i == 1) {
                            medicionText = medicionText + "Nombre: " + st2.nextElement() + ",";
                        }
                        if (i == 2) {
                            medicionText = medicionText + "  Ancho: " + st2.nextElement() + ",";
                        }
                        if (i == 3) {
                            medicionText = medicionText + "  Alto: " + st2.nextElement();
                        }
                        i++;
                    }

                    mediciones.add(medicionText);
                }

                fileIn.close();
                //ret = stringBuilder.toString();
            }


        }
        catch (Exception e) {
            e.printStackTrace();
        }


        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, mediciones);

        ListView listView = (ListView) findViewById(R.id.listMediciones);
        listView.setAdapter(adapter);
    }
}
