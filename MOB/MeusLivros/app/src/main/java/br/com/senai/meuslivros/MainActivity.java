package br.com.senai.meuslivros;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.List;

import br.com.senai.meuslivros.adapter.LivroAdapter;
import br.com.senai.meuslivros.data.LivroDAO;
import br.com.senai.meuslivros.model.Livro;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private LivroDAO livroDAO;
    private SearchView campoBusca;
    private RecyclerView listaDeLivros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        campoBusca = findViewById(R.id.campoBusca);
        campoBusca.setOnQueryTextListener(this);
        listaDeLivros = findViewById(R.id.listaDeLivros);
        livroDAO = new LivroDAO(getApplicationContext());
        List<Livro> livros = livroDAO.getLivros();
        carregarLista(livros);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void carregarLista(List<Livro> livros) {
        LivroAdapter adapter = new LivroAdapter(this, livros);
        RecyclerView.LayoutManager lm = new GridLayoutManager(this, 2);
        listaDeLivros.setAdapter(adapter);
        listaDeLivros.setLayoutManager(lm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_livros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista(livroDAO.getLivros());
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        carregarLista(livroDAO.filtrar(s));
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        carregarLista(livroDAO.filtrar(s));
        return true;
    }

}
