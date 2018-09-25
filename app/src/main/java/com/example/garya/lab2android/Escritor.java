package com.example.garya.lab2android;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Escritor {

    public static boolean Escribir(String cadena, int ver) {
        try {
        String nuevo = "";
        String raiz = Environment.getExternalStorageDirectory().toString();

        switch (ver){
            case 0:
                raiz += "/CompresionHuffman/";
                nuevo = raiz + "COMP.huf";
                break;
            case 1:
                raiz += "/CompresionHuffman/";
                nuevo = raiz + "COMP.BIN";
                break;
            case 2:
                raiz += "/CompresionHuffman/";
                nuevo = raiz + "DESCOMPRESS.txt";
                break;
            case 3:
                raiz += "/CompresionLZW/";
                nuevo = raiz + "COMP.lzw";
                break;
            case 4:
                raiz += "/CompresionLZW/";
                nuevo = raiz + "DESCOMPRESS.txt";
                break;
                default:
                    return false;
        }

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
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

    }

}
