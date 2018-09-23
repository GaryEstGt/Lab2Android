package com.example.garya.lab2android;

import android.app.Application;
import android.net.Uri;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LZW {
    List<String> tabla = new LinkedList<>();
    String Cadena;
    String textoCodificado;

    public LZW (Application app, Uri archivo, boolean comprimir)throws IOException {
        Cadena = Lector.LeerArchivo(app, archivo);
        textoCodificado = "";

        if(comprimir){
            GenerarTablaInicial(Cadena);
          LZWCompresion(0,Cadena);
        }
        else{

        }
    }

    void GenerarTablaInicial(String texto){
        for (int i = 0; i < texto.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(texto.charAt(i));

            if(tabla.contains(stringBuilder.toString())){
                continue;
            }
            else{
                tabla.add(stringBuilder.toString());
            }
        }
    }

    void LZWCompresion (int n, String texto)
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
}
