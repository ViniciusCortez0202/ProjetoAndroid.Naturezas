package com.projeto.naturezas.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.projeto.naturezas.R;
import com.projeto.naturezas.models.Usuario;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {

    private AdapterRanking adapterRanking;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Intent intent = this.getIntent();

        if(intent.hasExtra(this.getString(R.string.ranking_geral))){
           List<Usuario> usuarios = (ArrayList<Usuario>) intent.getSerializableExtra(this.getString(R.string.ranking_geral));
            //Coloca os dados de usuario[] dentro do adapter da listview

            adapterRanking = new AdapterRanking(usuarios, this);

            ListView listView = findViewById(R.id.listview_mainativity);
            listView.setAdapter(adapterRanking);
        } else {
            Toast.makeText(this, "Não foi possível acessar o ranking", Toast.LENGTH_LONG);
        }
    }
}
