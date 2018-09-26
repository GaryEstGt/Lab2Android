package com.example.garya.lab2android;

import android.app.Application;
import android.net.Uri;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
        int line = 0;
        while((line = BR.read()) != -1)
        {
            char val = (char)line;
            SB.append(val);
        }

        IS.close();
        BR.close();
        return SB.toString();
    }
    public static String LeerMisCompresiones(){
        String contenido="";
        try{
            File file;
            file=new File(Environment.getExternalStorageDirectory().toString()+"/MisCompresiones/MISCOMPRESIONES.txt");
            BufferedReader buffer=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            contenido=buffer.readLine();
            buffer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return contenido;
    }
}
