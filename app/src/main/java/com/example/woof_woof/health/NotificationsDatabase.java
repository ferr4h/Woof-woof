package com.example.woof_woof.health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NotificationsDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="Notifications.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="notifications_list";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_TYPE="nt_type";
    private static final String COLUMN_DATE="nt_date";
    private static final String COLUMN_TIME="nt_time";
    private static final String COLUMN_DESCRIPTION="nt_description";


    public NotificationsDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TYPE + " TEXT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_TIME + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addNotification(String type, String date, String time, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_NAME, null,  cv);
        if (result == -1){
            Toast.makeText(context, "Не удалось сохранить напоминание", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Напоминание сохранено", Toast.LENGTH_SHORT).show();
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
    void updateData(String row_id, String type, String date, String time, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TYPE, type);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Не удалось обновить данные", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Данные успешно обновлены", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Не получилось удалить напоминание", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Напоминание удалено", Toast.LENGTH_SHORT).show();
        }
    }
}
