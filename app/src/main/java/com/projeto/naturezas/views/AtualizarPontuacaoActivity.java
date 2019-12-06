package com.projeto.naturezas.views;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.naturezas.R;
import com.projeto.naturezas.services.Logar;
import com.projeto.naturezas.services.MudarPontuacao;

public class AtualizarPontuacaoActivity extends AppCompatActivity {




    public void mudarPontuacao(String pontuacao){

        SharedPreferences sp = this.getSharedPreferences(this.getString(R.string.app_name), Context.MODE_PRIVATE);
        int id = sp.getInt(this.getString(R.string.id_usuario), 0);
        MudarPontuacao mp = new MudarPontuacao(AtualizarPontuacaoActivity.this);
        mp.execute(pontuacao);
        
    }

}
