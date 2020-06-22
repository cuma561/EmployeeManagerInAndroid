package com.example.employeemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EmployeeDBManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Employees.db";
    public static final String TABLE_NAME = "employee";
    public static final String COLUMN_ID = "empId";
    public static final String COLUMN_FIRST_NAME = "firstname";
    public static final String COLUMN_LAST_NAME = "lastname";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_DEPT= "dept";
    public static final String COLUMN_SALARY = "salary";

    public EmployeeDBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (\n" +
                "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + COLUMN_FIRST_NAME + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_LAST_NAME + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_GENDER + " TEXT NOT NULL,\n" +
                "    " + COLUMN_DEPT + " varchar(200) NOT NULL,\n" +
                "    " + COLUMN_SALARY + " double NOT NULL\n" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists " + TABLE_NAME);
    }

    public Employee addEmployee(Employee e) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, e.getFirstname());
        contentValues.put(COLUMN_LAST_NAME,e.getLastname());
        contentValues.put(COLUMN_GENDER,e.getGender());
        contentValues.put(COLUMN_DEPT, e.getDepartments());
        contentValues.put(COLUMN_SALARY, e.getSalary());
        SQLiteDatabase db = getWritableDatabase();
        long insertid = db.insert(TABLE_NAME,null,contentValues);
        e.setEmpid(insertid);
        return e;
    }

    Cursor getAllEmployees() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    boolean updateEmployee(long id,String firstname, String lastname,String gender, String dept, double salary) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRST_NAME, firstname);
        contentValues.put(COLUMN_LAST_NAME,lastname);
        contentValues.put(COLUMN_GENDER,gender);
        contentValues.put(COLUMN_DEPT, dept);
        contentValues.put(COLUMN_SALARY, salary);
        return db.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }

    boolean deleteEmployee(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) == 1;
    }
}
