package com.projeto.naturezas.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.projeto.naturezas.R;
import com.projeto.naturezas.models.Usuario;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();

        if(intent.hasExtra(this.getString(R.string.ranking_geral))){
            Usuario usuario[] = (Usuario[]) intent.getSerializableExtra(this.getString(R.string.ranking_geral));
            //Coloca os dados de usuario[] dentro do adapter da listview
        }
    }
}
