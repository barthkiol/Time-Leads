package TimeLeads.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import TimeLeads.model.CursoModel;
import TimeLeads.uteis.DataBaseUtil;
import TimeLeads.uteis.DataBaseUtil;

public class CursoRepository {

    DataBaseUtil databaseUtil;

    public CursoRepository(Context context) { databaseUtil = new DataBaseUtil(context);}

    //insert
    public void salvar(CursoModel cursoModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", cursoModel.getNome());
        contentValues.put("horas_neces", cursoModel.getHoras_neces());
        contentValues.put("periodos", cursoModel.getPeriodos());
        contentValues.put("coordenador_id", cursoModel.getCoordenador_id());


        databaseUtil.GetConexaoDataBase().insert("CURSO", null, contentValues);


    }

    @SuppressLint("Range")
    public List<CursoModel> ListarCursos(){

        ArrayList cursos = new ArrayList();
        StringBuilder stringBuilderListCurso = new StringBuilder();
        stringBuilderListCurso.append("Select * from CURSO ");
        stringBuilderListCurso.append("order by id_curso");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListCurso.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            CursoModel cursoModel = new CursoModel();
            cursoModel.setId(cursor.getInt(cursor.getColumnIndex("id_curso")));
            cursoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cursoModel.setHoras_neces(cursor.getString(cursor.getColumnIndex("horas_neces")));
            cursoModel.setPeriodos(cursor.getString(cursor.getColumnIndex("periodos")));
            cursoModel.setCoordenador_id(cursor.getInt(cursor.getColumnIndex("coordenador_id")));

            cursos.add(cursoModel);
            cursor.moveToNext();
        }
        return cursos;
    }


    @SuppressLint("Range")
    public List<CursoModel> ListarCursosCoordenador(int coordenador){

        ArrayList cursos = new ArrayList();
        StringBuilder stringBuilderListCurso = new StringBuilder();
        stringBuilderListCurso.append("Select * from CURSO where coordenador_id = " + coordenador);

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListCurso.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            CursoModel cursoModel = new CursoModel();
            cursoModel.setId(cursor.getInt(cursor.getColumnIndex("id_curso")));
            cursoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            cursoModel.setHoras_neces(cursor.getString(cursor.getColumnIndex("horas_neces")));
            cursoModel.setPeriodos(cursor.getString(cursor.getColumnIndex("periodos")));
            cursoModel.setCoordenador_id(cursor.getInt(cursor.getColumnIndex("coordenador_id")));

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
    public CursoModel GetCurso(int num){
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(
                "select * from CURSO where id_curso = " + num, null);
        cursor.moveToFirst();
        CursoModel cursoModel = new CursoModel();
        cursoModel.setId(cursor.getInt(cursor.getColumnIndex("id_curso")));
        cursoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        cursoModel.setHoras_neces(cursor.getString(cursor.getColumnIndex("horas_neces")));
        cursoModel.setPeriodos(cursor.getString(cursor.getColumnIndex("periodos")));
        cursoModel.setCoordenador_id(cursor.getInt(cursor.getColumnIndex("coordenador_id")));

        return cursoModel;
    }

    public void editar(CursoModel cursoModel){

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", cursoModel.getNome());
        contentValues.put("horas_neces", cursoModel.getHoras_neces());
        contentValues.put("periodos", cursoModel.getPeriodos());
        contentValues.put("coordenador_id", cursoModel.getCoordenador_id());


        databaseUtil.GetConexaoDataBase().update("CURSO", contentValues, "id_curso = ?",
                new String[]{Integer.toString(cursoModel.getId())});
    }
}
