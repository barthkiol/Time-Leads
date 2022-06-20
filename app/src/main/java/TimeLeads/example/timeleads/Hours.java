package TimeLeads.example.timeleads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timeleads.R;

import java.util.ArrayList;
import java.util.List;

import TimeLeads.model.AlunoModel;
import TimeLeads.model.ChamadoModel;
import TimeLeads.model.HorasComplementaresModel;
import TimeLeads.repository.AlunoRepository;
import TimeLeads.repository.Curso_AlunoRepository;
import TimeLeads.repository.HorasComplementaresRepository;

public class Hours extends Fragment {

    ListView lv;
    View rootview;
    ArrayAdapter<String> adapter;

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.hours,container, false);
        int idAlunoLogado = (int) getActivity().getIntent().getIntExtra("ALUNO_ID", 0);

        AlunoRepository alunoRepository = new AlunoRepository(((MainActivity) getContext()));
        AlunoModel aluno = alunoRepository.GetAluno(idAlunoLogado);
        HorasComplementaresRepository horasComplementaresRepository = new HorasComplementaresRepository(((MainActivity) getContext()));
        Curso_AlunoRepository curso_alunoRepository = new Curso_AlunoRepository(((MainActivity) getContext()));
        Integer totalHoras = horasComplementaresRepository.GetTotalHorasComplementaresAluno(idAlunoLogado);
        String horasCurso = curso_alunoRepository.GetHorasCursoAluno(idAlunoLogado);
        progressBar = (ProgressBar) rootview.findViewById(R.id.progressBar);
        progressBar.setMax(Integer.parseInt(horasCurso));

        if(totalHoras > Integer.parseInt(horasCurso)){
            totalHoras = Integer.parseInt(horasCurso);
        }
        if(totalHoras == null || totalHoras == 0){
            progressBar.setProgress(0);
        }else{
            progressBar.setProgress(totalHoras);
        }

        TextView txtProgress = (TextView) rootview.findViewById(R.id.txtProgress);
        if(totalHoras >= Integer.parseInt(horasCurso)){
            txtProgress.setText("Parabéns " + aluno.getNome() + ", você complementou todas as horas complementares do seu curso. Ótimo trabalho!");

        }else{
            txtProgress.setText("Ola " + aluno.getNome() + ", você complementou " + totalHoras + " horas complementares no seu curso!");
        }


        TextView txtNumber = (TextView) rootview.findViewById(R.id.txtStartEnd);
        txtNumber.setText(progressBar.getProgress() + " / " + progressBar.getMax());


        List<HorasComplementaresModel> horas = horasComplementaresRepository.ListarHorasComplementaresAluno(idAlunoLogado);
        int l = horas.size();
        String [] horasString = new String[l];

        for (int i=0;i<l;i++){
            horasString[i] = horas.get(i).getNome() + " - " + horas.get(i).getHoras() + " horas";

        }
        lv = rootview.findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.requestitem, R.id.text_view, horasString);
        lv.setAdapter(adapter);

        return rootview;

    }
}
