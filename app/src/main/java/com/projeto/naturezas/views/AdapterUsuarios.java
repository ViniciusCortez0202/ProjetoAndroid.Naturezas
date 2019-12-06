package br.ifal.aula.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.ifal.aula.R;
import br.ifal.aula.domain.Contato;

public class AdapterContato extends BaseAdapter {


    private List<Contato> listaContatos;
    private Context context;

    public AdapterContato(List<Contato> listaContatos, Context context) {
        this.listaContatos = listaContatos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaContatos.size();
    }

    @Override
    public Contato getItem(int position) {
        return listaContatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaContatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflar a view item
        View view = LayoutInflater.from(context).inflate(R.layout.item_contato, parent, false);

        // Recuperar o contato
        Contato c = listaContatos.get(position);

        // Recuperar as views
        TextView textViewNome = view.findViewById(R.id.item_contato_nome);
        TextView textViewTelefone = view.findViewById(R.id.item_contato_telefone);
        TextView textViewEnd = view.findViewById(R.id.item_contato_endcompleto);
        TextView textViewcep = view.findViewById(R.id.item_contato_cep);

        // Adicionar na view
        textViewNome.setText(c.getNome());
        textViewTelefone.setText(c.getTelefone());

        // Verifica se o campo endereco existe
        if(textViewEnd != null){
            String endereco = c.getRua() + ", " + c.getCidade() + " - " + c.getUf();
            textViewEnd.setText(endereco);
            textViewcep.setText(c.getCep());
        }

        return view;
    }

    public void atualiza(List<Contato> contatos) {
        listaContatos.clear();
        listaContatos.addAll(contatos);
        notifyDataSetChanged();
    }

    public void remove(Contato contatos) {
        listaContatos.remove(contatos);
        //notifyDataSetChanged();
    }

}
