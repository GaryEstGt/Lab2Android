package com.example.garya.lab2android;

import android.app.Application;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Huffman {

    char[] Cadena;
    List<Caracter> tabla = new LinkedList<>();
    List<Nodo> tablaArbol = new LinkedList<>();
    List<Nodo> tablaDirecciones = new LinkedList<>();
    //List<Nodo> padres = new LinkedList<>();

    public Huffman (Application application, Uri archivo)throws IOException{
        //Cadena = Lector.LeerArchivo(application, archivo).toCharArray();
    }

    void GenerarTabla(Application application, Uri archivo) throws IOException {
        List<Character> CaracteresBuscados = new LinkedList<>();

        Cadena = "El pan de mi casa es particular.".toCharArray();

        for (int i = 0; i < Cadena.length ; i++)
        {
            if (!CaracteresBuscados.contains(Cadena[i])) {
                Float probabilidad = ((float)contarCaracter(Cadena[i])/(float)Cadena.length);
                Caracter car = new Caracter(Cadena[i], probabilidad,"");
                CaracteresBuscados.add(car.getCaracter());
                tabla.add(car);
            }
            else{
                continue;
            }
        }

        GenerarArbol();
    }

    void GenerarArbol(){
        Collections.sort(tabla, new CompareByProbabilidad());

        for (Caracter car:tabla) {
            Nodo nodo = new Nodo(car);
            tablaArbol.add(nodo);
        }

        while(tablaArbol.size() > 1){
            encontrarMenores();
        }
    }

    void encontrarMenores(){
        JuntarNodos(tablaArbol.get(0),tablaArbol.get(1));
    }

    void JuntarNodos(Nodo n1, Nodo n2){
        float sumaProbabilidad = n1.getCaracter().getProbabilidad() + n2.getCaracter().getProbabilidad();
        Nodo padre = new Nodo(new Caracter('\u0000', sumaProbabilidad,""));
        padre.setHijoDerecho(n2);
        padre.setHijoIzquierdo(n1);

        n1.setPadre(padre);
        n2.setPadre(padre);

        tablaArbol.add(padre);
        tablaArbol.remove(n1);
        tablaArbol.remove(n2);

        Collections.sort(tablaArbol, new CompareByCaracter());
        //padres.add(padre);
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
    public List<Nodo> setDirecciones(String ubicacion,List<Nodo> tabla,Nodo raiz){
        if(raiz.caracter.caracter==('\u0000')){
            tabla=setDirecciones(ubicacion+"0",tabla,raiz.getHijoIzquierdo());
            tabla=setDirecciones(ubicacion+"1",tabla,raiz.getHijoDerecho());
        }
        else
            raiz.caracter.direccion=ubicacion;
            tablaDirecciones.add(raiz);
        return tabla;
    }
    public  void Escribir(){

    }

}
