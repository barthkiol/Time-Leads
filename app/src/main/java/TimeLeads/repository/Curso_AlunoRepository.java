package TimeLeads.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import TimeLeads.model.Curso_AlunoModel;
import TimeLeads.uteis.DataBaseUtil;
import TimeLeads.uteis.DataBaseUtil;

public class Curso_AlunoRepository {

    DataBaseUtil databaseUtil;

    public Curso_AlunoRepository(Context context) { databaseUtil = new DataBaseUtil(context);}

    //insert
    public void salvar(Curso_AlunoModel cursoModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("periodo", cursoModel.getPeriodo());
        contentValues.put("horas_aluno", cursoModel.getHoras_aluno());
        contentValues.put("status", cursoModel.getStatus());
        contentValues.put("aluno_id", cursoModel.getAluno_id());
        contentValues.put("curso_id", cursoModel.getCurso_id());

        databaseUtil.GetConexaoDataBase().insert("CURSO_ALUNO", null, contentValues);


    }

    @SuppressLint("Range")
    public List<Curso_AlunoModel> ListarCurso_Alunos(){

        ArrayList cursos = new ArrayList();
        StringBuilder stringBuilderListCurso_Aluno = new StringBuilder();
        stringBuilderListCurso_Aluno.append("Select * from CURSO_ALUNO ");
        stringBuilderListCurso_Aluno.append("order by id_curso_aluno");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListCurso_Aluno.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Curso_AlunoModel cursoModel = new Curso_AlunoModel();
            cursoModel.setId(cursor.getInt(cursor.getColumnIndex("id_curso_aluno")));
            cursoModel.setPeriodo(cursor.getString(cursor.getColumnIndex("periodo")));
            cursoModel.setHoras_aluno(cursor.getString(cursor.getColumnIndex("horas_aluno")));
            cursoModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            cursoModel.setAluno_id(cursor.getInt(cursor.getColumnIndex("aluno_id")));
            cursoModel.setCurso_id(cursor.getInt(cursor.getColumnIndex("curso_id")));

            cursos.add(cursoModel);
            cursor.moveToNext();
        }
        return cursos;
    }

    public Integer excluir(int codigo){
        return databaseUtil.GetConexaoDataBase().delete("CURSO", "id_curso = ?",
                new String[]{Integer.toString(codigo)});
    }

    @SuppressLint("Range")
    public Curso_AlunoModel GetCurso_Aluno(int num){
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(
                "select * from CURSO where id_curso = " + num, null);
        cursor.moveToFirst();
        Curso_AlunoModel cursoModel = new Curso_AlunoModel();
        cursoModel.setId(cursor.getInt(cursor.getColumnIndex("id_curso_aluno")));
        cursoModel.setPeriodo(cursor.getString(cursor.getColumnIndex("periodo")));
        cursoModel.setHoras_aluno(cursor.getString(cursor.getColumnIndex("horas_aluno")));
        cursoModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));
        cursoModel.setAluno_id(cursor.getInt(cursor.getColumnIndex("aluno_id")));
        cursoModel.setCurso_id(cursor.getInt(cursor.getColumnIndex("curso_id")));
        return cursoModel;
    }

    public void editar(Curso_AlunoModel cursoModel){

        ContentValues contentValues = new ContentValues();
        contentValues.put("periodo", cursoModel.getPeriodo());
        contentValues.put("horas_aluno", cursoModel.getHoras_aluno());
        contentValues.put("status", cursoModel.getStatus());
        contentValues.put("aluno_id", cursoModel.getAluno_id());
        contentValues.put("curso_id", cursoModel.getCurso_id());


        databaseUtil.GetConexaoDataBase().update("CURSO", contentValues, "id_curso = ?",
                new String[]{Integer.toString(cursoModel.getId())});
    }
}
