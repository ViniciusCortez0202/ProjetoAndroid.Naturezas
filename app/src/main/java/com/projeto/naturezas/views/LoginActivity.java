package com.projeto.naturezas.views;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.projeto.naturezas.R;
import com.projeto.naturezas.models.Usuario;
import com.projeto.naturezas.services.CriarConta;
import com.projeto.naturezas.services.InterceptSMS;
import com.projeto.naturezas.services.Logar;
import com.projeto.naturezas.services.MudarPontuacao;
import com.projeto.naturezas.services.Ranking;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CriarConta l = new CriarConta(LoginActivity.this);

        Usuario usuario = new Usuario("Rocha", "fa85fa@g.com", "82996587987", "222");

        /*if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, Manifest.permission.READ_SMS)) {
                ActivityCompat.requestPermissions(LoginActivity.this,
                        new String[]{Manifest.permission.READ_SMS},
                        123);
            }
        }*/

       l.execute(usuario);

       /*Intent in = new Intent(LoginActivity.this, Home.class);
        startActivity(in);*/

    }

   /* @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 123:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    InterceptSMS intercept = new InterceptSMS();
                } else {
                    // Usuário não aceitou a permissão
                }

                break;
        }
    }*/


}
