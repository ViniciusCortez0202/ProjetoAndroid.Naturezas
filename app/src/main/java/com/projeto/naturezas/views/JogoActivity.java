package com.projeto.naturezas.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.naturezas.R;
import com.projeto.naturezas.services.MudarPontuacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class JogoActivity extends AppCompatActivity {
    private TextView contarQuestao;
    private TextView enunciadoQuestao;
    private ImageView imagemQuestao;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private String respostaCerta;
    private int contadorRespostaCerta = 0;
    private int contador = 1;

    ArrayList<ArrayList<String>> questoes = new ArrayList<>();

    String respostasDados[][] =
            {
                    {"questao1", "Qual a Geometria Molecular da molécula acima?", "Angular", "Tetraédrica", "Linear", "Trigonal"},
                    {"questao2", "Qual fenômeno físico é o descrito acima?", "Reflexão", "Refração", "Ressonância", "Interferência"},
                    {"questao3", "Qual a molécula acima?", "DNA", "Proteína", "Aminoácido", "RNA"},
                    {"questao4", "Que efeito físico está associado na imagem?", "Efeito Joule", "Efeito Doppler", "Efeito Estufa", "Aquecimento Global"},
                    {"questao5", "Qual elemento químico acima?", "Benzeno", "Metano", "Etano", "Propano"},
                    {"questao6", "Que conceito biológico está associado na imagem acima?", "Evolução", "Reprodução", "Mitose", "Meiose"},
                    {"questao7", "Que conceito físico está associado na imagem acima?", "Torque", "Quantidade de movimento", "Impulso", "Energia"},
                    {"questao8", "Do que se trata a imagem?", "Laboratório de Química", "Laboratório de Física", "Laboratório de Biologia", "Laboratório de Geologia"},
                    {"questao9", "A imagem se refere ao que?", "Cladograma", "Fluxograma", "Calendário", "Organograma"},
                    {"questao10", "Que ciência é essa?", "Física", "Química", "Biologia", "Matemática"}


            };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telajogo);

        contarQuestao = findViewById(R.id.contarQuestao);
        imagemQuestao = findViewById(R.id.idImagem);
        enunciadoQuestao = findViewById(R.id.pergunta);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);

        for(int i = 0; i<respostasDados.length; i++)
        {
            ArrayList<String> dados = new ArrayList<>();
            dados.add(respostasDados[i][0]);
            dados.add(respostasDados[i][1]);
            dados.add(respostasDados[i][2]);
            dados.add(respostasDados[i][3]);
            dados.add(respostasDados[i][4]);
            dados.add(respostasDados[i][5]);

            questoes.add(dados);
        }

        mostrarProximaQuestao();
    }

    public void mudarPontuacao(String pontuacao){

        SharedPreferences sp = this.getSharedPreferences(this.getString(R.string.app_name), Context.MODE_PRIVATE);
        int id = sp.getInt(this.getString(R.string.id_usuario), 0);
        MudarPontuacao mp = new MudarPontuacao(JogoActivity.this);
        if(id > 0) {
            mp.execute(id + "", pontuacao);
        }
    }

    public void mostrarProximaQuestao()
    {
        contarQuestao.setText("Questão " + contador);

        Random random = new Random();
        int randomNumero = random.nextInt(questoes.size());

        ArrayList<String> quiz = questoes.get(randomNumero);

        imagemQuestao.setImageResource(getResources().getIdentifier(quiz.get(0), "drawable", getPackageName()));
        enunciadoQuestao.setText(quiz.get(1));
        respostaCerta = quiz.get(2);

        // Remove a imagem e a pergunta
        quiz.remove(0);
        quiz.remove(0);


        Collections.shuffle(quiz);
        //Transfere a resposta
        btn1.setText(quiz.get(0));
        btn2.setText(quiz.get(1));
        btn3.setText(quiz.get(2));
        btn4.setText(quiz.get(3));

        questoes.remove(randomNumero);

    }

    public void checarQuestao(View v)
    {
        Button respostaBotao = findViewById(v.getId());
        String btnTexto = respostaBotao.getText().toString();

        String alertaTitulo;

        if(btnTexto.equals(respostaCerta))
        {
            alertaTitulo = "Correto! Parabéns";
            contadorRespostaCerta++;
        }
        else {
            alertaTitulo = "Errado! Ops";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertaTitulo);
        builder.setMessage("Resposta: " + respostaCerta);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(questoes.size()<1)
                {
                    mostrarResultado();
                }
                else
                {
                    contador++;
                    mostrarProximaQuestao();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void mostrarResultado()
    {
        mudarPontuacao(String.valueOf(contadorRespostaCerta));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado!");
        builder.setMessage(contadorRespostaCerta + "/10");
        builder.setPositiveButton("Tente de novo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        });
        builder.setNegativeButton("Sair", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();

    }
}



