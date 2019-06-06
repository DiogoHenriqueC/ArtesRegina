package br.com.dhc.artesregina.adapters;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.dhc.artesregina.R;
import br.com.dhc.artesregina.modelo.Produto;

public class DestaqueAdapter extends RecyclerView.Adapter<DestaqueAdapter.ViewHolder> {

    List<Produto> produtos;
    private AdapterView.OnItemClickListener clickImagem;

    public DestaqueAdapter(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void setClickImagem(AdapterView.OnItemClickListener clickImagem) {
        this.clickImagem = clickImagem;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;
        private ImageView imagem;
        private TextView descricao;
        private TextView autor;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            imagem = itemView.findViewById(R.id.image);
            descricao = itemView.findViewById(R.id.disponibilidade);
            autor = itemView.findViewById(R.id.autor);

            imagem.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(clickImagem != null){
                            clickImagem.onItemClick(null, itemView, getAdapterPosition(), getItemId());
                        }
                    }
                }
            );
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ConstraintLayout destaque = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.carrossel_destaque_item, parent, false);
        return new ViewHolder(destaque);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Produto produto = produtos.get(position);
        holder.titulo.setText(produto.getNome());
        holder.descricao.setText(produto.getDescricao());
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
