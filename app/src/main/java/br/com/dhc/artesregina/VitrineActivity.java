package br.com.dhc.artesregina;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import com.dgreenhalgh.android.simpleitemdecoration.linear.DividerItemDecoration;
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration;
import com.dgreenhalgh.android.simpleitemdecoration.linear.StartOffsetItemDecoration;

import java.util.List;

import br.com.dhc.artesregina.adapters.CarrosselAdapter;
import br.com.dhc.artesregina.adapters.DestaqueAdapter;
import br.com.dhc.artesregina.dao.ProdutoDAO;
import br.com.dhc.artesregina.modelo.Produto;

public class VitrineActivity extends AppCompatActivity {

    RecyclerView carrosselDestaque;
    RecyclerView carrosselPadrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitrine);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(VitrineActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

        Resources resources = this.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        float px = 16 * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        float px2 = 16 * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        carrosselDestaque = findViewById(R.id.recycle_destaque);
        carrosselDestaque.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider)));
        carrosselDestaque.addItemDecoration(new StartOffsetItemDecoration(Math.round(px)));
        carrosselDestaque.addItemDecoration(new EndOffsetItemDecoration(Math.round(px)));
        carrosselDestaque.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        carrosselPadrao = findViewById(R.id.carrossel_menor);
        carrosselPadrao.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.divider)));
        carrosselPadrao.addItemDecoration(new StartOffsetItemDecoration(Math.round(px2)));
        carrosselPadrao.addItemDecoration(new EndOffsetItemDecoration(Math.round(px2)));
        carrosselPadrao.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
        carregaLista2();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vitrine, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.meus_produtos) {

            Toast.makeText(VitrineActivity.this, "Meus Produtos", Toast.LENGTH_SHORT).show();

            Intent intentVaiProFormulario = new Intent(VitrineActivity.this, ListaProdutosActivity.class);
            startActivity(intentVaiProFormulario);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void carregaLista() {

        ProdutoDAO dao = new ProdutoDAO(this);
        List<Produto> produtos = dao.buscaProdutos();
        dao.close();

        // Para filtrar exibicao tem que filtrar os produtos e alimentar o adpater com os produtos ja filtrados
        DestaqueAdapter adapter = new DestaqueAdapter(produtos);
        adapter.setClickImagem(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produto = (Produto) view.getTag();

                Intent intentVaiProProduto = new Intent(VitrineActivity.this, FolhaProdutoActivity.class);
                intentVaiProProduto.putExtra("produto", produto);
                startActivity(intentVaiProProduto);

                Toast.makeText(VitrineActivity.this, produto.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        carrosselDestaque.setAdapter(adapter);

    }


    private void carregaLista2(){
        ProdutoDAO dao2 = new ProdutoDAO(this);
        List<Produto> produtosCategoria = dao2.buscaProdutos();
        dao2.close();

        CarrosselAdapter adapterPadrao = new CarrosselAdapter(produtosCategoria);
        adapterPadrao.setClickImagem(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto prod = (Produto) view.getTag();

                Intent intentVaiProProduto = new Intent(VitrineActivity.this, FolhaProdutoActivity.class);
                intentVaiProProduto.putExtra("produto", prod);
                startActivity(intentVaiProProduto);

                Toast.makeText(VitrineActivity.this, prod.getNome(), Toast.LENGTH_SHORT).show();
            }
        });

        carrosselPadrao.setAdapter(adapterPadrao);
    }




}
