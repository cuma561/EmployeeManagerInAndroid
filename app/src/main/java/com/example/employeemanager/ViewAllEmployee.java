package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllEmployee extends AppCompatActivity {


    List<Employee> employeeList;
    EmployeeDBManager db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_employee);

        db = new EmployeeDBManager(this);
        employeeList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewEmployees);



        loadEmployeeFromDatabase();
    }

    private void loadEmployeeFromDatabase(){
        Cursor cursor = db.getAllEmployees();
        if(cursor.moveToFirst()){
            do{
                employeeList.add(new Employee(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));
            }while (cursor.moveToNext());
            EmployeeAdapter adapter = new EmployeeAdapter(this, R.layout.list_layout_employee, employeeList, db);
            listView.setAdapter(adapter);
        }
    }

}
