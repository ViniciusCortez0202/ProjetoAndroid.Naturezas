package com.projeto.naturezas.services;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.Toast;

import com.projeto.naturezas.models.Usuario;
import com.projeto.naturezas.views.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class CriarConta extends AsyncTask<Usuario, Object, String> {

    WeakReference<LoginActivity> reference;

    public CriarConta(Context context){
        this.reference = new WeakReference<LoginActivity>((LoginActivity) context);
    }


    @Override
    protected String doInBackground(Usuario... usuarios) {

        Usuario usuario = usuarios[0];

        RequiresHttp require = new RequiresHttp();
        String res = require.criarContaUsuario(usuario.getNome(), usuario.getEmail(),
               usuario.getSenha(), usuario.getNumero());
        Log.i("Valor", res);
        return res;
    }

    /*@Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject json = new JSONObject(s);

            if(json.has("mensagem")){
                Toast.makeText(this.reference.get(), json.getString("mensagem"), Toast.LENGTH_LONG).show();
            } else {
                String numeroConfirmacao = String.valueOf(json.getInt("numeroConfirmacao"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }*/
}
