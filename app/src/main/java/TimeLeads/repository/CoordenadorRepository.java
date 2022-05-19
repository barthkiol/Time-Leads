package TimeLeads.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import TimeLeads.model.CoordenadorModel;
import TimeLeads.uteis.DataBaseUtil;

public class CoordenadorRepository {

    DataBaseUtil databaseUtil;

    public CoordenadorRepository(Context context) { databaseUtil = new DataBaseUtil(context);}

    //insert coordenador
    public void salvar(CoordenadorModel coordenadorModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", coordenadorModel.getNome());
        contentValues.put("email", coordenadorModel.getEmail());
        contentValues.put("senha", coordenadorModel.getSenha());
        contentValues.put("cpf", coordenadorModel.getCpf());


        databaseUtil.GetConexaoDataBase().insert("COORDENADOR", null, contentValues);


    }

    @SuppressLint("Range")
    public List<CoordenadorModel> ListarCoordenadors(){

        ArrayList coordenadors = new ArrayList();
        StringBuilder stringBuilderListCoordenador = new StringBuilder();
        stringBuilderListCoordenador.append("Select * from COORDENADOR ");
        stringBuilderListCoordenador.append("order by id_coordenador");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListCoordenador.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            CoordenadorModel coordenadorModel = new CoordenadorModel();
            coordenadorModel.setId(cursor.getInt(cursor.getColumnIndex("id_coordenador")));
            coordenadorModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            coordenadorModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            coordenadorModel.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            coordenadorModel.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));

            coordenadors.add(coordenadorModel);
            cursor.moveToNext();
        }
        return coordenadors;
    }

    public Integer excluir(int codigo){
        return databaseUtil.GetConexaoDataBase().delete("COORDENADOR", "id_coordenador = ?",
                new String[]{Integer.toString(codigo)});
    }

    @SuppressLint("Range")
    public CoordenadorModel GetCoordenador(int num){
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(
                "select * from COORDENADOR where id_coordenador = " + num, null);
        cursor.moveToFirst();
        CoordenadorModel coordenadorModel = new CoordenadorModel();
        coordenadorModel.setId(cursor.getInt(cursor.getColumnIndex("id_coordenador")));
        coordenadorModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        coordenadorModel.setEmail(cursor.getString(cursor.getColumnIndex("email")));
        coordenadorModel.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
        coordenadorModel.setCpf(cursor.getString(cursor.getColumnIndex("cpf")));

        return coordenadorModel;
    }

    public void editar(CoordenadorModel coordenador){

        ContentValues content = new ContentValues();
        content.put("nome", coordenador.getNome());
        content.put("email", coordenador.getEmail());
        content.put("senha", coordenador.getSenha());
        content.put("cpf", coordenador.getCpf());

        databaseUtil.GetConexaoDataBase().update("COORDENADOR", content, "id_coordenador = ?",
                new String[]{Integer.toString(coordenador.getId())});
    }
}
