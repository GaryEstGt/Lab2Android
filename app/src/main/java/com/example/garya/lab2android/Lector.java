package com.example.garya.lab2android;

import android.app.Application;
import android.net.Uri;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Lector {

    public static String LeerArchivo(Application application, Uri archivo) throws IOException {
        File datoArchivo=new File(archivo.getPath());
        long valor=datoArchivo.length();
        Data.getInstance().tamañoOriginal= Double.valueOf(valor);
        InputStream IS = application.getContentResolver().openInputStream(archivo);
        BufferedReader BR = new BufferedReader(new InputStreamReader(IS));
        StringBuilder SB = new StringBuilder();
        String line = "";
        while((line = BR.readLine()) != null)
        {
            for (int i = 0; i < line.length(); i++) {
                SB.append(line.charAt(i));
            }
        }

        IS.close();
        BR.close();
        return SB.toString();
    }
}
