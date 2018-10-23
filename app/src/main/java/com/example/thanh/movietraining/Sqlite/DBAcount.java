package com.example.thanh.movietraining.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.thanh.movietraining.model.LoginModel;
import com.example.thanh.movietraining.model.MovieModel;


public class DBAcount extends SQLiteOpenHelper {
    private final String TAG = "DBManager";
    private static final String DATABASE_NAME = "Logins_manager";
    private static final String TABLE_NAME = "Logins";
    private static int VERSION = 1;


    private static final String error = "error";
    private static final String code = "code";
    private static final String message = "message";
    private static final String id = "id";
    private static final String email = "email";
    private static final String password = "password";
    private static final String full_name = "full_name";
    private static final String gender = "gender";
    private static final String birthday = "birthday";
    private static final String facebook_id = "facebook_id";
    private static final String google_id = "google_id";
    private static final String access_token = "access_token";
    private static final String created_at = "created_at";
    private static final String updated_at = "updated_at";


    private Context context;

    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            id + " TEXT primary key, " +
            error + " TEXT, " +
            code + " TEXT, " +
            message + " TEXT, " +
            password + " TEXT, " +
            full_name + " TEXT, " +
            gender + " TEXT, " +
            birthday + " TEXT, " +
            facebook_id + " TEXT, " +
            google_id + " TEXT, " +
            access_token + " TEXT, " +
            created_at + " TEXT, " +
            updated_at + " TEXT, " +
            email + " TEXT)";

    public DBAcount(Context context) {
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

    public void addAccount(LoginModel logins) {

        deleteStudent();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(id, logins.data.getId());
        values.put(error, logins.getError());
        values.put(code, logins.getCode());
        values.put(message, logins.getMessage());
        values.put(password, logins.data.getPassword());
        values.put(full_name, logins.data.getFull_name());
        values.put(gender, logins.data.getGender());
        values.put(birthday, logins.data.getBirthday());
        values.put(facebook_id, logins.data.getFacebook_id());
        values.put(google_id, logins.data.getGoogle_id());
        values.put(access_token, logins.data.getAccess_token());
        values.put(created_at, logins.data.getCreated_at());
        values.put(updated_at, logins.data.getUpdated_at());
        values.put(email, logins.data.getEmail());

        db.insert(TABLE_NAME, null, values);
        db.close();

        Log.d(TAG, "add Account Successfuly");
    }

    public LoginModel getAccount() {

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        LoginModel logins = null;
        cursor.moveToFirst();

        LoginModel.Data data = new LoginModel.Data(cursor.getString(0), cursor.getString(7), cursor.getString(12), cursor.getString(13), cursor.getString(11), cursor.getString(9), cursor.getString(6), cursor.getString(8), cursor.getString(4), cursor.getString(10), cursor.getString(5));
        logins = new LoginModel(cursor.getString(3), cursor.getString(1), data, cursor.getString(2));

        db.close();
        return logins;
    }

    public int updateStudent(LoginModel logins) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(id, logins.data.getId());
        values.put(error, logins.getError());
        values.put(code, logins.getCode());
        values.put(message, logins.getMessage());
        values.put(password, logins.data.getPassword());
        values.put(full_name, logins.data.getFull_name());
        values.put(gender, logins.data.getGender());
        values.put(birthday, logins.data.getBirthday());
        values.put(facebook_id, logins.data.getFacebook_id());
        values.put(google_id, logins.data.getGoogle_id());
        values.put(access_token, logins.data.getAccess_token());
        values.put(created_at, logins.data.getCreated_at());
        values.put(updated_at, logins.data.getUpdated_at());
        values.put(email, logins.data.getEmail());

        return db.update(TABLE_NAME, values, id + "=?", new String[]{String.valueOf(logins.data.getId())});
    }

    public int deleteStudent() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }


}
