package com.example.garya.lab2android;

import android.app.Application;
import android.net.Uri;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LZW {
    List<String> tabla = new LinkedList<>();
    List<String> tablaInicial = new LinkedList<>();
    String Cadena;
    String textoCodificado;
    String textoDecodificado;
    String[] tablaAscii = new String[2];

    LZW (Application app, Uri archivo)throws IOException {
        Cadena = Lector.LeerArchivo(app, archivo);
        textoCodificado = "";
        textoDecodificado = "";
    }

    public boolean Comprimir(){
        try{
            GenerarTablaInicial(Cadena);
            LZWCompresion(0,Cadena);

            if(!GenerarArchivosCompresion()){
                return false;
            }

            return true;
        }catch(Exception e){
            return false;
        }
    }

    private void GenerarTablaInicial(String texto){
        for (int i = 0; i < texto.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(texto.charAt(i));

            if(tablaInicial.contains(stringBuilder.toString())){
                continue;
            }
            else{
                tablaInicial.add(stringBuilder.toString());
                tabla.add(stringBuilder.toString());
            }
        }
    }

    private void LZWCompresion (int n, String texto)
    {
        if(n < (texto.length())){
            StringBuilder stringBuilder = new StringBuilder();

            if(n == texto.length() - 1){
                stringBuilder.append(texto.charAt(n));
                String s = stringBuilder.toString();
                textoCodificado+=(char)tabla.indexOf(s);
            }
            else{
                stringBuilder.append(texto.charAt(n));
                String s = stringBuilder.toString();

                stringBuilder.delete(0,stringBuilder.length());

                stringBuilder.append(texto.charAt(n+1));
                String siguiente = stringBuilder.toString();

                stringBuilder.delete(0,stringBuilder.length());

                if (!tabla.contains(s + siguiente))
                {
                    tabla.add(s+siguiente);
                    textoCodificado+=(char)tabla.indexOf(s);

                    LZWCompresion(n+1, texto);
                }
                else
                {
                    while((tabla.contains(s+siguiente))&&(siguiente != ""))
                    {
                        s = s+siguiente;

                        if(n+2<texto.length()){
                            stringBuilder.append(texto.charAt(n+2));
                            siguiente = stringBuilder.toString();
                        }
                        else{
                            siguiente = "";
                        }

                        stringBuilder.delete(0,stringBuilder.length());

                        n++;
                    }

                    if(siguiente != ""){
                        tabla.add(s+siguiente);
                    }

                    textoCodificado+=(char)tabla.indexOf(s);

                    LZWCompresion(n+1, texto);
                }
            }
        }
    }

    private boolean GenerarArchivosCompresion(){
        String ArchivoLZW = "";
        for (int i = 0; i < tablaInicial.size(); i++) {
            ArchivoLZW += tablaInicial.get(i);

            if (i != tablaInicial.size() - 1) {
                ArchivoLZW += "°~";
            }
        }

        ArchivoLZW += "~&" + textoCodificado;

        if(Escritor.Escribir(ArchivoLZW,3)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean Descomprimir(){
        try{
            tablaAscii = Cadena.split("~&");
            LeerTablaInicial(tablaAscii[0]);
            DescompresionLZW(tablaAscii[1]);

            if(!GenerarArchivosDescompresion()){
                return false;
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private void LeerTablaInicial(String cadenaTabla){
        String[] caracteres = cadenaTabla.split("°~");

        for (int i = 0; i < caracteres.length; i++) {
            tablaInicial.add(caracteres[i]);
            tabla.add(caracteres[i]);
        }
    }

    private void DescompresionLZW(String cadenaAscii){
        StringBuilder stringBuilder = new StringBuilder();
        String anterior = "";
        String actual = "";

        for (int i = 0; i < cadenaAscii.length(); i++) {
            if(i == 0){
                stringBuilder.append(cadenaAscii.charAt(i));
                int indice = Integer.parseInt(stringBuilder.toString());
                actual = tabla.get(indice);
                stringBuilder.delete(0,stringBuilder.length());

                textoDecodificado+=actual;
            }
            else{
                stringBuilder.append(cadenaAscii.charAt(i - 1));
                int indice = Integer.parseInt(stringBuilder.toString());
                anterior = tabla.get(indice);
                stringBuilder.delete(0,stringBuilder.length());

                stringBuilder.append(cadenaAscii.charAt(i));
                indice = Integer.parseInt(stringBuilder.toString());
                actual = tabla.get(indice);
                stringBuilder.delete(0,stringBuilder.length());

                textoDecodificado+=actual;
                tabla.add(anterior + actual.charAt(0));
            }
        }
    }

    private boolean GenerarArchivosDescompresion(){
        if(Escritor.Escribir(textoDecodificado,4)){
            return true;
        }
        else{
            return false;
        }
    }
}
