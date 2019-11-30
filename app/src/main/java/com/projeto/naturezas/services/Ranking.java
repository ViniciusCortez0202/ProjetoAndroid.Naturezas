package com.projeto.naturezas.services;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.projeto.naturezas.views.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class Ranking extends AsyncTask<String, Object, String> {

    WeakReference<LoginActivity> reference;

    public Ranking(Context context){
        this.reference = new WeakReference<LoginActivity>((LoginActivity) context);
    }


    @Override
    protected String doInBackground(String... strings) {
        RequiresHttp res = new RequiresHttp();

        String resposta = res.buscarRaking();

        return resposta;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONArray json = new JSONArray(s);

            if(json.length() > 0){
                for (int i = 0; i < json.length(); i++) {
                    String nome = json.getJSONObject(i).getString("usu_nome");
                    Toast.makeText(this.reference.get(), nome, Toast.LENGTH_LONG).show();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
