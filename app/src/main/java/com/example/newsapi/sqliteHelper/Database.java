package com.example.newsapi.sqliteHelper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public  class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "users_db";
    private static final int DB_VERSION = 4;
    private static final String TABLE_NAME = "users";
    private static final String ID_COL = "id";
    private static final String EMAIL_COL = "email";
    private static final String NAME_COL = "name";
    private static final String MOBILE_COL = "mobile";
    private static final String PASSWORD_COL = "password";

    public Database(Context context)
    {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+ TABLE_NAME +" ("+ ID_COL+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +EMAIL_COL+" TEXT,"
                +NAME_COL+" TEXT,"
                +MOBILE_COL+" TEXT,"
                +PASSWORD_COL+" TEXT"+")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void register(String name,String mobile, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME_COL,name);
        values.put(MOBILE_COL,mobile);
        values.put(PASSWORD_COL,password);
        values.put(EMAIL_COL,email);
        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public boolean login(String mobile, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        @SuppressLint("Recycle") Cursor cursor= db.rawQuery("select * from users where mobile = ? and password = ?", new String[]{mobile,password});
        return cursor.getCount() > 0;
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

}