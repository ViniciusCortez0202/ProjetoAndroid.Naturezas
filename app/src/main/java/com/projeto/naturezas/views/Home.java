package com.projeto.naturezas.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.projeto.naturezas.R;

public class Home extends AppCompatActivity {

    private final int REQUEST_CODE_PERMISSION_SMS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.verificarPermissao();
        this.verificarLogin();
    }


    private void verificarLogin(){
        SharedPreferences sp = this.getSharedPreferences(this.getString(R.string.app_name), Context.MODE_PRIVATE);

        if(sp.getInt(this.getString(R.string.usuario_logado), 0) == 1){
            String nomeUsuario = sp.getString(this.getString(R.string.nome_usuario), "");
            Toast.makeText(this, "Bem Vindo(a) " + nomeUsuario, Toast.LENGTH_LONG).show();
        } else {
            //Ativar botão de login
        }

    }




    private void verificarPermissao(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        REQUEST_CODE_PERMISSION_SMS);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Obrigrado! Faça login para guardar sua pontuação", Toast.LENGTH_LONG);
                } else {
                    Toast.makeText(this, "Não será possível fazer login para guardar sua pontuação", Toast.LENGTH_LONG);
                }
                break;
        }
    }
}
