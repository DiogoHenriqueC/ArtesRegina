package br.com.dhc.artesregina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.dhc.artesregina.dao.ProdutoDAO;
import br.com.dhc.artesregina.modelo.Produto;

public class FolhaProdutoActivity extends AppCompatActivity {

    private FolhaProdutoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        helper = new FolhaProdutoHelper(this);
        ProdutoDAO dao = new ProdutoDAO(this);
        Intent intent = getIntent();
        intent.getLongExtra("produto", 0L);
        Produto produto = dao.buscaProduto(intent.getLongExtra("produto", 0L));
        dao.close();

        if (produto != null){
            helper.preencheFolhaProduto(produto);
            if (produto.getImagem() != null) {
                ImageView imga = findViewById(R.id.imagem_produto);
                Glide.with(this).load(produto.getImagem()).centerCrop().into(imga);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
