package com.projeto.naturezas.views;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.projeto.naturezas.R;
import com.projeto.naturezas.services.Logar;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Logar l = new Logar(LoginActivity.this);

        l.execute("cortezvinicius11@gmail.com", "1111");

    }
}
