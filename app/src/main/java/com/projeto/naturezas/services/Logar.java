package com.projeto.naturezas.services;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.lang.ref.WeakReference;
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
        s = s.substring(1, s.length()-2);
        try {
            JSONObject json = new JSONObject(s);

            if(json.has("mensagem")){
                String res = json.getString("mensagem");
                Toast.makeText(reference.get(), res, Toast.LENGTH_LONG).show();
            } else {
                //Intent intent = new Intent(this.reference.get().getClass();
                String nome = json.getString("usuario");
                String email = json.getString("email");
                int id = json.getInt("id");
                Toast.makeText(reference.get(), nome + ", " + email + ", " + id, Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
