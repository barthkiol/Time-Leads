package TimeLeads.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import TimeLeads.model.AlunoModel;
import TimeLeads.uteis.DataBaseUtil;
import TimeLeads.uteis.DataBaseUtil;

public class AlunoRepository {

    DataBaseUtil databaseUtil;

    public AlunoRepository(Context context) { databaseUtil = new DataBaseUtil(context);}

    //insert
    public void salvar(AlunoModel alunoModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", alunoModel.getNome());
        contentValues.put("data_nasc", alunoModel.getData_nasc());
        contentValues.put("cpf", alunoModel.getCPF());
        contentValues.put("email", alunoModel.getEmail());
        contentValues.put("senha", alunoModel.getSenha());
        contentValues.put("foto", alunoModel.getFoto());
        contentValues.put("matricula", alunoModel.getMatricula());

        databaseUtil.GetConexaoDataBase().insert("ALUNO", null, contentValues);


    }

    @SuppressLint("Range")
    public List<AlunoModel> ListarAlunos(){

        ArrayList alunos = new ArrayList();
        StringBuilder stringBuilderListAluno = new StringBuilder();
        stringBuilderListAluno.append("Select * from ALUNO ");
        stringBuilderListAluno.append("order by nome");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListAluno.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            AlunoModel alunoModel = new AlunoModel();
            alunoModel.setId(cursor.getInt(cursor.getColumnIndex("id_aluno")));
            alunoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            alunoModel.setData_nasc(cursor.getString(cursor.getColumnIndex("data_nasc")));
            alunoModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            alunoModel.setCPF(cursor.getString(cursor.getColumnIndex("cpf")));
            alunoModel.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            alunoModel.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
            alunoModel.setMatricula(cursor.getString(cursor.getColumnIndex("matricula")));

            alunos.add(alunoModel);
            cursor.moveToNext();
        }
        return alunos;
    }


    @SuppressLint("Range")
    public List<AlunoModel> ListarAlunosCoordenador(int curso){

        ArrayList alunos = new ArrayList();
        StringBuilder stringBuilderListAluno = new StringBuilder();
        stringBuilderListAluno.append("Select ALUNO.id_aluno, ALUNO.nome, ALUNO.cpf, ALUNO.data_nasc, ALUNO.email, ALUNO.foto, ALUNO.matricula, ALUNO.senha from ALUNO INNER JOIN CURSO_ALUNO ON ALUNO.id_aluno = CURSO_ALUNO.aluno_id INNER JOIN CURSO ON CURSO_ALUNO.curso_id = CURSO.id_curso ");
        stringBuilderListAluno.append(" where CURSO.id_curso = " + curso);
        stringBuilderListAluno.append(" order by ALUNO.nome");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListAluno.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            AlunoModel alunoModel = new AlunoModel();
            alunoModel.setId(cursor.getInt(cursor.getColumnIndex("id_aluno")));
            alunoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            alunoModel.setData_nasc(cursor.getString(cursor.getColumnIndex("data_nasc")));
            alunoModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            alunoModel.setCPF(cursor.getString(cursor.getColumnIndex("cpf")));
            alunoModel.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            alunoModel.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
            alunoModel.setMatricula(cursor.getString(cursor.getColumnIndex("matricula")));

            alunos.add(alunoModel);
            cursor.moveToNext();
        }
        return alunos;
    }

    public Integer excluir(int codigo){
        return databaseUtil.GetConexaoDataBase().delete("ALUNO", "id_aluno = ?",
                new String[]{Integer.toString(codigo)});
    }

    @SuppressLint("Range")
    public AlunoModel GetAluno(int num){
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(
                "select * from ALUNO where id_aluno = " + num, null);
        cursor.moveToFirst();
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setId(cursor.getInt(cursor.getColumnIndex("id_aluno")));
        alunoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        alunoModel.setData_nasc(cursor.getString(cursor.getColumnIndex("data_nasc")));
        alunoModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        alunoModel.setCPF(cursor.getString(cursor.getColumnIndex("cpf")));
        alunoModel.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        alunoModel.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
        alunoModel.setMatricula(cursor.getString(cursor.getColumnIndex("matricula")));;

        return alunoModel;
    }


    @SuppressLint("Range")
    public AlunoModel GetAlunoLogin(String email, String senha){
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(
                "select * from ALUNO where email = '" + email + "' and senha = '" + senha + "' ", null);
        cursor.moveToFirst();
        if(cursor.moveToFirst() == true) {
            AlunoModel alunoModel = new AlunoModel();
            alunoModel.setId(cursor.getInt(cursor.getColumnIndex("id_aluno")));
            alunoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            alunoModel.setData_nasc(cursor.getString(cursor.getColumnIndex("data_nasc")));
            alunoModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            alunoModel.setCPF(cursor.getString(cursor.getColumnIndex("cpf")));
            alunoModel.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            alunoModel.setFoto(cursor.getString(cursor.getColumnIndex("foto")));
            alunoModel.setMatricula(cursor.getString(cursor.getColumnIndex("matricula")));
            return alunoModel;
        }else{
            return null;
        }

    }

    public void editar(AlunoModel aluno){

        ContentValues content = new ContentValues();
        content.put("nome", aluno.getNome());
        content.put("data_nasc", aluno.getData_nasc());
        content.put("email", aluno.getEmail());
        content.put("cpf", aluno.getCPF());
        content.put("senha", aluno.getSenha());
        content.put("foto", aluno.getFoto());
        content.put("matricula", aluno.getMatricula());

        databaseUtil.GetConexaoDataBase().update("ALUNO", content, "id_aluno = ?",
                new String[]{Integer.toString(aluno.getId())});
    }
}
