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
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.timeleads.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import TimeLeads.model.AlunoModel;
import TimeLeads.repository.AlunoRepository;

public class CriarAluno extends AppCompatActivity {

    Button btnimg;
    Button btnsalva;
    ImageView imgfoto;
    EditText nome;
    EditText dataNasc;
    EditText cpf;
    EditText email;
    EditText senha;
    EditText matricula;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_aluno);

        init();
        btnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        CriarAluno.this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnsalva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlunoModel aluno = new AlunoModel();
                aluno.setNome(nome.getText().toString());
                aluno.setData_nasc(dataNasc.getText().toString());
                aluno.setCPF(cpf.getText().toString());
                aluno.setEmail(email.getText().toString());
                aluno.setSenha(senha.getText().toString());
                aluno.setMatricula(matricula.getText().toString());
                imageViewToByte(imgfoto);
                byte[] fotoemByte = imageViewToByte(imgfoto);
                String fotoemString = Base64.encodeToString(fotoemByte,Base64.DEFAULT);
                aluno.setFoto(fotoemString);
                AlunoRepository repo = new AlunoRepository(CriarAluno.this);
                repo.salvar(aluno);
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
        nome = (EditText) findViewById(R.id.txtNome);
        dataNasc = (EditText) findViewById(R.id.txtDataNasc);
        cpf = (EditText) findViewById(R.id.txtCpf);
        email = (EditText) findViewById(R.id.txtEmail);
        senha = (EditText) findViewById(R.id.txtSenha);
        matricula = (EditText) findViewById(R.id.txtMatricula);
        btnimg = (Button) findViewById(R.id.btnImg);
        btnsalva = (Button) findViewById(R.id.btnSalvar);
        imgfoto = (ImageView) findViewById(R.id.fotoPerfil);
    }
}