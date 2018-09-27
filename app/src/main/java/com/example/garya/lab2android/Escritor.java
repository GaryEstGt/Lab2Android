package com.example.garya.lab2android;

import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public class Escritor {
    private static Charset UTF8 = Charset.forName("UTF-8");

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
            case 5:
                raiz += "/MisCompresiones/";
                nuevo = raiz+ "MISCOMPRESIONES.txt";
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

    public static boolean Escribir2(Uri selectedFile, Application app, String cadena, int version){
        try{
            ParcelFileDescriptor file = app.getContentResolver().openFileDescriptor(selectedFile, "w");
            FileOutputStream fileOutputStream = new FileOutputStream(file.getFileDescriptor());
            Writer writer = new OutputStreamWriter(fileOutputStream, UTF8);
            writer.write(cadena);
            writer.flush();
            writer.close();
            fileOutputStream.close();
            file.close();
            if(version==0 || version==3){
                String[] datos = selectedFile.getPath().split("/");
                String nombre = datos[datos.length - 1].split("\\.")[0];
                Long tamañoComprimido;
                Cursor returnCursor1=app.getContentResolver().query(selectedFile,null,null,null,null);
                int sizeIndex=returnCursor1.getColumnIndex(OpenableColumns.SIZE);
                returnCursor1.moveToFirst();
                tamañoComprimido=returnCursor1.getLong(sizeIndex);
                setMisCompresiones(nombre,selectedFile.getEncodedPath(),Data.getInstance().tamañoOriginal, Double.valueOf(tamañoComprimido));
            }
            return true;
        }catch(Exception e){
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
