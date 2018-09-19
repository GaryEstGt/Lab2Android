package com.example.garya.lab2android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

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
    String direccion2;
    @BindView(R.id.txtMostrar)
    TextView txtMostrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        txtMostrar.setMovementMethod(new ScrollingMovementMethod());
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
               try {
                   Toast.makeText(this.getApplicationContext(), "Comprimiendo...", Toast.LENGTH_LONG).show();
                    huffman = new Huffman(this.getApplication(), uri, true,direccion);
                   Toast.makeText(this.getApplicationContext(), "Archivo comprimido en "+direccion, Toast.LENGTH_LONG).show();


              } catch (IOException e) {
                 e.printStackTrace();
               }
                break;
            case R.id.btnDescomprimir:
                Toast.makeText(this.getApplicationContext(), "El Archivo esta siendo descomprimido", Toast.LENGTH_LONG).show();
                try {
                    huffman = new Huffman(this.getApplication(), uri,false,direccion);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(this.getApplicationContext(), "Descomprimido en "+direccion, Toast.LENGTH_LONG).show();
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
           leer = new Lector();
           try {
                String contenido = Lector.LeerArchivo(this.getApplication(),uri);
                txtMostrar.setText(contenido);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this.getApplicationContext(), "Archivo cargado con éxito", Toast.LENGTH_LONG).show();
        }
    }
   /* String LeoArchivo(Uri archivo) throws IOException {
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
    }*/
}
