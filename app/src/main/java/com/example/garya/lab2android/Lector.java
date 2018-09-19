package com.example.garya.lab2android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Lector {

    /*public static String LeerArchivo(Application application, Uri archivo) throws IOException{
        InputStream IS = application.getContentResolver().openInputStream(archivo);
        BufferedReader BR = new BufferedReader(new InputStreamReader(IS));
        StringBuilder SB = new StringBuilder();
        String line = "";

        while((line = BR.readLine()) != null)
        {
            SB.append(line);
        }

        IS.close();
        BR.close();
        return SB.toString();
    }*/
   public static String LeerTexto(String direccion,String nombre){
       String textoLeido="";
       try{

           File file;
           file=new File(direccion,nombre);
           BufferedReader lector=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
           textoLeido=lector.readLine();
           lector.close();

       }catch (Exception e){

       }
       return textoLeido;


   }
}
