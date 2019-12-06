package com.projeto.naturezas.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.projeto.naturezas.R;
import com.projeto.naturezas.models.Usuario;
import com.projeto.naturezas.views.CadastrarActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class CriarConta extends AsyncTask<Usuario, Object, String> {

    WeakReference<CadastrarActivity> reference;

    public CriarConta(Context context){
        this.reference = new WeakReference<CadastrarActivity>((CadastrarActivity) context);
    }


    @Override
    protected String doInBackground(Usuario... usuarios) {

        Usuario usuario = usuarios[0];

        RequiresHttp require = new RequiresHttp();
        String res = require.criarContaUsuario(usuario.getNome(), usuario.getEmail(),
               usuario.getSenha(), usuario.getNumero(), usuario.getFoto());

        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject json = new JSONObject(s);

            if(json.has("mensagem")){
                Toast.makeText(this.reference.get(), json.getString("mensagem"), Toast.LENGTH_LONG).show();
            } else {
                String numeroConfirmacao = String.valueOf(json.getInt("numeroConfirmacao"));
                SharedPreferences sp = this.reference.get().getSharedPreferences
                        (this.reference.get().getString(R.string.numero_confirmacao), Context.MODE_PRIVATE);
                 SharedPreferences.Editor edtior = sp.edit();
                 edtior.putString("numeroConfirmacao", numeroConfirmacao);
                 edtior.commit();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
