package br.com.dhc.artesregina.adapters;


import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.dhc.artesregina.R;
import br.com.dhc.artesregina.modelo.Produto;

public class CarrosselAdapter extends RecyclerView.Adapter<CarrosselAdapter.ViewHolder> {

    List<Produto> produtos;
    private AdapterView.OnItemClickListener clickImagem3;

    public CarrosselAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setClickImagem(AdapterView.OnItemClickListener clickImagem) {
        this.clickImagem3 = clickImagem;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;
        private ImageView imagem;
        private TextView autor;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.artesanato_item);
            imagem = itemView.findViewById(R.id.quadrado_pequeno);
            autor = itemView.findViewById(R.id.autor_menor);

            imagem.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                          if(clickImagem3 != null){
                          clickImagem3.onItemClick(null, itemView, getAdapterPosition(), getItemId());
                      }
                  }
              }
            );
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout padrao = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.carrossel_item, parent, false);
        return new ViewHolder(padrao);

    }

    @Override
    public void onBindViewHolder(@NonNull CarrosselAdapter.ViewHolder holder, int position) {
        Produto produto = produtos.get(position);
        holder.titulo.setText(produto.getNome());
        holder.autor.setText(produto.getAutor());
        if(produto.getImagem() != null){
            Glide.with(holder.itemView).load(produto.getImagem()).centerCrop().into(holder.imagem);
        }
        holder.itemView.setTag(produto);
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }
}
