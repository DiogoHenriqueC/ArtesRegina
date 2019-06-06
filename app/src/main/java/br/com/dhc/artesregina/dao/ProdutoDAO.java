package br.com.dhc.artesregina.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.dhc.artesregina.modelo.Produto;

public class ProdutoDAO extends SQLiteOpenHelper {
    public ProdutoDAO(Context context) {
        super(context, "MostrArt", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //QUERY ON CREATE
        String sql = "CREATE TABLE Produtos (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, descricao TEXT, categoria TEXT, autor TEXT, price REAL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //QUERY ON CREATE
        String sql = "DROP TABLE IF EXISTS Produtos";
        db.execSQL(sql);
        onCreate(db);

    }

    public void insere(Produto produto) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesProduto(produto);
        db.insert("Produtos", null, dados);

    }

    @NonNull
    private ContentValues getContentValuesProduto(Produto produto) {
        ContentValues dados = new ContentValues();
        dados.put("nome", produto.getNome());
        dados.put("descricao", produto.getDescricao());
        dados.put("categoria", produto.getCategoria());
        dados.put("autor", produto.getAutor());
        dados.put("price", produto.getPrice());
        //dados.put("imagem", produto.getImagem());
        return dados;
    }

    public List<Produto> buscaProdutos() {

        String sql = "SELECT * FROM Produtos;";
        SQLiteDatabase db = getReadableDatabase();

        // Parametro pra busca - Retorna cursor
        Cursor c = db.rawQuery(sql, null);

        // Lista de produtos
        List<Produto> produtos = new ArrayList<Produto>();

        // Enquanto tiver proxima linha.. executa
        while (c.moveToNext()){

            Produto produto = new Produto();
            produto.setId(c.getLong(c.getColumnIndex("id")));
            produto.setNome(c.getString(c.getColumnIndex("nome")));
            produto.setDescricao(c.getString(c.getColumnIndex("descricao")));
            produto.setCategoria(c.getString(c.getColumnIndex("categoria")));
            produto.setAutor(c.getString(c.getColumnIndex("autor")));
            produto.setPrice(c.getString(c.getColumnIndex("price")));

            produtos.add(produto);

        }

        c.close();
        return produtos;

    }

    public void altera(Produto produto) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = getContentValuesProduto(produto);
        String[] params = {produto.getId().toString()};
        db.update("Produtos", dados,"id = ?", params);

    }

    public void deleta(Produto produto) {

        SQLiteDatabase db = getWritableDatabase();
        String[] params = {produto.getId().toString()};
        db.delete("Produtos", "id = ?", params);

    }
}
