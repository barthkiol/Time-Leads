package TimeLeads.example.timeleads;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.timeleads.R;

import java.util.List;

import TimeLeads.model.AlunoModel;
import TimeLeads.repository.AlunoRepository;
import TimeLeads.uteis.LinhaConsultarAdapter;

public class StudentsActivity extends AppCompatActivity {

    ListView lista_alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int curso = (int) getIntent().getIntExtra("CURSO_ID", 0);
        setContentView(R.layout.activity_students);
        lista_alunos = (ListView) findViewById(R.id.lista_aluno);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        this.CarregarAlunos();

    }

    protected void CarregarAlunos(){
        AlunoRepository alunoRepository = new AlunoRepository(this);
        int curso = (int) getIntent().getIntExtra("CURSO_ID", 0);
        String nomeCurso = (String) getIntent().getStringExtra("CURSO_NOME");
        getSupportActionBar().setTitle(nomeCurso);
        List<AlunoModel> alunos = alunoRepository.ListarAlunosCoordenador(curso);
        lista_alunos.setAdapter((ListAdapter) new LinhaConsultarAdapter(this, alunos));


    }
}