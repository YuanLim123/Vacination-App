package com.example.vaccination_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "vaccine.db";
    private static final int DATABASE_VERSION = 1;

    //usertable
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";


    //userinfotable
    public static final String TABLE_NAME2 = "userinfo";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IC = "ic";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_VACCINE = "vaccine";

    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createuser = "CREATE TABLE " + TABLE_NAME + " (\n" +
                "   " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT user_pk PRIMARY KEY AUTOINCREMENT ,\n" +
                "   " + COLUMN_USERNAME + " TEXT NOT NULL,\n" +
                "   " + COLUMN_PASSWORD + " TEXT NOT NULL\n" +
                ");";
        db.execSQL(createuser);

        String createuserinfo = "CREATE TABLE " + TABLE_NAME2 + " (\n" +
                "   " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT user_pk PRIMARY KEY AUTOINCREMENT ,\n" +
                "   " + COLUMN_NAME + " TEXT NOT NULL,\n" +
                "   " + COLUMN_IC + " TEXT NOT NULL,\n" +
                "   " + COLUMN_PHONE + " TEXT NOT NULL,\n" +
                "   " + COLUMN_VACCINE + " TEXT NOT NULL\n" +
                ");";
        db.execSQL(createuserinfo);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);

        String sql2 = "DROP TABLE IF EXISTS " + TABLE_NAME2 + ";";
        db.execSQL(sql2);
        onCreate(db);
    }

    // insert a user record
    public boolean insert(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert("user", null, contentValues);
        if (result == -1)
            return false; // not successful
        else
            return true; // record inserted successfully

    }

    public boolean insertbooking(String name, String ic, String phone, String vaccine){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("IC", ic);
        contentValues.put("phone", phone);
        contentValues.put("vaccine", vaccine);
        long result = db.insert("userinfo", null, contentValues);
        if (result == -1)
            return false; // not successful
        else
            return true; // record inserted successfully
    }

    Cursor getAllUsers(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
    }

    public boolean checkUsername (String username){
        SQLiteDatabase db = this.getReadableDatabase();
        String checkusername = "SELECT * FROM user WHERE username = ?";
        Cursor cursor = db.rawQuery(checkusername, new String[] {username});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String checklogin = "SELECT * FROM user WHERE username=? AND password=?";
        Cursor cursor = db.rawQuery(checklogin, new String[] {username, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkBook(String ic){
        SQLiteDatabase db = this.getWritableDatabase();
        String checkbook = "SELECT * FROM userinfo WHERE ic = ?";
        Cursor cursor = db.rawQuery(checkbook, new String[] {ic});

        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    boolean updateUser(int id, String name, String ic, String phone, String vaccine) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_IC, ic);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_VACCINE, vaccine);
        return db.update(TABLE_NAME2, contentValues, COLUMN_ID + "=?", new String[] {String.valueOf(id)}) == 1;
    }

    boolean deleteUser(int id){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME2, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }

    public Cursor readInfo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM userinfo WHERE id=?", null);
        return cursor;
    }
}
