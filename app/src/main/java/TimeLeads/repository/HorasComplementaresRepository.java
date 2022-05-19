package TimeLeads.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import TimeLeads.model.HorasComplementaresModel;
import TimeLeads.uteis.DataBaseUtil;

public class HorasComplementaresRepository {

    DataBaseUtil databaseUtil;

    public HorasComplementaresRepository(Context context) { databaseUtil = new DataBaseUtil(context);}

    //insert horasComplementares
    public void salvar(HorasComplementaresModel horasComplementaresModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", horasComplementaresModel.getNome());
        contentValues.put("horas", horasComplementaresModel.getHoras());
        contentValues.put("aluno_id", horasComplementaresModel.getAluno_id());



        databaseUtil.GetConexaoDataBase().insert("HORAS_COMPLEMENTARES", null, contentValues);


    }

    @SuppressLint("Range")
    public List<HorasComplementaresModel> ListarHorasComplementaress(){

        ArrayList horasComplementares = new ArrayList();
        StringBuilder stringBuilderListHorasComplementares = new StringBuilder();
        stringBuilderListHorasComplementares.append("Select * from HORAS_COMPLEMENTARES ");
        stringBuilderListHorasComplementares.append("order by id_horasComplementares");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListHorasComplementares.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            HorasComplementaresModel horasComplementaresModel = new HorasComplementaresModel();
            horasComplementaresModel.setId(cursor.getInt(cursor.getColumnIndex("id_horasComplementares")));
            horasComplementaresModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            horasComplementaresModel.setHoras(cursor.getString(cursor.getColumnIndex("horas")));
            horasComplementaresModel.setAluno_id(cursor.getInt(cursor.getColumnIndex("id_aluno")));

            horasComplementares.add(horasComplementaresModel);
            cursor.moveToNext();
        }
        return horasComplementares;
    }

    public Integer excluir(int codigo){
        return databaseUtil.GetConexaoDataBase().delete("HORAS_COMPLEMENTARES", "id_horasComplementares = ?",
                new String[]{Integer.toString(codigo)});
    }

    @SuppressLint("Range")
    public HorasComplementaresModel GetHorasComplementares(int num){
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(
                "select * from HORAS_COMPLEMENTARES where id_horasComplementares = " + num, null);
        cursor.moveToFirst();
        HorasComplementaresModel horasComplementaresModel = new HorasComplementaresModel();
        horasComplementaresModel.setId(cursor.getInt(cursor.getColumnIndex("id_horasComplementares")));
        horasComplementaresModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        horasComplementaresModel.setHoras(cursor.getString(cursor.getColumnIndex("horas")));
        horasComplementaresModel.setAluno_id(cursor.getInt(cursor.getColumnIndex("id_aluno")));
        return horasComplementaresModel;
    }

    public void editar(HorasComplementaresModel horasComplementares){

        ContentValues content = new ContentValues();
        content.put("nome", horasComplementares.getNome());
        content.put("horas", horasComplementares.getHoras());
        content.put("aluno_id", horasComplementares.getAluno_id());


        databaseUtil.GetConexaoDataBase().update("HORAS_COMPLEMENTARES", content, "id_horasComplementares = ?",
                new String[]{Integer.toString(horasComplementares.getId())});
    }
}
