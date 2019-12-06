package com.projeto.naturezas.views;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.projeto.naturezas.R;
import com.projeto.naturezas.services.*;
import com.projeto.naturezas.models.Usuario;
import com.projeto.naturezas.services.CriarConta;
import com.projeto.naturezas.services.Logar;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogar = findViewById(R.id.btn_login_logar);
        buttonLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Recuperar email
                EditText editTextEmail = findViewById(R.id.login_email);
                String email = editTextEmail.getText().toString();

                //Recuperar senha
                EditText editTextSenha = findViewById(R.id.login_senha);
                String senha = editTextSenha.getText().toString();

                logar(email, senha);

            }
        });

        FloatingActionButton buttonCriarConta = findViewById(R.id.login_fab);

        buttonCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CriarConta.class);
                startActivity(intent);
            }
        });

    }


    private void logar(String email, String senha){
        Logar login = new Logar(LoginActivity.this);
        login.execute(email, senha);
    }

}