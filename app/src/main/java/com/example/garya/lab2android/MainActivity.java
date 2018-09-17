package com.example.garya.lab2android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnElegir,R.id.btnComprimir, R.id.btnDescomprimir})
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
                    huffman = new Huffman(this.getApplication(), uri,true);
                    huffman.ComprimirArchivo();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnDescomprimir:
                /*try {
                    huffman = new Huffman(this.getApplication(), uri,true);
                    huffman.ComprimirArchivo(this.getApplication(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
                Toast.makeText(this.getApplicationContext(),"Descomprimir",Toast.LENGTH_LONG).show();
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
            textView.setText(texto[texto.length - 1]);
            Toast.makeText(this.getApplicationContext(),"Archivo cargado con éxito",Toast.LENGTH_LONG).show();
        }
    }
}
