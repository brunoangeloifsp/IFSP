package br.edu.ifsp.aulasqlite.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "DB_PESSOAS";
    private static final int DB_VERSION = 1;
    public static final String TB_PESSOA = "tb_pessoa";

    public DBOpenHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableSQL = "CREATE TABLE " + TB_PESSOA +
                "(_id integer primary key autoincrement," +
                "nome TEXT NOT NULL, " +
                "cpf TEXT, " +
                "email TEXT);";

        sqLiteDatabase.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //NSA: versão 1
    }
}
