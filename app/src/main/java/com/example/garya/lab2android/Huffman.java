package com.example.garya.lab2android;

import android.net.Uri;

import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Huffman {

    char[] Cadena;
    List<Character> CaracteresBuscados = new LinkedList<>();
    List<Caracter> tabla = new LinkedList<>();
    List<Nodo> padres = new LinkedList<>();

    public void GenerarTabla(Uri archivo) throws IOException {
        Cadena = Lector.LeerArchivo(archivo).toCharArray();

        for (int i = 0; i < Cadena.length ; i++)
        {
            if (!CaracteresBuscados.contains(Cadena[i])) {
                Double probabilidad = (double)(contarCaracter(Cadena[i])/ Cadena.length);
                Caracter car = new Caracter(Cadena[i], probabilidad);
                tabla.add(car);
            }
            else{
                continue;
            }
        }
    }

    public void GenerarArbol(){
        Collections.sort(tabla, new CompareByProbabilidad());
    }

    public int contarCaracter (char x){
        int cantidad = 0;

        for (int i = 0; i < Cadena.length; i++) {
            if (x == Cadena[i]) {
                cantidad++;
            }
        }

        return cantidad;
    }
}
