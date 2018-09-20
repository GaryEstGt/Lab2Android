package com.example.garya.lab2android;

import android.app.Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Escritor {
    public static void Escribir(Application application, String cadena, String directorio,int ver){
        String nuevo;
        if(ver==0){
            nuevo=directorio.substring(0,directorio.indexOf("."));
            nuevo=nuevo+"COMP.huff";
        }
        else if(ver==1){
            nuevo=directorio.substring(0,directorio.indexOf("."));
            nuevo=nuevo+"COMP.BIN";
        }
        else{
            nuevo=directorio.substring(0,directorio.indexOf("."));
            nuevo=nuevo+"DESCOMPRESS.txt";
        }

        try {
            File chivo=new File(nuevo);
            FileWriter escritor=new FileWriter(nuevo);
            PrintWriter print=new PrintWriter(escritor);
            print.print(cadena);
            print.close();
            escritor.close();
            chivo.createNewFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
