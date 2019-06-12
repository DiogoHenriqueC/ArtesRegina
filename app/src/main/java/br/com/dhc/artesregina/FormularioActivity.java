package br.com.dhc.artesregina;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import br.com.dhc.artesregina.dao.ProdutoDAO;
import br.com.dhc.artesregina.modelo.Produto;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private static final int IMAGE_GALLERY_REQUEST = 1;
    private Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Produto produto = (Produto) intent.getSerializableExtra("produto");
        if (produto != null){
            helper.preencheFormulario(produto);
            if (produto.getImagem() != null) {

                ImageView imga = findViewById(R.id.imageProduto);
                Glide.with(this).load(produto.getImagem()).centerCrop().into(imga);
            }
        }

        Button nextButton = (Button) findViewById(R.id.addImage);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });

    }

    // Selecionar imagem da galeria
    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, IMAGE_GALLERY_REQUEST);
    }


    // Direcionar imagem selecionada na galeria
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case IMAGE_GALLERY_REQUEST:
                    //data.getData returns the content URI for the selected Image
                    selectedImage = data.getData();
                    ImageView imagem = findViewById(R.id.imageProduto);
                    Glide.with(this).load(selectedImage).centerCrop().into(imagem);
                    //imagem.setImageURI(selectedImage);
                    break;
            }
    }


    // Converte imagens para Byte
    public byte[] convertImageToByte(Uri uri){
        byte[] data = null;
        try {
            ContentResolver cr = getBaseContext().getContentResolver();
            InputStream inputStream = cr.openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            data = baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:

                Produto produto = helper.getProduto();
                ProdutoDAO dao = new ProdutoDAO(this);

                if(selectedImage != null){
                    produto.setImagem(convertImageToByte(selectedImage));
                }

                if(produto.getId() != null){
                    dao.altera(produto);

                } else{
                    dao.insere(produto);

                }
                dao.close();

                //Toast.makeText(FormularioActivity.this, "Produto salvo!", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
