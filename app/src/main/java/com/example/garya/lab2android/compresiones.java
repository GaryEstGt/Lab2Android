package com.example.garya.lab2android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class compresiones extends AppCompatActivity {

    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.list)
    ListView list;
    Uri uri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compresiones);
        ButterKnife.bind(this);

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
                Intent intentHuffman = new Intent(compresiones.this, MainActivity.class);
                startActivity(intentHuffman);

                return true;
            case R.id.menu_Compresiones:
                Toast.makeText(this.getApplicationContext(), "Ya esta en Mis Compresiones", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_LZW:
                Intent intentLZW = new Intent(compresiones.this, compresionlzw.class);
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

    public void verEnLista(List<MisCompresiones> listaCompresiones) {
        ArrayAdapter<MisCompresiones> adapter = new ArrayAdapter<MisCompresiones>(this, android.R.layout.simple_list_item_1, listaCompresiones);
        list.setAdapter(adapter);
    }
    public void llenarLista(Uri archivo1) throws IOException {
        String[] text = uri.getPath().split("/");
        String nombreArchivo=text[text.length-1];
        if(nombreArchivo.equals("MISCOMPRESIONES.txt")){
            Data.getInstance().listaCompresiones.clear();
            String direccion = Environment.getExternalStorageDirectory().toString() + "/MisCompresiones/";
            File archivo = new File(direccion);
            if (archivo.exists()) {
                direccion+="MISCOMPRESIONES.txt";
                String contenido = Lector.LeerArchivo(this.getApplication(),archivo1);
                String[] Compresiones = contenido.split("\\{");
                String[] datosCompresiones;
                for (int x = 0; x < Compresiones.length; x++) {
                    datosCompresiones = Compresiones[x].split(",");
                    MisCompresiones compresionTemporal = new MisCompresiones(datosCompresiones[0], datosCompresiones[1], Double.valueOf(datosCompresiones[2]), Double.valueOf(datosCompresiones[3]), Double.valueOf(datosCompresiones[4]));
                    Data.getInstance().listaCompresiones.add(compresionTemporal);
                }
                verEnLista(Data.getInstance().listaCompresiones);
                Toast.makeText(this.getApplicationContext(), "Archivo cargado con éxito", Toast.LENGTH_LONG).show();
                Toast.makeText(this.getApplicationContext(), "Compresiones Cargadas", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this.getApplicationContext(), "No se Han Hecho Compresiones", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this.getApplicationContext(), "Seleccione el archivo llamado MISCOMPRESIONES.txt ubicado en"+uri.getPath(), Toast.LENGTH_LONG).show();
        }

    }

    @OnClick(R.id.btn_Cargar)
    public void onViewClicked() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, "Choose File"), 0);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_CANCELED) {
                //Cancelado por el usuario
            }
            if ((resultCode == RESULT_OK) && (requestCode == 0)) {
                //Procesar el resultado

                uri = data.getData(); //obtener el uri content
                String[] text = uri.getPath().split("/");
                String contenido = Lector.LeerArchivo(this.getApplication(),uri);

                llenarLista(uri);
            }
        } catch (Exception e) {
            Toast.makeText(this.getApplicationContext(), "Error al cargar el archivo", Toast.LENGTH_LONG).show();
        }


    }
}

