package com.example.garya.lab2android;

import java.util.Comparator;

public class Nodo {

    Nodo Padre;
    Nodo HijoDerecho;
    Nodo HijoIzquierdo;
    Caracter caracter;
    //float probabilidad;

    public Caracter getCaracter() {
        return caracter;
    }

    public void setCaracter(Caracter car) {
        caracter = car;
    }

    //public float getProbabilidad() {
        //return probabilidad;
    //}

    //public void setProbabilidad(float p) {
        //probabilidad = p;
    //}

    public Nodo getPadre() {
        return Padre;
    }

    public void setPadre(Nodo padre) {
        Padre = padre;
    }

    public Nodo getHijoDerecho() {
        return HijoDerecho;
    }

    public void setHijoDerecho(Nodo hijoDerecho) {
        HijoDerecho = hijoDerecho;
    }

    public Nodo getHijoIzquierdo() {
        return HijoIzquierdo;
    }

    public void setHijoIzquierdo(Nodo hijoIzquierdo) {
        HijoIzquierdo = hijoIzquierdo;
    }

    public Nodo (Caracter car/*, float p*/){
        Padre = null;
        HijoDerecho = null;
        HijoIzquierdo = null;
        caracter = car;
        //probabilidad = p;
    }

}

class CompareByCaracter implements Comparator<Nodo> {



    @Override

    public int compare(Nodo c1, Nodo c2) {

        if (c1.getCaracter().getProbabilidad() == c2.getCaracter().getProbabilidad()) {
            return 0;
        }
        else if (c1.getCaracter().getProbabilidad() < c2.getCaracter().getProbabilidad()) {
            return -1;
        }
        else{
            return 1;
        }

    }

}
