package com.example.garya.lab2android;

public class MisCompresiones {
    String nombreArchivo;
    String ruta;
    Double razonCompresion;
    Double factorCompresion;
    Double porcentajeReduccion;

    public MisCompresiones(String nombreArchivo, String ruta, Double razonCompresion, Double factorCompresion, Double porcentajeReduccion) {
        this.nombreArchivo = nombreArchivo;
        this.ruta = ruta;
        this.razonCompresion = razonCompresion;
        this.factorCompresion = factorCompresion;
        this.porcentajeReduccion = porcentajeReduccion;
    }

    @Override
    public String toString() {
        return
                "Nombre Archivo= " + nombreArchivo + "\n" +
                " Ruta= " + ruta + "\n" +
                " Razon Compresion= " + razonCompresion +"\n"+
                " Factor Compresion= " + factorCompresion +"\n"+
                " Porcentaje Reduccion= " + porcentajeReduccion+"%";
    }
    public String toStringArchivo(){
        return nombreArchivo+","+ruta+","+razonCompresion+","+factorCompresion+","+porcentajeReduccion+"}";
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Double getRazonCompresion() {
        return razonCompresion;
    }

    public void setRazonCompresion(Double tamañoOriginal,Double tamañoComprimido) {
        this.razonCompresion = tamañoComprimido/tamañoOriginal;
    }

    public Double getFactorCompresion() {
        return factorCompresion;
    }

    public void setFactorCompresion(Double tamañoOriginal,Double tamañoComprimido) {
        this.factorCompresion = tamañoOriginal/tamañoComprimido;
    }

    public Double getPorcentajeReduccion() {
        return porcentajeReduccion;
    }

    public void setPorcentajeReduccion() {
        this.porcentajeReduccion = razonCompresion*100;
    }


}
