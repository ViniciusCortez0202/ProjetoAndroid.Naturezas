package com.projeto.naturezas.services;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.naturezas.R;
import com.projeto.naturezas.models.Usuario;
import com.projeto.naturezas.views.LoginActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class Ranking extends AsyncTask<String, Object, String> {

    WeakReference<AppCompatActivity> reference;

    public Ranking(Context context){
        this.reference = new WeakReference<AppCompatActivity>((AppCompatActivity) context);
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
        Usuario usuario[] = new Usuario[30];
        try {
            JSONArray json = new JSONArray(s);

            if(json.length() > 0){
                for (int i = 0; i < json.length(); i++){
                    usuario[i].setNome(json.getJSONObject(i).getString("usu_nome"));
                    usuario[i].setPontuacao( json.getJSONObject(i).getInt("usu_pontuacao"));
                }
            }
            Intent intent = new Intent(this.reference.get(), Ranking.class);
            intent.putExtra(this.reference.get().getString(R.string.ranking_geral), usuario);
            this.reference.get().startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
