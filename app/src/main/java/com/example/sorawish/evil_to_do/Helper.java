package com.example.sorawish.evil_to_do;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sorawish on 6/5/2017.
 */

public class Helper extends SQLiteOpenHelper {
    public static final String dbTable="task";
    public static final String dbColumn="taskName";
    private static final String dbName="dbName";
    private static final int dbVer=1;

    public Helper(Context context) {
        super(context, dbName, null, dbVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL",dbTable,dbColumn);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = String.format("DELETE TABLE IF EXISTS %s",dbTable);
        db.execSQL(query);
        onCreate(db);
    }

    public void insertNewTask(String task){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbColumn,task);
        db.insertWithOnConflict(dbTable,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(dbTable,dbColumn+" = ?",new String[]{task});
        db.close();
    }
}
