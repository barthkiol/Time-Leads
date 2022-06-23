package TimeLeads.example.timeleads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timeleads.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import TimeLeads.model.AlunoModel;
import TimeLeads.model.CursoModel;
import TimeLeads.model.Curso_AlunoModel;
import TimeLeads.repository.AlunoRepository;
import TimeLeads.repository.CursoRepository;
import TimeLeads.repository.Curso_AlunoRepository;

public class Profile extends Fragment {

    TextView nome, email, matricula, cpf, data, curso;
    ImageView picture;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.profile,container, false);
        int idAlunoLogado = (int) getActivity().getIntent().getIntExtra("ALUNO_ID", 0);
        AlunoRepository alunoRepository = new AlunoRepository(((MainActivity) getContext()));
        AlunoModel aluno = alunoRepository.GetAluno(idAlunoLogado);
        Curso_AlunoRepository cursoRepository = new Curso_AlunoRepository((MainActivity) getContext());
        Curso_AlunoModel cursoModel = cursoRepository.GetCurso_Aluno(idAlunoLogado);
        CursoModel cursoAluno = new CursoRepository((MainActivity) getContext()).GetCurso(cursoModel.getCurso_id());

        curso = (TextView) rootview.findViewById(R.id.cursoAluno);
        nome = (TextView) rootview.findViewById(R.id.nomeAluno);
        email = (TextView) rootview.findViewById(R.id.emailAluno);
        matricula = (TextView) rootview.findViewById(R.id.matriculaAluno);
        cpf = (TextView) rootview.findViewById(R.id.cpfAluno);
        data = (TextView) rootview.findViewById(R.id.dataNasceAluno);
        picture = (ImageView) rootview.findViewById(R.id.fotoAluno);

        nome.setText(aluno.getNome());
        email.setText(aluno.getEmail());
        matricula.setText("Matricula: " + aluno.getMatricula());
        cpf.setText("CPF: " + aluno.getCPF());
        data.setText("Data Nasc.: " + aluno.getData_nasc());
        curso.setText(cursoAluno.getNome() + " " + cursoModel.getPeriodo() + "º Período");
        picture.setImageBitmap(StringToBitMap(aluno.getFoto()));

        /*
        ByteArrayInputStream imageStream = new ByteArrayInputStream(aluno.getFoto());
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        picture.setImageBitmap(theImage);

         */
        return rootview;
    }

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