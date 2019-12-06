package com.projeto.naturezas.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.R;
import android.widget.TextView;

import com.projeto.naturezas.models.Usuario;

import java.util.List;

public class AdapterUsuariosRank extends BaseAdapter {

    private List<Usuario> listaUsuario;
    private Context context;

    public AdapterUsuariosRank(List<Usuario> listaContatos, Context context) {
        this.listaUsuario = listaContatos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaUsuario.size();
    }

    @Override
    public Usuario getItem(int position) {
        return listaUsuario.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaUsuario.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Inflar a view item
        View view = LayoutInflater.from(context).inflate(R.layout.);

        // Recuperar o contato
        Usuario usuario = listaUsuario.get(position);

        // Recuperar as views
        TextView textViewNome = view.findViewById(R.id.item_contato_nome);
        TextView textViewTelefone = view.findViewById(R.id.item_contato_telefone);
        TextView textViewEnd = view.findViewById(R.id.item_contato_endcompleto);
        TextView textViewcep = view.findViewById(R.id.item_contato_cep);

        // Adicionar na view
        textViewNome.setText(usuario.getNome());
        textViewTelefone.setText(usuario.getNumero());

        return view;
    }

    public void atualiza(List<Usuario> usuarios) {
        listaUsuario.clear();
        listaUsuario.addAll(usuarios);
        notifyDataSetChanged();
    }

    public void remove(Usuario usuarios) {
        listaUsuario.remove(usuarios);
    }

}

