package br.com.senai.meuslivros;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import br.com.senai.meuslivros.data.LivroDAO;
import br.com.senai.meuslivros.helper.FormularioHelper;
import br.com.senai.meuslivros.model.Livro;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_GALERIA = 1;
    public static final int PERMISSAO_REQUEST = 1;
    private Button botaoCadastrar;
    private ImageView capaLivro;
    private FormularioHelper helper;
    private LivroDAO livroDAO;

    @Override
    protected void onStart() {
        super.onStart();
        livroDAO = new LivroDAO(getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
        botaoCadastrar = helper.getBotaoCadastrar();
        capaLivro = helper.getCapaDoLivro();

        capaLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CODIGO_GALERIA);
            }
        });

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Livro l = helper.getLivro();
                livroDAO.inserir(l);
                finish();
            }
        });

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSAO_REQUEST);
            }
        }

    }//fiM OnCreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == CODIGO_GALERIA){
            Uri uriDaImagem = data.getData();
            String[] diretorios = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(uriDaImagem,diretorios, null, null ,null);
            c.moveToFirst();
            int collumnIdex = c.getColumnIndex(diretorios[0]);
            String caminhoDaImagem = c.getString(collumnIdex);
            c.close();
            Bitmap imagemRetornada = BitmapFactory.decodeFile(caminhoDaImagem);
            capaLivro.setImageBitmap(imagemRetornada);
            capaLivro.setTag(caminhoDaImagem);
        }

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSAO_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // / A permissão foi concedida. Pode continuar
            } else{
              // A permissão foi negada. Precisa ver o que deve ser desabilitado

            }
            return;
        }
    }
}
