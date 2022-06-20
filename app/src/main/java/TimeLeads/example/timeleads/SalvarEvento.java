package TimeLeads.example.timeleads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.timeleads.R;
import com.google.android.material.button.MaterialButtonToggleGroup;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import TimeLeads.model.EventModel;
import TimeLeads.repository.EventRepository;

public class SalvarEvento extends AppCompatActivity {

    Button btnimg;
    Button btnsalva;
    ImageView imgfoto;
    EditText titulo;
    EditText data;
    EditText horas;
    EditText descricao;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvar_evento);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));


        init();
        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        SalvarEvento.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnsalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventModel evento = new EventModel();
                evento.setDescricao(descricao.getText().toString());
                evento.setHoras_validas(horas.getText().toString());
                evento.setTitulo(titulo.getText().toString());
                evento.setData(data.getText().toString());
                imageViewToByte(imgfoto);
                byte[] fotoemByte = imageViewToByte(imgfoto);
                String fotoemString = Base64.encodeToString(fotoemByte,Base64.DEFAULT);
                evento.setImagem(fotoemString);
                EventRepository repo = new EventRepository(SalvarEvento.this);
                repo.salvar(evento);
            }

        });
    }
    private byte[] imageViewToByte(ImageView img) {
        Bitmap bitmap = ((BitmapDrawable) img.getDrawable() ).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0  && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgfoto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        titulo = (EditText) findViewById(R.id.txtTitle);
        data = (EditText) findViewById(R.id.txtDataEvent);
        descricao = (EditText) findViewById(R.id.txtDescricao);
        horas = (EditText) findViewById(R.id.txtHours);
        btnimg = (Button) findViewById(R.id.btnImg);
        btnsalva = (Button) findViewById(R.id.btnSalvar);
        imgfoto = (ImageView) findViewById(R.id.fotoEvent);
    }

    public Bitmap StringToBitMap(String encodedString){ // imgfoto.setImageBitmap(StringToBitMap(evento.getImagem()));
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}