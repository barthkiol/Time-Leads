package TimeLeads.uteis;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseUtil extends SQLiteOpenHelper {

    private static final String NOME_BASE_DE_DADOS = "TIMELEADS_HLG.dB";

    private static final int VERSAO_BASE_DE_DADOS = 1;

    public DataBaseUtil(Context context){
        super(context, NOME_BASE_DE_DADOS, null, VERSAO_BASE_DE_DADOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Tabela de evento
        StringBuilder stringBuilderCreateTableEvento = new StringBuilder();

        stringBuilderCreateTableEvento.append("Create Table EVENTO(");
        stringBuilderCreateTableEvento.append("id_evento Integer Primary Key Autoincrement, ");
        stringBuilderCreateTableEvento.append("titulo Text not null, ");
        stringBuilderCreateTableEvento.append("descricao Text not null, ");
        stringBuilderCreateTableEvento.append("horas_validas Text not null, ");
        stringBuilderCreateTableEvento.append("data  Text not null, ");
        stringBuilderCreateTableEvento.append("imagem BLOB not null)");

        db.execSQL(stringBuilderCreateTableEvento.toString());
        /*
        //Tabela de aluno
        StringBuilder stringBuilderCreateTableAluno= new StringBuilder();

        stringBuilderCreateTableAluno.append("Create Table ALUNO(");
        stringBuilderCreateTableAluno.append("id_aluno Integer Primary Key Autoincrement, ");
        stringBuilderCreateTableAluno.append("nome Text not null, ");
        stringBuilderCreateTableAluno.append("data_nasc Text not null, ");
        stringBuilderCreateTableAluno.append("cpf Text not null, ");
        stringBuilderCreateTableAluno.append("email  Text not null, ");
        stringBuilderCreateTableAluno.append("senha Text not null, ");
        stringBuilderCreateTableAluno.append("foto BLOB not null, ");
        stringBuilderCreateTableAluno.append("matricula Text not null) ");

        db.execSQL(stringBuilderCreateTableAluno.toString());

        //Tabela de Chamado
        StringBuilder stringBuilderCreateTableChamado= new StringBuilder();

        stringBuilderCreateTableChamado.append("Create Table CHAMADO(");
        stringBuilderCreateTableChamado.append("id_chamado Integer Primary Key Autoincrement, ");
        stringBuilderCreateTableChamado.append("titulo Text not null, ");
        stringBuilderCreateTableChamado.append("data_envio Text not null, ");
        stringBuilderCreateTableChamado.append("arquivo BLOB not null, ");
        stringBuilderCreateTableChamado.append("descricao Text not null, ");
        stringBuilderCreateTableChamado.append("status Text not null, ");
        stringBuilderCreateTableChamado.append("aluno_id integer Constraint aluno_id REFERENCES ALUNO (id_aluno)) ");


        db.execSQL(stringBuilderCreateTableChamado.toString());

        //Tabela de Coordenador
        StringBuilder stringBuilderCreateTableCoordenador= new StringBuilder();

        stringBuilderCreateTableCoordenador.append("Create Table COORDENADOR(");
        stringBuilderCreateTableCoordenador.append("id_coordenador Integer Primary Key Autoincrement, ");
        stringBuilderCreateTableCoordenador.append("nome Text not null, ");
        stringBuilderCreateTableCoordenador.append("email Text not null, ");
        stringBuilderCreateTableCoordenador.append("senha Text not null, ");
        stringBuilderCreateTableCoordenador.append("cpf Text not null) ");


        db.execSQL(stringBuilderCreateTableCoordenador.toString());


        //Tabela de Curso
        StringBuilder stringBuilderCreateTableCurso= new StringBuilder();

        stringBuilderCreateTableCurso.append("Create Table CURSO(");
        stringBuilderCreateTableCurso.append("id_curso Integer Primary Key Autoincrement, ");
        stringBuilderCreateTableCurso.append("nome Text not null, ");
        stringBuilderCreateTableCurso.append("horas_neces Text not null, ");
        stringBuilderCreateTableCurso.append("periodos Text not null, ");
        stringBuilderCreateTableCurso.append("coordenador_id integer  Constraint  coordenador_id REFERENCES COORDENADOR (id_coordenador)) ");


        db.execSQL(stringBuilderCreateTableCurso.toString());

        //Tabela de Horas complementares
        StringBuilder stringBuilderCreateTableHorasComp= new StringBuilder();

        stringBuilderCreateTableHorasComp.append("Create Table HORAS_COMPLEMENTARES(");
        stringBuilderCreateTableHorasComp.append("id_hora Integer Primary Key Autoincrement, ");
        stringBuilderCreateTableHorasComp.append("nome Text not null, ");
        stringBuilderCreateTableHorasComp.append("horas Text not null, ");
        stringBuilderCreateTableHorasComp.append("aluno_id integer Constraint aluno_id REFERENCES ALUNO (id_aluno))  ");


        db.execSQL(stringBuilderCreateTableHorasComp.toString());


        //Tabela de Curso_ALuno
        StringBuilder stringBuilderCreateTableCursoAluno= new StringBuilder();

        stringBuilderCreateTableCursoAluno.append("Create Table CURSO_ALUNO(");
        stringBuilderCreateTableCursoAluno.append("id_curso_aluno Integer Primary Key Autoincrement, ");
        stringBuilderCreateTableCursoAluno.append("periodo Text not null, ");
        stringBuilderCreateTableCursoAluno.append("horas_aluno Text not null, ");
        stringBuilderCreateTableCursoAluno.append("status Text not null, ");
        stringBuilderCreateTableCursoAluno.append("aluno_id integer Constraint aluno_id REFERENCES ALUNO (id_aluno), ");
        stringBuilderCreateTableCursoAluno.append("curso_id integer Constraint curso_id REFERENCES CURSO (id_curso)) ");


        db.execSQL(stringBuilderCreateTableCursoAluno.toString());
        */

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