package TimeLeads.example.timeleads;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timeleads.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import TimeLeads.model.ChamadoModel;
import TimeLeads.repository.AlunoRepository;
import TimeLeads.repository.ChamadoRepository;

public class CriarChamado extends AppCompatActivity {

    TextView title, description;
    ImageView arquivo;
    Button anexar;
    FloatingActionButton fab;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_chamado);
        getSupportActionBar().setTitle("Novo Chamado");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        int idAluno = (int) getIntent().getIntExtra("ALUNO_ID", 0);

        init();
        anexar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        CriarChamado.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

      fab.setEnabled(true);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChamadoModel chamadoModel = new ChamadoModel();
                chamadoModel.setTitulo(title.getText().toString());
                chamadoModel.setDescricao(description.getText().toString());
                imageViewToByte(arquivo);
                byte[] fotoemByte = imageViewToByte(arquivo);
                String fotoemString = Base64.encodeToString(fotoemByte,Base64.DEFAULT);
                chamadoModel.setArquivo(fotoemString);
                chamadoModel.setStatus("ABERTO");
                chamadoModel.setAluno_id(idAluno);
                calendar = Calendar.getInstance();
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                date = dateFormat.format(calendar.getTime());
                chamadoModel.setData_envio(date);

                ChamadoRepository repo = new ChamadoRepository(CriarChamado.this);
                repo.salvar(chamadoModel);
                Toast.makeText(CriarChamado.this, "Chamado criado com sucesso!", Toast.LENGTH_SHORT).show();
                finish();

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
                arquivo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        title = (EditText) findViewById(R.id.requestTitle);
        description = (EditText) findViewById(R.id.requestDescription);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        anexar = (Button) findViewById(R.id.btnAnexar);
        arquivo = (ImageView) findViewById(R.id.requestArquivo);
    }
}