package TimeLeads.example.timeleads;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timeleads.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import TimeLeads.model.AlunoModel;
import TimeLeads.model.ChamadoModel;
import TimeLeads.model.EventModel;
import TimeLeads.model.HorasComplementaresModel;
import TimeLeads.repository.AlunoRepository;
import TimeLeads.repository.ChamadoRepository;
import TimeLeads.repository.EventRepository;
import TimeLeads.repository.HorasComplementaresRepository;

public class RequestActivity extends AppCompatActivity {

    TextView titulo, status, data, descricao;
    ImageView arquivo;
    FloatingActionButton faba, fabr;
    private String m_Text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        int idAlunoLogado = (int) getIntent().getIntExtra("ALUNO_ID", 0);
        init();

        if(idAlunoLogado != 0){
            faba.setVisibility(View.GONE);
            fabr.setVisibility(View.GONE);
        }  // Apagar os botões de Aceitar e Recusar caso seja aluno

        int id = (int) getIntent().getIntExtra("CHAMADO_ID", 0);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        getSupportActionBar().setTitle("Validar Chamado");

        Bundle extra = this.getIntent().getExtras();
        int id_chamado = extra.getInt("CHAMADO_ID");
        ChamadoModel chamadoModel = new ChamadoRepository(this).GetChamado(id_chamado);
        AlunoModel alunoModel = new AlunoRepository(this).GetAluno(chamadoModel.getAluno_id());

        titulo.setText(chamadoModel.getTitulo());
        status.setText(alunoModel.getNome());
        data.setText("Data: " + chamadoModel.getData_envio());
        descricao.setText(chamadoModel.getDescricao());
        arquivo.setImageBitmap(StringToBitMap(chamadoModel.getArquivo()));

        faba.setOnClickListener(new View.OnClickListener() { // chamado aceito
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RequestActivity.this);
                builder.setTitle("Aceitar Chamado");

                // Set up the input
                final EditText input = new EditText(RequestActivity.this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setHint("Horas Validas");
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("Validar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();

                        //cria hora complementar para o aluno
                        HorasComplementaresRepository horasRepo = new HorasComplementaresRepository(RequestActivity.this);
                        HorasComplementaresModel horasModel = new HorasComplementaresModel();
                        horasModel.setAluno_id(chamadoModel.getAluno_id());
                        horasModel.setNome(chamadoModel.getTitulo());
                        horasModel.setHoras(m_Text);
                        horasRepo.salvar(horasModel);

                        //atualiza o chamado para aceito
                        ChamadoRepository chamadoRepo = new ChamadoRepository(RequestActivity.this);
                        ChamadoModel chamadoEdit = new ChamadoModel();
                        chamadoEdit.setId(chamadoModel.getId());
                        chamadoEdit.setArquivo(chamadoModel.getArquivo());
                        chamadoEdit.setAluno_id(chamadoModel.getAluno_id());
                        chamadoEdit.setData_envio(chamadoModel.getData_envio());
                        chamadoEdit.setTitulo(chamadoModel.getTitulo());
                        chamadoEdit.setDescricao(chamadoModel.getDescricao());
                        chamadoEdit.setStatus("ACEITO");
                        chamadoRepo.editar(chamadoEdit);
                        finish();
                        Toast.makeText(RequestActivity.this, "Chamado aceito com sucesso!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        }); // Aceitar Chamado

        fabr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RequestActivity.this);
                builder.setTitle("Recusar Chamado");


                final TextView text = new TextView(RequestActivity.this);
                text.setText("Tem certeza que deseja recusar esse chamado?");
                builder.setView(text);

                // Set up the buttons
                builder.setPositiveButton("Recusar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //atualiza o chamado para Recusado
                        ChamadoRepository chamadoRepo = new ChamadoRepository(RequestActivity.this);
                        ChamadoModel chamadoEdit = new ChamadoModel();
                        chamadoEdit.setId(chamadoModel.getId());
                        chamadoEdit.setArquivo(chamadoModel.getArquivo());
                        chamadoEdit.setAluno_id(chamadoModel.getAluno_id());
                        chamadoEdit.setData_envio(chamadoModel.getData_envio());
                        chamadoEdit.setTitulo(chamadoModel.getTitulo());
                        chamadoEdit.setDescricao(chamadoModel.getDescricao());
                        chamadoEdit.setStatus("RECUSADO");
                        chamadoRepo.editar(chamadoEdit);
                        Toast.makeText(RequestActivity.this, "Chamado recusado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();


                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        }); // Recusar Chamado

    }

    public void init(){
        titulo = findViewById(R.id.txtTituloR);
        status = findViewById(R.id.txtStatusR);
        data = findViewById(R.id.txtDataR);
        descricao = findViewById(R.id.txtDescR);
        faba = findViewById(R.id.btnAccept);
        fabr = findViewById(R.id.btnRefuse);
        arquivo = findViewById(R.id.imageView4);
    }  // Iniciar as Views

    public Bitmap StringToBitMap(String encodedString){ //
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