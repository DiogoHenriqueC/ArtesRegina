package br.com.dhc.artesregina.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.dhc.artesregina.modelo.Artesao;

public class ArtesaoDAO extends SQLiteOpenHelper {
    public ArtesaoDAO(Context context) {
        super(context, "Artesao", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String sql
        String sql = "CREATE TABLE Artesao (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, endereco TEXT, telefone TEXT, email TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Artesao";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Artesao artesao) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome", artesao.getNome());
        dados.put("endereco", artesao.getEndereco());
        dados.put("telefone", artesao.getTelefone());
        dados.put("email", artesao.getEmail());

        db.insert("Artesao", null, dados);
    }
}
