package TimeLeads.uteis;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseUtil extends SQLiteOpenHelper {

    private static final String NOME_BASE_DE_DADOS = "TIMELEADSHLG.dB";

    private static final int VERSAO_BASE_DE_DADOS = 1;

    public DataBaseUtil(Context context){
        super(context, NOME_BASE_DE_DADOS, null, VERSAO_BASE_DE_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder stringBuilderCreateTable = new StringBuilder();

        stringBuilderCreateTable.append("Create Table EVENTO(");
        stringBuilderCreateTable.append("id_evento Integer Primary Key Autoincrement, ");
        stringBuilderCreateTable.append("titulo Text not null, ");
        stringBuilderCreateTable.append("descricao Text not null, ");
        stringBuilderCreateTable.append("horas_validas Text not null, ");
        stringBuilderCreateTable.append("data  Text not null, ");
        stringBuilderCreateTable.append("imagem BLOB not null)");

        db.execSQL(stringBuilderCreateTable.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("Drop table if exists EVENTO");
        onCreate(db);
    }

    //executa a classec da rotina de banco de dados
    public SQLiteDatabase GetConexaoDataBase(){
        return this.getWritableDatabase();
    }
}