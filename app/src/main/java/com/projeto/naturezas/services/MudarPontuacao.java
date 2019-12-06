package com.projeto.naturezas.services;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.projeto.naturezas.views.JogoActivity;
import com.projeto.naturezas.views.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class MudarPontuacao extends AsyncTask<String, Object, String> {

    private WeakReference<JogoActivity> reference;

    public MudarPontuacao(Context context) {

        this.reference = new WeakReference<JogoActivity>(
                (JogoActivity) context);
    }

    @Override
    protected String doInBackground(String... strings) {
        String id = strings[0];
        String pontuacao = strings[1];

        RequiresHttp client = new RequiresHttp();
        String response = client.atualizarPontuacao(id, pontuacao);

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject json = new JSONObject(s);

            if(json.has("mensagem")){
                String res = json.getString("mensagem");
                Toast.makeText(reference.get(), res, Toast.LENGTH_LONG).show();
            } else {
                String res = json.getString("confimacao");
                Toast.makeText(reference.get(), res, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
