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
import TimeLeads.repository.AlunoRepository;

public class Profile extends Fragment {

    TextView nome, email, matricula, cpf, data;
    ImageView picture;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.profile,container, false);
        int idAlunoLogado = (int) getActivity().getIntent().getIntExtra("ALUNO_ID", 0);
        AlunoRepository alunoRepository = new AlunoRepository(((MainActivity) getContext()));
        AlunoModel aluno = alunoRepository.GetAluno(idAlunoLogado);

        nome = (TextView) rootview.findViewById(R.id.nomeAluno);
        email = (TextView) rootview.findViewById(R.id.emailAluno);
        matricula = (TextView) rootview.findViewById(R.id.matriculaAluno);
        cpf = (TextView) rootview.findViewById(R.id.cpfAluno);
        data = (TextView) rootview.findViewById(R.id.dataNasceAluno);
        picture = (ImageView) rootview.findViewById(R.id.fotoAluno);

        nome.setText(aluno.getNome());
        email.setText(aluno.getEmail());
        matricula.setText(aluno.getMatricula());
        cpf.setText(aluno.getCPF());
        data.setText(aluno.getData_nasc());
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