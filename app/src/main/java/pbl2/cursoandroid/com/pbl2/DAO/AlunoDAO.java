package pbl2.cursoandroid.com.pbl2.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pbl2.cursoandroid.com.pbl2.modelo.Aluno;

/**
 * Created by keciosantos on 11/12/17.
 */

public class AlunoDAO extends SQLiteOpenHelper {
    private static final String DATABASE = "BancoPBL";
    private static final int VERSAO = 1;
    private static final String TABELA = "Alunos";

    public AlunoDAO(Context ctx) {
        super(ctx, DATABASE, null, VERSAO);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql =  "CREATE TABLE IF NOT EXISTS "+TABELA +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR," +
                "ano DATE, " +
                "semestre INT, " +
                "participacao"+
                "presenca"+
                "comprometimento"+
                "idturma INTEGER ," +
                "FOREIGN KEY(idturma) REFERENCES turma(idturma))";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS" + TABELA;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public void insere(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome",aluno.getNome());
        values.put("ano", String.valueOf(aluno.getAno()));
        values.put("semestre",aluno.getSemestre());
        values.put("participacao",aluno.getParticipacao());
        values.put("presenca",aluno.getPresenca());
        values.put("comprometimento",aluno.getComprometimento());

        getWritableDatabase().insert(TABELA,null,values);

    }
    public List<Aluno> getLista(){
        ArrayList<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM" + TABELA + ";";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        while (cursor.moveToNext()){
            Aluno aluno = new Aluno();
            aluno.setId(cursor.getColumnIndex("id"));
            aluno.setNome(String.valueOf(cursor.getColumnIndex("nome")));
            aluno.setAno(String.valueOf(cursor.getColumnIndex("ano")));
            aluno.setSemestre(cursor.getColumnIndex("semestre"));
            aluno.setParticipacao(cursor.getColumnIndex("participacao"));
            aluno.setPresenca(cursor.getColumnIndex("presenca"));
            aluno.setComprometimento(cursor.getColumnIndex("comprometimento"));
            aluno.setIdTurma(cursor.getColumnIndex("idTurma"));
            alunos.add(aluno);
        }
        return alunos;
    }

    public void deletar(Aluno aluno){
        String[] args = {""+ aluno.getId()};
        getWritableDatabase().delete(TABELA,"id=?",args);
    }
    public void atualizar(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome",aluno.getNome());
        values.put("ano", String.valueOf(aluno.getAno()));
        values.put("semestre",aluno.getSemestre());
        values.put("participacao",aluno.getParticipacao());
        values.put("presenca",aluno.getPresenca());
        values.put("comprometimento",aluno.getComprometimento());
        String args[] = {""+aluno.getId()};
        getWritableDatabase().update("Alunos", values,"id=?", args);

    }

}
