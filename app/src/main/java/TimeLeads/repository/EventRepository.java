package TimeLeads.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import TimeLeads.model.EventModel;
import TimeLeads.uteis.DataBaseUtil;
import TimeLeads.uteis.DataBaseUtil;

public class EventRepository {

    DataBaseUtil databaseUtil;

    public EventRepository(Context context) { databaseUtil = new DataBaseUtil(context);}

    //insert
    public void salvar(EventModel eventModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", eventModel.getTitulo());
        contentValues.put("descricao", eventModel.getDescricao());
        contentValues.put("horas_validas", eventModel.getHoras_validas());
        contentValues.put("data", eventModel.getData());
        contentValues.put("imagem", eventModel.getImagem());

        databaseUtil.GetConexaoDataBase().insert("EVENTO", null, contentValues);


    }

    @SuppressLint("Range")
    public List<EventModel> ListarEventos(){

        ArrayList eventos = new ArrayList();
        StringBuilder stringBuilderListEvento = new StringBuilder();
        stringBuilderListEvento.append("Select * from Evento ");
        stringBuilderListEvento.append("order by titulo");

        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderListEvento.toString(), null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            EventModel eventModel = new EventModel();
            eventModel.setId(cursor.getInt(cursor.getColumnIndex("id_evento")));
            eventModel.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
            eventModel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            eventModel.setHoras_validas(cursor.getString(cursor.getColumnIndex("horas_validas")));
            eventModel.setData(cursor.getString(cursor.getColumnIndex("data")));
            eventModel.setImagem(cursor.getString(cursor.getColumnIndex("imagem")));
            eventos.add(eventModel);
            cursor.moveToNext();
        }
        return eventos;
    }

    public Integer excluir(int codigo){
        return databaseUtil.GetConexaoDataBase().delete("EVENTO", "id_evento = ?",
                new String[]{Integer.toString(codigo)});
    }

    @SuppressLint("Range")
    public EventModel GetEvento(int num){
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(
                "select * from EVENTO where id_evento = " + num, null);
        cursor.moveToFirst();
        EventModel eventModel = new EventModel();
        eventModel.setId(cursor.getInt(cursor.getColumnIndex("id_evento")));
        eventModel.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
        eventModel.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
        eventModel.setHoras_validas(cursor.getString(cursor.getColumnIndex("horas_validas")));
        eventModel.setData(cursor.getString(cursor.getColumnIndex("data")));
        eventModel.setImagem(cursor.getString(cursor.getColumnIndex("imagem")));

        return eventModel;
    }

    public void editar(EventModel evento){

        ContentValues content = new ContentValues();
        content.put("titulo", evento.getTitulo());
        content.put("descricao", evento.getDescricao());
        content.put("horas_validas", evento.getHoras_validas());
        content.put("data", evento.getData());
        content.put("imagem", evento.getImagem());

        databaseUtil.GetConexaoDataBase().update("EVENTO", content, "id_evento = ?",
                new String[]{Integer.toString(evento.getId())});
    }
}
