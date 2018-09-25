package com.example.garya.lab2android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class compresiones extends AppCompatActivity {

    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.list)
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compresiones);
        ButterKnife.bind(this);
        verEnLista(Data.getInstance().listaCompresiones);
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
}
