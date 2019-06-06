package br.com.dhc.artesregina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.dhc.artesregina.modelo.Produto;

public class FolhaProdutoActivity extends AppCompatActivity {

    private FolhaProdutoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        helper = new FolhaProdutoHelper(this);

        Intent intent = getIntent();
        Produto produto = (Produto) intent.getSerializableExtra("produto");
        if (produto != null){
            helper.preencheFolhaProduto(produto);

        }

    }







}
