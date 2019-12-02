package com.projeto.naturezas.views;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.projeto.naturezas.R;
import com.projeto.naturezas.services.Logar;
import com.projeto.naturezas.services.MudarPontuacao;
import com.projeto.naturezas.services.Ranking;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Logar l = new Logar(LoginActivity.this);

        l.execute("cortezvinicius11@gmail.com", "1111");

        Intent in = new Intent(LoginActivity.this, Home.class);
        startActivity(in);

    }
}
