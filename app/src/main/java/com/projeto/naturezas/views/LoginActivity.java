package com.projeto.naturezas.views;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        MudarPontuacao l = new MudarPontuacao(LoginActivity.this);

        l.execute("5", "100");

    }
}
