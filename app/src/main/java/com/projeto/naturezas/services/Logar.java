package com.projeto.naturezas.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.lang.ref.WeakReference;
import java.nio.channels.InterruptedByTimeoutException;

import com.projeto.naturezas.R;
import com.projeto.naturezas.views.Home;
import com.projeto.naturezas.views.LoginActivity;

public class Logar extends AsyncTask<String, Object, String> {
    private WeakReference<LoginActivity> reference;

    public Logar(Context context) {

        this.reference = new WeakReference<LoginActivity>(
                (LoginActivity) context);
    }

    @Override
    protected String doInBackground(String... listaCep) {
        String email = listaCep[0];
        String senha = listaCep[1];

        RequiresHttp client = new RequiresHttp();
        String response = client.fazerLogin(email, senha);

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("valor", s);
        s = s.substring(1, s.length()-2);
        try {
            JSONObject json = new JSONObject(s);
            Log.i("valor", s);
            if(json.has("mensagem")){
                String res = json.getString("mensagem");
                Toast.makeText(reference.get(), res, Toast.LENGTH_LONG).show();
            } else {
                String nome = json.getString("usuario");
                String email = json.getString("email");
                int id = json.getInt("id");

                SharedPreferences sp = this.reference.get().getSharedPreferences
                        (this.reference.get().getString(R.string.app_name), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(this.reference.get().getString(R.string.id_usuario), id);
                editor.putString(this.reference.get().getString(R.string.nome_usuario), nome);
                editor.putString(this.reference.get().getString(R.string.email_usuario), email);
                editor.putInt(this.reference.get().getString(R.string.usuario_logado), 1);
                editor.commit();
                Intent intent = new Intent(this.reference.get(), Home.class);
                this.reference.get().startActivity(intent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
