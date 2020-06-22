package com.example.employeemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LRDBManager extends SQLiteOpenHelper {

    public LRDBManager(Context context) {
        super(context, "EmployeeManager.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(email text primary key,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String email,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email",email);
        cv.put("password",password);
        long insert = db.insert("user",null,cv);
        if(insert == -1) return false;
        else return true;
    }

    public Boolean chkemial(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from user where email=?",new String[]{email});
        if(c.getCount()> 0){
            return false;
        }else{
            return true;
        }
    }

    public Boolean emailpassword(String email,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from user where email=? and password=?",new String[]{email,password});
        if(c.getCount() > 0) return true;
        else return false;
    }

}
