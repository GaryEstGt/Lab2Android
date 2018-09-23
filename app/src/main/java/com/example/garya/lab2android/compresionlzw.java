package com.example.garya.lab2android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class compresionlzw extends AppCompatActivity {

    @BindView(R.id.txt_archivo)
    TextView txtArchivo;
    @BindView(R.id.txt_Mostrar)
    TextView txtMostrar;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compresionlzw);
        ButterKnife.bind(this);
        txtMostrar.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_Huffman:
                Intent intentHuffman = new Intent(compresionlzw.this, MainActivity.class);
                startActivity(intentHuffman);

                return true;
            case R.id.menu_Compresiones:
                Intent intentLZW = new Intent(compresionlzw.this, compresiones.class);
                startActivity(intentLZW);

                return true;
            case R.id.menu_LZW:
                Toast.makeText(this.getApplicationContext(), "Ya esta en Compresion LZW", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Salir:
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick({R.id.btn_Abrir, R.id.btn_Comprimir, R.id.btn_Descomprimir})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_Abrir:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(Intent.createChooser(intent, "Choose File"), 0);
                break;
            case R.id.btn_Comprimir:
                break;
            case R.id.btn_Descomprimir:
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            //Cancelado por el usuario
        }
        if ((resultCode == RESULT_OK) && (requestCode == 0)) {
            //Procesar el resultado

            uri = data.getData(); //obtener el uri content
            String[] texto = uri.getPath().split("/");
            txtArchivo.setText(texto[texto.length - 1]);
            try {
                String contenido = Lector.LeerArchivo(this.getApplication(), uri);
                txtMostrar.setText(contenido);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(this.getApplicationContext(), "Archivo cargado con éxito", Toast.LENGTH_LONG).show();
        }
    }
}
