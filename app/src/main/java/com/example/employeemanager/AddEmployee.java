package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddEmployee extends AppCompatActivity {

    Button btnBack,btnAdd;
    EditText fname,lname,dept,salary;
    Employee newEmployee;
    EmployeeDBManager db;
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton,femaleRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        newEmployee = new Employee();
        db = new EmployeeDBManager(this);

        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(myGoToBack);

        btnAdd = (Button)findViewById(R.id.addEmployee);


        fname = (EditText)findViewById(R.id.FName);
        lname = (EditText)findViewById(R.id.LName);
        dept = (EditText)findViewById(R.id.Departments);
        salary = (EditText)findViewById(R.id.Salary);

        radioGroup = (RadioGroup) findViewById(R.id.radio_gender);
        maleRadioButton = (RadioButton) findViewById(R.id.radio_male);
        femaleRadioButton = (RadioButton) findViewById(R.id.radio_female);

        final String firstname = fname.getText().toString().trim();
        final String lastname = lname.getText().toString().trim();
        final String departments = dept.getText().toString().trim();
        final String sal = salary.getText().toString().trim();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_male) {
                    newEmployee.setGender("Male");
                } else if (checkedId == R.id.radio_female) {
                    newEmployee.setGender("Female");
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Not checked options",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputsAreCorrect(firstname,lastname,departments,sal)){
                    newEmployee.setFirstname(fname.getText().toString());
                    newEmployee.setLastname(lname.getText().toString());
                    newEmployee.setDepartments(dept.getText().toString());
                    newEmployee.setSalary(salary.getText().toString());
                    db.addEmployee(newEmployee);
                    Toast t = Toast.makeText(AddEmployee.this, "Employee has been added successfully !", Toast.LENGTH_SHORT);
                    t.show();
                    Intent i = new Intent(AddEmployee.this,EmployeeMenu.class);
                    startActivity(i);
                }
            }
        });

    }

    private View.OnClickListener myGoToBack = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Back();
        }
    };

    public void Back(){
        Intent i = new Intent(AddEmployee.this,EmployeeMenu.class);
        startActivity(i);
    }
    private boolean inputsAreCorrect(String firstname,String lastname,String departments,String sal){
        if(fname.getText().toString().length() == 0){
            fname.setError("Please enter First Name");
            fname.requestFocus();
            return false;
        }
        if(lname.getText().toString().length() == 0){
            lname.setError("Please enter Last Name");
            lname.requestFocus();
            return false;
        }

        if(dept.getText().toString().length() == 0){
            dept.setError("Please enter Departments");
            dept.requestFocus();
            return false;
        }
        if(salary.getText().toString().length() == 0){
            salary.setError("Please enter Salary");
            salary.requestFocus();
            return false;
        }
        return true;
    }
}
