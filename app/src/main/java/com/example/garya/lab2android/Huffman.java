package com.example.garya.lab2android;

import android.app.Application;
import android.net.Uri;

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

    public void GenerarTabla(Application application, Uri archivo) throws IOException {
        //Cadena = Lector.LeerArchivo(application, archivo).toCharArray();

        Cadena = "El pan de mi casa es particular ala gran diabla.".toCharArray();

        for (int i = 0; i < Cadena.length ; i++)
        {
            if (!CaracteresBuscados.contains(Cadena[i])) {
                Float probabilidad = ((float)contarCaracter(Cadena[i])/(float)Cadena.length);
                Caracter car = new Caracter(Cadena[i], probabilidad);
                CaracteresBuscados.add(car.getCaracter());
                tabla.add(car);
            }
            else{
                continue;
            }
        }

        GenerarArbol();
    }

    public void GenerarArbol(){
        Collections.sort(tabla, new CompareByProbabilidad());
    }

    public int contarCaracter (char x){
        int cantidad = 0;

        for (int i = 0; i < (float)Cadena.length; i++) {
            if (x == Cadena[i]) {
                cantidad++;
            }
        }

        return cantidad;
    }
}
