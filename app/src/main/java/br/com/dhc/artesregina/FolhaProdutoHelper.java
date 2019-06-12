package br.com.dhc.artesregina;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import br.com.dhc.artesregina.modelo.Produto;

public class FolhaProdutoHelper {

    private TextView nome;
    private TextView descricao;
    private TextView categoria;
    private TextView autor;
    private TextView price;
    private ImageView imagem;

    private Produto produto;

    public FolhaProdutoHelper(FolhaProdutoActivity activity){
        nome = (TextView) activity.findViewById(R.id.artesanato);
        descricao = (TextView) activity.findViewById(R.id.descricao);
        categoria = (TextView) activity.findViewById(R.id.categoria);
        autor = (TextView) activity.findViewById(R.id.autor);
        price = (TextView) activity.findViewById(R.id.price);
        imagem = (ImageView) activity.findViewById(R.id.imageProduto);

        produto = new Produto();

    }

    public Produto getProduto2() {

        produto.setNome(nome.getText().toString());
        produto.setDescricao(descricao.getText().toString());
        produto.setCategoria(categoria.getText().toString());
        produto.setAutor(autor.getText().toString());
        produto.setPrice(price.getText().toString());

        return produto;
    }

    public void preencheFolhaProduto(Produto produto) {

        nome.setText(produto.getNome());
        descricao.setText(produto.getDescricao());
        categoria.setText(produto.getCategoria());
        autor.setText(produto.getAutor());
        price.setText(produto.getPrice());

        this.produto = produto;
    }
}
