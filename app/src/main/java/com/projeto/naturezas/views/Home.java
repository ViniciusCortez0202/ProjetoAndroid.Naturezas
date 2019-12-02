package com.projeto.naturezas.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.projeto.naturezas.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sp = this.getSharedPreferences(this.getString(R.string.app_name), Context.MODE_PRIVATE);
        String nome = sp.getString(this.getString(R.string.nome_usuario), "Nome indisponível");
        Toast.makeText(this, nome, Toast.LENGTH_LONG).show();
    }
}
