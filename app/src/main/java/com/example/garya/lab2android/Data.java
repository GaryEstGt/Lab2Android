package com.example.garya.lab2android;

import java.util.LinkedList;
import java.util.List;

public class Data {
    private static Data instanciaUnica;

    private Data() {}

    private synchronized static void createInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Data();
        }
    }

    public static Data getInstance() {
        createInstance();

        return instanciaUnica;
    }
    public Double tamañoOriginal;
    public int contadorLectura=0;
    public List<MisCompresiones> listaCompresiones=new LinkedList<>();
}
