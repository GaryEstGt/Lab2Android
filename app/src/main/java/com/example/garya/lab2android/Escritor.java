package com.example.garya.lab2android;

import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Escritor {

    public static boolean Escribir(String cadena, int ver,String nombre) {
        try {
        String nuevo = "";
        String raiz = Environment.getExternalStorageDirectory().toString();

        switch (ver){
            case 0:
                raiz += "/CompresionHuffman/";
                nuevo = raiz +nombre+ "COMP.huf";
                break;
            case 1:
                raiz += "/CompresionHuffman/";
                nuevo = raiz + nombre+"COMP.BIN";
                break;
            case 2:
                raiz += "/CompresionHuffman/";
                nuevo = raiz + nombre+"DESCOMPRESS.txt";
                break;
            case 3:
                raiz += "/CompresionLZW/";
                nuevo = raiz + nombre+"COMP.lzw";
                break;
            case 4:
                raiz += "/CompresionLZW/";
                nuevo = raiz +nombre+ "DESCOMPRESS.txt";
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
            print.write(cadena);
            print.close();
            if(ver==0 || ver==3){
                setMisCompresiones(nombre,raiz,Data.getInstance().tamañoOriginal, Double.valueOf(archivo.length()));
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }

    }
    public static void setMisCompresiones(String nombreArchivo,String ruta,Double tamañoOriginal,Double tamañoComprimido){
        MisCompresiones compresion =new MisCompresiones(nombreArchivo,ruta,0.0,0.0,0.0);
        compresion.setFactorCompresion(tamañoOriginal,tamañoComprimido);
        compresion.setRazonCompresion(tamañoOriginal,tamañoComprimido);
        compresion.setPorcentajeReduccion();
        Data.getInstance().listaCompresiones.add(compresion);
        Escribir(textoMisCompresiones(),5,"");
    }
    public static String textoMisCompresiones(){
        String texto="";
        for (MisCompresiones compresion:
                Data.getInstance().listaCompresiones) {
            texto+=compresion.toStringArchivo();
        }
        return texto;
    }

}
