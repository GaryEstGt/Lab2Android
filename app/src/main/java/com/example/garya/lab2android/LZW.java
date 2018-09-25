package com.example.garya.lab2android;

import android.app.Application;
import android.net.Uri;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class LZW {
    List<String> tabla = new LinkedList<>();
    List<String> tablaInicial = new LinkedList<>();
    List<String> tablaNumeros = new LinkedList<>();
    String Cadena;
    String textoCodificado;
    String textoBinario;
    String textoDecodificado;
    String[] tablaAscii = new String[2];
    Uri datosArchivo;
    String nombreArchivo="";

    LZW (Application app, Uri archivo)throws IOException {
        //Cadena = Lector.LeerArchivo(app, archivo);
        Cadena = "wabbawabba";
        textoCodificado = "";
        textoDecodificado = "";
        textoBinario = "";
        datosArchivo=archivo;
    }
    public void obtenerNombreArchivo(){
        String[] path=datosArchivo.getPath().split("/");
        String[] nombre=path[path.length-1].split("\\.");
        nombreArchivo=nombre[0];
    }

    public boolean Comprimir(){
        try{
            GenerarTablaInicial(Cadena);
            LZWCompresion(0,Cadena);
            CompletarBinarios();
            BinarioAscii();
            GenerarBinarioCompleto();

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
                tablaNumeros.add(Integer.toBinaryString(tabla.indexOf(s)));
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
                    tablaNumeros.add(Integer.toBinaryString(tabla.indexOf(s)));

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

                    tablaNumeros.add(Integer.toBinaryString(tabla.indexOf(s)));

                    LZWCompresion(n+1, texto);
                }
            }
        }
    }

    private void CompletarBinarios(){
        String numeroMaximo = Integer.toBinaryString(tabla.size() - 1);

        for (int i = 0; i < tablaNumeros.size(); i++) {
            int cerosFaltantes = numeroMaximo.length() - tablaNumeros.get(i).length() % numeroMaximo.length();

            if(cerosFaltantes != 8){
                for (int j = 0; i < cerosFaltantes; j++) {
                    tablaNumeros.set(i,"0" + tablaNumeros.get(i));
                }
            }
        }
    }

    private void GenerarBinarioCompleto(){
        for (String numero:tablaNumeros) {
            textoBinario+=numero;
        }
    }


    private void BinarioAscii(){
        int contador = 0;
        String ascii = "";
        int numero = 0;

        int cerosFaltantes = 8 - textoBinario.length()%8;

        if(cerosFaltantes != 8){
            for (int i = 0; i < cerosFaltantes; i++) {
                textoBinario = "0" + textoBinario;
            }
        }

        for (int i = 0; i < textoBinario.length(); i++) {
            contador++;
            if (contador <= 8) {
                ascii = ascii + textoBinario.charAt(i);
                if (contador == 8){
                    numero = Integer.parseInt(ascii,2);
                    textoCodificado += (char)Integer.valueOf(numero).intValue();
                    contador = 0;
                    ascii = "";
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
    obtenerNombreArchivo();
        if(Escritor.Escribir(ArchivoLZW,3,nombreArchivo)){
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
        String anterior = "";
        String actual = "";

        for (int i = 0; i < cadenaAscii.length(); i++) {
            if(i == 0){
                String numeroBinario = Integer.toBinaryString(cadenaAscii.charAt(i));
                actual = tabla.get(Integer.parseInt(numeroBinario,2));

                textoDecodificado+=actual;
            }
            else{

                String numeroBinario = Integer.toBinaryString(cadenaAscii.charAt(i - 1));
                anterior = tabla.get(Integer.parseInt(numeroBinario,2));

                numeroBinario = Integer.toBinaryString(cadenaAscii.charAt(i));
                actual = tabla.get(Integer.parseInt(numeroBinario,2));

                textoDecodificado+=actual;
                tabla.add(anterior + actual.charAt(0));
            }
        }
    }

    private boolean GenerarArchivosDescompresion(){
        obtenerNombreArchivo();
        if(Escritor.Escribir(textoDecodificado,4,nombreArchivo)){
            return true;
        }
        else{
            return false;
        }
    }
}
