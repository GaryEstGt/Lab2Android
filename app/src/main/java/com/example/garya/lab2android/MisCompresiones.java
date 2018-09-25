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
        return "Compresion " +
                "nombreArchivo= '" + nombreArchivo + '\'' +
                " ruta= '" + ruta + '\'' +
                " razonCompresion= " + razonCompresion +
                " factorCompresion= " + factorCompresion +
                " porcentajeReduccion= " + porcentajeReduccion;
    }
    public String toStringArchivo(){
        return "{"+nombreArchivo+","+ruta+","+razonCompresion+","+factorCompresion+","+porcentajeReduccion+"}";
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

    public void setRazonCompresion(Double razonCompresion) {
        this.razonCompresion = razonCompresion;
    }

    public Double getFactorCompresion() {
        return factorCompresion;
    }

    public void setFactorCompresion(Double factorCompresion) {
        this.factorCompresion = factorCompresion;
    }

    public Double getPorcentajeReduccion() {
        return porcentajeReduccion;
    }

    public void setPorcentajeReduccion(Double porcentajeReduccion) {
        this.porcentajeReduccion = porcentajeReduccion;
    }


}
