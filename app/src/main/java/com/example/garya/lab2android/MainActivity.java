package com.example.garya.lab2android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.btnComprimir)
    Button btnComprimir;
    @BindView(R.id.btnDescomprimir)
    Button btnDescomprimir;

    Huffman huffman;
    Lector leer;
    Escritor escribir;
    Uri uri;
    String direccion;
    @BindView(R.id.txtMostrar)
    TextView txtMostrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnElegir, R.id.btnComprimir, R.id.btnDescomprimir})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnElegir:
                int VALOR_RETORNO = 1;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(Intent.createChooser(intent, "Choose File"), 0);


                break;
            case R.id.btnComprimir:
               // try {
                   // huffman = new Huffman(this.getApplication(), uri, true);
                escribir=new Escritor();
                    escribir.Escribir(this.getApplication(),"Hol fui escrito o un intento de eso",direccion);

              //  } catch (IOException e) {
              //      e.printStackTrace();
              //  }
                break;
            case R.id.btnDescomprimir:
                /*try {
                    huffman = new Huffman(this.getApplication(), uri,true);
                    huffman.ComprimirArchivo(this.getApplication(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                Toast.makeText(this.getApplicationContext(), "Descomprimir", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            //Cancelado por el usuario
        }
        if ((resultCode == RESULT_OK) && (requestCode == 0)) {
            //Procesar el resultado

            uri = data.getData(); //obtener el uri content
            String[] texto = uri.getPath().split("/");
            direccion=uri.getPath();
            textView.setText(texto[texto.length - 1]);
          //  leer = new Lector();
           try {
                String contenido = LeoArchivo(uri);
                txtMostrar.setText(contenido);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this.getApplicationContext(), "Archivo cargado con éxito", Toast.LENGTH_LONG).show();
        }
    }
    String LeoArchivo(Uri archivo) throws IOException {
        InputStream IS = getContentResolver().openInputStream(archivo);
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
    }
}
