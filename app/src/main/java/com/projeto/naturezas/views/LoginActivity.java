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
import android.widget.Toast;

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
    }


    private void logar(String email, String senha){
        Logar login = new Logar(this);
        login.execute(email, senha);
    }

}