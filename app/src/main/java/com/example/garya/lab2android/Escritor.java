package com.example.garya.lab2android;

import android.app.Application;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Escritor {
    public static void Escribir(Application application, String cadena, String directorio){
        String nuevo=directorio.substring(0,directorio.indexOf("."));
        nuevo=nuevo+".huff";
        try {
            FileWriter escritor=new FileWriter(nuevo);
            PrintWriter print=new PrintWriter(escritor);
            print.print(cadena);
            print.close();
            escritor.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
