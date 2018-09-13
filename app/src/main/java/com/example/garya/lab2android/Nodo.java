package com.example.garya.lab2android;

public class Nodo {

    Nodo Padre;
    Nodo HijoDerecho;
    Nodo HijoIzquierdo;

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

    public Nodo (){
        Padre = null;
        HijoDerecho = null;
        HijoIzquierdo = null;
    }

}
