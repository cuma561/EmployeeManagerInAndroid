package com.example.employeemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeMenu extends AppCompatActivity {

    Button btnAdd,btnViewAll,btnLogOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_menu);

        btnAdd = (Button)findViewById(R.id.btnAddEmployee);
        btnAdd.setOnClickListener(myGoToAddEmployee);

        btnViewAll = (Button)findViewById(R.id.btnViewAllEmployee);
        btnViewAll.setOnClickListener(myGoToViewAllEmployee);

        btnLogOut = (Button)findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(myLogOut);


    }

    private View.OnClickListener myGoToAddEmployee = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GoToAddEmployee();
        }
    };

    public void GoToAddEmployee(){
        Intent i = new Intent(EmployeeMenu.this,AddEmployee.class);
        startActivity(i);
    }

    private View.OnClickListener myGoToViewAllEmployee = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GoToViewAllEmployee();
        }
    };

    public void GoToViewAllEmployee(){
        Intent i = new Intent(EmployeeMenu.this,ViewAllEmployee.class);
        startActivity(i);
    }

    private View.OnClickListener myLogOut = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LogOut();
        }
    };

    public void LogOut(){
        Intent i = new Intent(EmployeeMenu.this,MainActivity.class);
        startActivity(i);
        finish();
        Toast.makeText(getApplicationContext(),"Logout is Successfully",Toast.LENGTH_LONG).show();
    }

}
