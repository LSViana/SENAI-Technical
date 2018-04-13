package br.com.senai.meuslivros.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import br.com.senai.meuslivros.model.Livro;

public class LivroDAO extends SQLiteOpenHelper {

    private static final String name = "meuslivros_production";
    private static final String tabelaLivro = "livros";
    private static final int version = 1;
    private List<Livro> livros;

    public LivroDAO(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + tabelaLivro + "(id INTEGER PRIMARY KEY AUTOINCREMENT, caminhoCapa TEXT, tituloLivro TEXT, nomeAutor TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + tabelaLivro;
        sqLiteDatabase.execSQL(sql);
    }

    public void inserir(Livro l) {
        ContentValues cv = new ContentValues();
        //
        cv.put("caminhoCapa", l.getCaminhoDaCapa());
        cv.put("tituloLivro", l.getTituloDoLivro());
        cv.put("nomeAutor", l.getAutorDoLivro());
        //
        getWritableDatabase().insert(tabelaLivro, null, cv);
    }

    public List<Livro> getLivros() {
        List<Livro> result = new ArrayList<>();
        //
        String sql = "SELECT * FROM " + tabelaLivro;
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        while(c.moveToNext()) {
            Livro l = new Livro();
            l.setId(c.getLong(c.getColumnIndex("id")));
            l.setCaminhoDaCapa(c.getString(c.getColumnIndex("caminhoCapa")));
            l.setTituloDoLivro(c.getString(c.getColumnIndex("tituloLivro")));
            l.setAutorDoLivro(c.getString(c.getColumnIndex("nomeAutor")));
            result.add(l);
        }
        //
        return result;
    }

    public List<Livro> filtrar(String texto) {
        List<Livro> result = new ArrayList<>();
        //
        String buscaTexto = "'%" + texto + "%'";
        String sql = "SELECT * FROM " + tabelaLivro + " WHERE tituloLivro LIKE " + buscaTexto + " OR nomeAutor LIKE " + buscaTexto;
        Cursor c = getReadableDatabase().rawQuery(sql, null);
        while(c.moveToNext()) {
            Livro l = new Livro();
            l.setId(c.getLong(c.getColumnIndex("id")));
            l.setCaminhoDaCapa(c.getString(c.getColumnIndex("caminhoCapa")));
            l.setTituloDoLivro(c.getString(c.getColumnIndex("tituloLivro")));
            l.setAutorDoLivro(c.getString(c.getColumnIndex("nomeAutor")));
            result.add(l);
        }
        //
        return result;
    }
}
