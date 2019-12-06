package com.projeto.naturezas.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.naturezas.R;
import com.projeto.naturezas.models.Usuario;

import java.util.List;

public class AdapterRanking extends BaseAdapter {

    private List<Usuario> usuarioList;
    private Context context;

    public AdapterRanking(List<Usuario> usuarioList, Context context) {
        this.usuarioList = usuarioList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return usuarioList.size();
    }

    @Override
    public Object getItem(int position) {
        return usuarioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return usuarioList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_ranking, parent, false);

        Usuario usuario = usuarioList.get(position);

        byte[] decodedString = Base64.decode(usuario.getFoto(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        ImageView imageViewItem = view.findViewById(R.id.item_usuario_img);
        TextView textViewItemNome = view.findViewById(R.id.item_usuario_nome);
        TextView textViewItemPontuacao = view.findViewById(R.id.item_usuario_pontuacao);

        imageViewItem.setImageBitmap(decodedByte);
        imageViewItem.setTag(1);
        textViewItemNome.setText(usuario.getNome());
        textViewItemPontuacao.setText(usuario.getPontuacao());

        return view;
    }
}
