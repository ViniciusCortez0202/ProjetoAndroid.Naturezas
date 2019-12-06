package com.projeto.naturezas.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.R;
import com.projeto.naturezas.models.Usuario;

import java.util.List;

public class AdapterUsuariosRank extends BaseAdapter {

    private List<Usuario> listaUsuarios;
    private Context context;

    public AdapterUsuariosRank(List<Usuario> listaUsuarios, Context context) {
        this.listaUsuarios = listaUsuarios;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaUsuarios.size();
    }


    @Override
    public Usuario getItem(int position) {
        return listaUsuarios.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaUsuarios.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflar a view
        View view = LayoutInflater.from(context).inflate(, parent, false);
        //comentario aletorio pra tentar dar comit


        return view;
    }
}

