package br.edu.ifsp.aulasqlite.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.edu.ifsp.aulasqlite.model.database.DBOpenHelper;
import br.edu.ifsp.aulasqlite.model.entity.Pessoa;

public class DBSQLiteAccess {

    private DBOpenHelper dbHelper;
    private SQLiteDatabase db;

    public DBSQLiteAccess(Context context) {
        dbHelper = new DBOpenHelper(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        if(db != null) {
            db.close();
        }
    }

    public void deletePessoa(Pessoa p) {
        open();
        db.delete(DBOpenHelper.TB_PESSOA,
                "_id= " + p.getId(),null);
        close();
    }

    public void insertPessoa(Pessoa p) {
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", p.getNome());
        contentValues.put("cpf", p.getCpf());
        contentValues.put("email", p.getEmail());

        db.insert(DBOpenHelper.TB_PESSOA,null,contentValues);

        close();
    }

    public void updatePessoa(Pessoa p){
        open();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", p.getNome());
        contentValues.put("cpf", p.getCpf());
        contentValues.put("email", p.getEmail());

        db.update(DBOpenHelper.TB_PESSOA,
                contentValues, "_id= " + p.getId(), null);

        close();
    }

    public Pessoa SelectOnePessoa(int id) {
        Pessoa p = null;

        open();

        String[] columns = new String[]{"_id", "nome", "cpf", "email"};

        Cursor cursor =db.query(DBOpenHelper.TB_PESSOA,
                columns, "_id= " + id, null,
                null, null, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            int idPessoa = cursor.getInt(0);
            String nomePessoa = cursor.getString(1);
            String cpfPessoa = cursor.getString(2);
            String emailPessoa = cursor.getString(3);
            p = new Pessoa(idPessoa, nomePessoa, cpfPessoa, emailPessoa);
        }

        close();
        return p;
    }

    public ArrayList<Pessoa> selectAll() {
        ArrayList<Pessoa> arrPessoas = new ArrayList<Pessoa>();
        Pessoa p = null;

        open();

        String[] columns = new String[]{"_id", "nome", "cpf", "email"};

        Cursor cursor = db.query(DBOpenHelper.TB_PESSOA, columns,null,
                null, null, null, "nome ASC");

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                p = new Pessoa(
                        cursor.getInt(0), //_id
                        cursor.getString(1),//nome
                        cursor.getString(2),//cpf
                        cursor.getString(3)//email
                );
                arrPessoas.add(p);
            }
            while(cursor.moveToNext());
        }

        close();

        return arrPessoas;
    }
}
