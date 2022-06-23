package TimeLeads.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import TimeLeads.model.ChamadoModel;
import TimeLeads.uteis.DataBaseUtil;
import TimeLeads.uteis.DataBaseUtil;

public class ChamadoRepository {

    DataBaseUtil databaseUtil;

    public ChamadoRepository(Context context) { databaseUtil = new DataBaseUtil(context);}

    //insert
    public void salvar(ChamadoModel chamadoModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", chamadoModel.getTitulo());
        contentValues.put("descricao", chamadoModel.getDescricao());
        contentValues.put("data_envio", chamadoModel.getData_envio());
        contentValues.put("arquivo", chamadoModel.getArquivo());
        contentValues.put("status", chamadoModel.getStatus());
        contentValues.put("aluno_id", chamadoModel.getAluno_id());


        databaseUtil.GetConexaoDataBase().insert("CHAMADO", null, contentValues);


    }

    @SuppressLint("Range")
    public List<ChamadoModel> ListarChamados(){

        ArrayList chamados = new ArrayList();
        StringBuilder stringBuilderListChamado = new StringBuilder();
        stringBuilderListChamado.append("Select * from CHAMADO ");
        stringBuilderListChamado.append("order by id_chamado");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListChamado.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ChamadoModel chamadoModel = new ChamadoModel();
            chamadoModel.setId(cursor.getInt(cursor.getColumnIndex("id_chamado")));
            chamadoModel.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            chamadoModel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            chamadoModel.setData_envio(cursor.getString(cursor.getColumnIndex("data_envio")));
            chamadoModel.setArquivo(cursor.getString(cursor.getColumnIndex("arquivo")));
            chamadoModel.setAluno_id(cursor.getInt(cursor.getColumnIndex("aluno_id")));
            chamadoModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));

            chamados.add(chamadoModel);
            cursor.moveToNext();
        }
        return chamados;
    }


    @SuppressLint("Range")
    public List<ChamadoModel> ListarChamadosAbertos(){

        ArrayList chamados = new ArrayList();
        StringBuilder stringBuilderListChamado = new StringBuilder();
        stringBuilderListChamado.append("Select * from CHAMADO where STATUS = 'ABERTO' ");
        stringBuilderListChamado.append("order by id_chamado");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListChamado.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ChamadoModel chamadoModel = new ChamadoModel();
            chamadoModel.setId(cursor.getInt(cursor.getColumnIndex("id_chamado")));
            chamadoModel.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            chamadoModel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            chamadoModel.setData_envio(cursor.getString(cursor.getColumnIndex("data_envio")));
            chamadoModel.setArquivo(cursor.getString(cursor.getColumnIndex("arquivo")));
            chamadoModel.setAluno_id(cursor.getInt(cursor.getColumnIndex("aluno_id")));
            chamadoModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));

            chamados.add(chamadoModel);
            cursor.moveToNext();
        }
        return chamados;
    }

    @SuppressLint("Range")
    public List<ChamadoModel> ListarChamadosAluno(int aluno){

        ArrayList chamados = new ArrayList();
        StringBuilder stringBuilderListChamado = new StringBuilder();
        stringBuilderListChamado.append("Select * from CHAMADO where (aluno_id = " + aluno + " ) ");
        stringBuilderListChamado.append(" AND (CHAMADO.status like 'RECUSADO' OR CHAMADO.status like 'ABERTO') order by id_chamado");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListChamado.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            ChamadoModel chamadoModel = new ChamadoModel();
            chamadoModel.setId(cursor.getInt(cursor.getColumnIndex("id_chamado")));
            chamadoModel.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            chamadoModel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            chamadoModel.setData_envio(cursor.getString(cursor.getColumnIndex("data_envio")));
            chamadoModel.setArquivo(cursor.getString(cursor.getColumnIndex("arquivo")));
            chamadoModel.setAluno_id(cursor.getInt(cursor.getColumnIndex("aluno_id")));
            chamadoModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));

            chamados.add(chamadoModel);
            cursor.moveToNext();
        }
        return chamados;
    }

    public Integer excluir(int codigo){
        return databaseUtil.GetConexaoDataBase().delete("CHAMADO", "id_chamado = ?",
                new String[]{Integer.toString(codigo)});
    }

    @SuppressLint("Range")
    public ChamadoModel GetChamado(int num){
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(
                "select * from CHAMADO where id_chamado = " + num, null);
        cursor.moveToFirst();
        ChamadoModel chamadoModel = new ChamadoModel();
        chamadoModel.setId(cursor.getInt(cursor.getColumnIndex("id_chamado")));
        chamadoModel.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
        chamadoModel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        chamadoModel.setData_envio(cursor.getString(cursor.getColumnIndex("data_envio")));
        chamadoModel.setArquivo(cursor.getString(cursor.getColumnIndex("arquivo")));
        chamadoModel.setAluno_id(cursor.getInt(cursor.getColumnIndex("aluno_id")));
        chamadoModel.setStatus(cursor.getString(cursor.getColumnIndex("status")));

        return chamadoModel;
    }

    public void editar(ChamadoModel chamado){

        ContentValues content = new ContentValues();
        content.put("titulo", chamado.getTitulo());
        content.put("descricao", chamado.getDescricao());
        content.put("data_envio", chamado.getData_envio());
        content.put("arquivo", chamado.getArquivo());
        content.put("status", chamado.getStatus());
        content.put("aluno_id", chamado.getAluno_id());

        databaseUtil.GetConexaoDataBase().update("CHAMADO", content, "id_chamado = ?",
                new String[]{Integer.toString(chamado.getId())});
    }
}
