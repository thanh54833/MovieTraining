package com.example.thanh.movietraining.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by chien on 4/8/17.
 */

public class DBLike extends SQLiteOpenHelper {
    private final String TAG = "DBManager";
    private static final String DATABASE_NAME = "Likes_manager";
    private static final String TABLE_NAME = "Likes";
    private static int VERSION = 1;


    private static final String like = "like";
    private static final String id = "id";


    private Context context;

    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" + id + " TEXT primary key, " + like + "TEXT)";

    public DBLike(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
        Log.d(TAG, "DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLQuery);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade: ");
    }

    public void addLike(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(id,id);
        values.put(like,"true");

        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d(TAG, "add Account Successfuly");
    }

    public ArrayList<String> getAllLike() {

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<String> arrayList=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                arrayList.add (cursor.getString(0));
            } while (cursor.moveToNext());
        }
        db.close();
        return arrayList;
    }

    public int deleteStudent() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }


}
