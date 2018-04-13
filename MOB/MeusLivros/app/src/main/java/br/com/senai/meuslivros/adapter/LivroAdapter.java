package br.com.senai.meuslivros.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.List;

import br.com.senai.meuslivros.R;
import br.com.senai.meuslivros.holder.LivroHolder;
import br.com.senai.meuslivros.model.Livro;

/**
 * Created by 49249273843 on 13/04/2018.
 */

public class LivroAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final List<Livro> livros;

    public LivroAdapter(Context context, List<Livro> livros) {
        this.context = context;
        this.livros = livros;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lista_item_livro, parent, false);
        LivroHolder holder = new LivroHolder(view, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LivroHolder livroHolder = (LivroHolder) holder;
        Livro livro = livros.get(position);
        livroHolder.preencher(livro);
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }
}
