package br.com.dhc.artesregina;

import android.content.Intent;
import android.widget.EditText;

import br.com.dhc.artesregina.modelo.Produto;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoDescricao;
    private final EditText campoCategoria;
    private final EditText campoAutor;
    private final EditText campoPrice;

    private Produto produto;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoDescricao = (EditText) activity.findViewById(R.id.formulario_descricao);
        campoCategoria = (EditText) activity.findViewById(R.id.formulario_categoria);
        campoAutor = (EditText) activity.findViewById(R.id.formulario_autor);
        campoPrice = (EditText) activity.findViewById(R.id.formulario_price);

        produto = new Produto();
    }

    public Produto getProduto() {

        produto.setNome(campoNome.getText().toString());
        produto.setDescricao(campoDescricao.getText().toString());
        produto.setAutor(campoAutor.getText().toString());
        produto.setCategoria(campoCategoria.getText().toString());
        produto.setPrice(campoPrice.getText().toString());

        //precisa tratar como double o preco, layout nao ta ficando compativel.
        //aluno.setNota(Double.valueOf(campoNota.getProgress()));

        return produto;
    }

    public void preencheFormulario(Produto produto) {

        campoNome.setText(produto.getNome());
        campoDescricao.setText(produto.getDescricao());
        campoAutor.setText(produto.getAutor());
        campoCategoria.setText(produto.getCategoria());
        campoPrice.setText(produto.getPrice());

        this.produto = produto;

    }


    public void setImagem(byte[] bb){
        produto.setImagem(bb);
    }




}
