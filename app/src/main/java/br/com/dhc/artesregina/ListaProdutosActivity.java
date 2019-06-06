package br.com.dhc.artesregina;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.dhc.artesregina.dao.ProdutoDAO;
import br.com.dhc.artesregina.modelo.Produto;

public class ListaProdutosActivity extends AppCompatActivity {

    private ListView listaProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        // PRECISA FILTRAR PELO USUARIO LOGADO
        // OPCAO "MEUS PRODUTOS" SO APARECE QUANDO LOGADO
        // Criar o delete do item

        listaProdutos = (ListView) findViewById(R.id.lista_produtos);

        registerForContextMenu(listaProdutos);

        listaProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produto = (Produto) listaProdutos.getItemAtPosition(position);
                Toast.makeText(ListaProdutosActivity.this, "Aluno " + produto.getNome() + " clicado", Toast.LENGTH_SHORT).show();

                Intent intentVaiProFormulario = new Intent(ListaProdutosActivity.this, FormularioActivity.class);
                intentVaiProFormulario.putExtra("produto", produto);
                startActivity(intentVaiProFormulario);

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        // Deletar item
        MenuItem del = menu.add("Deletar");
        del.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Produto produto = (Produto) listaProdutos.getItemAtPosition(info.position);

                ProdutoDAO dao = new ProdutoDAO(ListaProdutosActivity.this);
                dao.deleta(produto);
                dao.close();

                carregaLista();

                // Toast.makeText(ListaAlunosActivity.this, "Delete " + aluno.getNome(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {

        // Pegando da String
        //String[] alunos = {"Daniel", "Ronaldo", "Jeferson", "Felipe"};
        //ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        //ArrayAdapter<String> DestaqueAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
        //listaAlunos.setAdapter(DestaqueAdapter);

        //Utilizando o Banco
        ProdutoDAO dao = new ProdutoDAO(this);
        List<Produto> produtos = dao.buscaProdutos();
        dao.close();

        ListView listaProdutos = (ListView) findViewById(R.id.lista_produtos);
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, produtos);
        listaProdutos.setAdapter(adapter);
    }
}
