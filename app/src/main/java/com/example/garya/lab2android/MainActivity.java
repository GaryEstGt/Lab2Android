package com.example.garya.lab2android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
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

    Uri uri,uri2;
    Huffman huffman;
    @BindView(R.id.txtMostrar)
    TextView txtMostrar;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        txtMostrar.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_Huffman:
                Toast.makeText(this.getApplicationContext(), "Ya esta en Compresion Huffman", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_Compresiones:
                Intent intentCompresiones = new Intent(MainActivity.this, compresiones.class);
                startActivity(intentCompresiones);
                return true;
            case R.id.menu_LZW:
                Intent intentLZW = new Intent(MainActivity.this, compresionlzw.class);
                startActivity(intentLZW);
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

    @OnClick({R.id.btnElegir, R.id.btnComprimir, R.id.btnDescomprimir})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnElegir:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(Intent.createChooser(intent, "Choose File"), 0);
                break;
            case R.id.btnComprimir:
                if(uri==null){
                    Toast.makeText(this.getApplicationContext(), "Seleccione un archivo para comprimir", Toast.LENGTH_LONG).show();
                }
                else{
                    String[] prueb=uri.getPath().split("/");
                    String prueb2;
                    prueb2=prueb[prueb.length-1].substring(prueb[prueb.length-1].length()-4,prueb[prueb.length-1].length());
                    if(prueb2.equals(".txt")){
                        try {
                            Toast.makeText(this.getApplicationContext(), "Comprimiendo...", Toast.LENGTH_LONG).show();
                            huffman = new Huffman(this.getApplication(), uri);
                            if(huffman.ComprimirArchivo()){
                                Toast.makeText(this.getApplicationContext(), "Elija la ruta de compresión", Toast.LENGTH_SHORT).show();
                                ElegirRutaCompresion();
                                Toast.makeText(this.getApplicationContext(), "Elija la ruta del archivo binario", Toast.LENGTH_SHORT).show();
                                ElegirRutaBinario();
                            }
                            else{
                                Toast.makeText(this.getApplicationContext(), "Error al comprimir el archivo", Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException e) {
                            Toast.makeText(this.getApplicationContext(), "Error al comprimir el archivo", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(this.getApplicationContext(), "Seleccione archivo de texto para comprimir", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.btnDescomprimir:
                if(uri==null){
                    Toast.makeText(this.getApplicationContext(), "Seleccione un archivo para descomprimir", Toast.LENGTH_LONG).show();
                }else{
                    String[] prueb=uri.getPath().split("/");
                    String prueb2;
                    prueb2=prueb[prueb.length-1].substring(prueb[prueb.length-1].length()-4,prueb[prueb.length-1].length());
                    if(prueb2.equals(".huf")){
                        Toast.makeText(this.getApplicationContext(), "El Archivo esta siendo descomprimido", Toast.LENGTH_LONG).show();
                        try {
                            huffman = new Huffman(this.getApplication(), uri);
                            if(huffman.Descomprimir()){
                                Toast.makeText(this.getApplicationContext(), "Elija la ruta de descompresión", Toast.LENGTH_SHORT).show();
                                ElegrirRutaDescompresion();
                            }
                            else{
                                Toast.makeText(this.getApplicationContext(), "Error al descomprimir el archivo", Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException e) {
                            Toast.makeText(this.getApplicationContext(), "Error al descomprimir el archivo", Toast.LENGTH_LONG).show();
                        }
                    }
                    else{
                        Toast.makeText(this.getApplicationContext(), "Seleccion un archivo .huf para descomprimir", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                try{
                    super.onActivityResult(requestCode, resultCode, data);
                    if (resultCode == RESULT_CANCELED) {
                        //Cancelado por el usuario
                    }if ((resultCode == RESULT_OK) && (requestCode == 0)) {
                        //Procesar el resultado

                        uri = data.getData();//obtener el uri content
                        String[] texto = uri.getPath().split("/");
                        textView.setText(texto[texto.length - 1]);
                        String contenido = Lector.LeerArchivo(this.getApplication(),uri);
                        txtMostrar.setText(contenido);
                        Toast.makeText(this.getApplicationContext(), "Archivo cargado con éxito", Toast.LENGTH_LONG).show();
                    }
                }catch(Exception e){
                    Toast.makeText(this.getApplicationContext(), "Error al cargar el archivo", Toast.LENGTH_LONG).show();
                }
                break;
            case 1:
                uri2 = data.getData();
                if(huffman.GenerarArchivosCompresion(uri2)){
                    Toast.makeText(this.getApplicationContext(), "Archivo comprimido en " + uri2.getPath(), Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                uri2 = data.getData();
                huffman.GenerarArchivoBinario(uri2);
                break;
            case 3:
                uri2 = data.getData();
                if(huffman.GenerarArchivosDescompresion(uri2)){
                    Toast.makeText(this.getApplicationContext(), "Archivo descomprimido en " + uri2.getPath(), Toast.LENGTH_LONG).show();
                }

                break;
        }
    }

    public void ElegirRutaCompresion(){
        Intent intent2 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent2.addCategory(Intent.CATEGORY_OPENABLE);
        intent2.setType("*/*");
        intent2.putExtra(Intent.EXTRA_TITLE, textView.getText().toString().split("\\.")[0] + ".huf");
        startActivityForResult(intent2, 1);
    }

    public void ElegirRutaBinario(){
        Intent intent2 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent2.addCategory(Intent.CATEGORY_OPENABLE);
        intent2.setType("*/*");
        intent2.putExtra(Intent.EXTRA_TITLE, textView.getText().toString().split("\\.")[0] + ".BIN");
        startActivityForResult(intent2, 2);
    }

    public void ElegrirRutaDescompresion(){
        Intent intent2 = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent2.addCategory(Intent.CATEGORY_OPENABLE);
        intent2.setType("*/*");
        intent2.putExtra(Intent.EXTRA_TITLE, textView.getText().toString().split("\\.")[0] + "Descomprimido.txt");
        startActivityForResult(intent2, 3);
    }
}
