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
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.projeto.naturezas.R;
import com.projeto.naturezas.services.Ranking;

import java.util.zip.Inflater;

public class Home extends AppCompatActivity {

    private final int REQUEST_CODE_PERMISSION_SMS = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.verificarPermissao();
        this.verificarLogin();

        Button buttonHomeLogin = findViewById(R.id.btn_home_login);
        Button buttonHomePartida = findViewById(R.id.btn_home_init);

        buttonHomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        buttonHomePartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, JogoActivity.class);
                startActivity(intent);
            }
        });

    }

    private void verificarLogin(){//vefirica se o usuario ja está logado
        SharedPreferences sp = this.getSharedPreferences(this.getString(R.string.app_name), Context.MODE_PRIVATE);

        if(sp.getInt(this.getString(R.string.usuario_logado), 0) == 1){
            String nomeUsuario = sp.getString(this.getString(R.string.nome_usuario), "");
            Toast.makeText(this, "Bem Vindo(a) " + nomeUsuario, Toast.LENGTH_LONG).show();
        } else {
            //Ativar botão de login
            Button buttonLogin = findViewById(R.id.btn_home_login);
            buttonLogin.setVisibility(View.VISIBLE);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toobar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id_item = item.getItemId();

        if(id_item == R.id.menu_ranking){
            Ranking ranking = new Ranking(this);

            ranking.execute();
        } else if (id_item == R.id.menu_deslogar) {
            SharedPreferences sp = this.getSharedPreferences(this.getString(R.string.app_name), Context.MODE_PRIVATE);
            sp.edit().clear().commit();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
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
