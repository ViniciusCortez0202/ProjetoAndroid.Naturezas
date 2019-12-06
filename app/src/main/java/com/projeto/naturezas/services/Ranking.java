package com.projeto.naturezas.services;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.naturezas.R;
import com.projeto.naturezas.models.Usuario;
import com.projeto.naturezas.views.Home;
import com.projeto.naturezas.views.LoginActivity;
import com.projeto.naturezas.views.RankingActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class Ranking extends AsyncTask<String, Object, String> {

    WeakReference<Home> reference;

    public Ranking(Context context){
        this.reference = new WeakReference<Home>((Home) context);
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
        List<Usuario> usuario = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(s);

            if(json.length() > 0){
                for (int i = 0; i < json.length(); i++){
                    Usuario u = new Usuario();

                    u.setFoto(json.getJSONObject(i).getString("foto"));
                    u.setNome(json.getJSONObject(i).getString("nome"));
                    u.setPontuacao( json.getJSONObject(i).getInt("pontuacao"));
                    usuario.add(u);
                }
            }

            Intent intent = new Intent(this.reference.get(), RankingActivity.class);
            intent.putExtra(this.reference.get().getString(R.string.ranking_geral),(Serializable) usuario);
            this.reference.get().startActivity(intent);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
