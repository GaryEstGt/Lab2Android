package com.example.garya.lab2android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.textView)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnElegir)
    public void onViewClicked() {
        int VALOR_RETORNO = 1;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "Choose File"), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            //Cancelado por el usuario
        }
        if ((resultCode == RESULT_OK) && (requestCode == 0)) {
            //Procesar el resultado
            Uri uri = data.getData(); //obtener el uri content
            String[] texto = uri.getPath().split("/");
            textView.setText(texto[texto.length-1]);

            Huffman huf = null;
            try {
                huf = new Huffman(this.getApplication(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                huf.GenerarTabla(this.getApplication(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
