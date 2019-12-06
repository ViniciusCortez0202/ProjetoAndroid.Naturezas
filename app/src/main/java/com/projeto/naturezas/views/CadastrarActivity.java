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

import com.projeto.naturezas.R;
import com.projeto.naturezas.services.*;
import com.projeto.naturezas.models.Usuario;
import com.projeto.naturezas.services.CriarConta;

public class CadastrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button buttonCadastro = findViewById(R.id.cadastro_botao);
        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recupera os dados em editTextNome
                EditText editTextNome = findViewById(R.id.cadastro_nome);
                String nome = editTextNome.getText().toString();

                //Recupera os dados em e-mail
                EditText editTextEmail = findViewById(R.id.cadastro_email);
                String email = editTextEmail.getText().toString();

                //Recupera os dados em numero
                EditText editTextNumero = findViewById(R.id.cadastro_numero);
                String numero = editTextNumero.getText().toString();

                //Recupera os dados em senha
                EditText editTextSenha = findViewById(R.id.cadastro_senha);
                String senha = editTextNumero.getText().toString();

                //Recuper os dados da foto
                String foto = "";

                Usuario usuario = new Usuario(nome, email, numero, senha, foto);

                cadastrarUsuario(usuario);

            }

        });

    }

    public void cadastrarUsuario(Usuario usuario) {
        CriarConta criarconta = new CriarConta(CadastrarActivity.this);
        criarconta.execute(usuario);
    }
}
