package com.projeto.naturezas.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.projeto.naturezas.BuildConfig;
import com.projeto.naturezas.R;
import com.projeto.naturezas.services.*;
import com.projeto.naturezas.models.Usuario;
import com.projeto.naturezas.services.CriarConta;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class CadastrarActivity extends AppCompatActivity {

    private String caminhoFoto;
    private String stringBase64;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button btnfoto = findViewById(R.id.cadastro_botao_imagem);
        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File file = new File(caminhoFoto);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(
                                CadastrarActivity.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                file
                        ));

                startActivityForResult(intent, 123);

            }
        });


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


                Usuario usuario = new Usuario(nome, email, numero, senha,  stringBase64);

                cadastrarUsuario(usuario);

            }

        });

    }

    public void cadastrarUsuario(Usuario usuario) {
        CriarConta criarconta = new CriarConta(CadastrarActivity.this);
        criarconta.execute(usuario);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setImage(caminhoFoto);
    }

    private void setImage(String foto) {
        ImageView imageView = findViewById(R.id.cadastro_imagem);
        Bitmap bitmap = BitmapFactory.decodeFile(foto);
        imageView.setImageBitmap(bitmap);
        imageView.setTag(foto);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        this.stringBase64 = byteArray.toString();
    }

}
