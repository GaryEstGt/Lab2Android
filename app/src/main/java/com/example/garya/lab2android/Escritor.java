package com.example.garya.lab2android;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Escritor {
    public static void Escribir(String cadena, int ver) {
        String nuevo;
        String raiz = Environment.getExternalStorageDirectory().toString();

        if (ver == 0) {
            raiz += "/CompresionHuffman/";
            nuevo = raiz + "COMP.huf";
        } else if (ver == 1) {
            raiz += "/CompresionHuffman/";
            nuevo = raiz + "COMP.BIN";
        } else {
            raiz += "/CompresionHuffman/";
            nuevo = raiz + "DESCOMPRESS.txt";
        }

        try {

            File directorio = new File(raiz);

            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            File archivo = new File(nuevo);

            if (!archivo.exists()) {
                archivo.createNewFile();
            } else {
                archivo.delete();
                archivo.createNewFile();
            }

            PrintWriter print = new PrintWriter(archivo);
            print.print(cadena);
            print.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
