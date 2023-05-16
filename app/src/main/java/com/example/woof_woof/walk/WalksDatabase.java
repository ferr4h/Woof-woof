package com.example.woof_woof.walk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class WalksDatabase extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME="Walks.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="walks_list";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_DATE="wk_date";
    private static final String COLUMN_START="wk_start";
    private static final String COLUMN_END="wk_end";
    private static final String COLUMN_DURATION="wk_duration";


    public WalksDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_START + " TEXT, " +
                COLUMN_END + " TEXT, " +
                COLUMN_DURATION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addWalk(String date, String start, String end, String duration){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_START, start);
        cv.put(COLUMN_END, end);
        cv.put(COLUMN_DURATION, duration);
        long result = db.insert(TABLE_NAME, null,  cv);
        if (result == -1){
            Toast.makeText(context, "Не удалось добавить выгул", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Выгул добавлен", Toast.LENGTH_SHORT).show();
        }
    }
    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor =null;
        if (db!=null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Не получилось удалить выгул", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Выгул удален", Toast.LENGTH_SHORT).show();
        }
    }
}
