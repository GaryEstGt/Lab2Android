package com.example.garya.lab2android;

import java.util.Comparator;

public class Caracter {

    char caracter;
    double probabilidad;

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }

    public double getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(double probabilidad) {
        this.probabilidad = probabilidad;
    }


    public Caracter(char Caracter, double Probabilidad){
        caracter = Caracter;
        probabilidad = Probabilidad;
    }


}

class CompareByProbabilidad implements Comparator<Caracter> {



    @Override

    public int compare(Caracter c1, Caracter c2) {

        if (c1.getProbabilidad() == c2.getProbabilidad()) {
            return 0;
        }
        else if (c1.getProbabilidad() < c2.getProbabilidad()) {
            return -1;
        }
        else{
            return 1;
        }

    }

}
