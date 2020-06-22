package com.example.employeemanager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

public class EmployeeAdapter extends ArrayAdapter<Employee> {

    Context context;
    int layoutRes;
    List<Employee> employeeList;
    EmployeeDBManager db;

    public EmployeeAdapter(Context context, int layoutRes, List<Employee> employeeList, EmployeeDBManager db) {
        super(context, layoutRes, employeeList);

        this.context = context;
        this.layoutRes = layoutRes;
        this.employeeList = employeeList;
        this.db = db;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutRes, null);
        final Employee employee = employeeList.get(position);

        TextView textViewFName = view.findViewById(R.id.textViewFName);
        textViewFName.setText(employee.getFirstname());

        TextView textViewLName = view.findViewById(R.id.textViewLName);
        textViewLName.setText(employee.getLastname());

        TextView textViewGender = view.findViewById(R.id.textViewGender);
        textViewGender.setText(employee.getGender());

        TextView textViewDepartment = view.findViewById(R.id.textViewDepartment);
        textViewDepartment.setText(employee.getDepartments());

        TextView textViewSalary = view.findViewById(R.id.textViewSalary);
        textViewSalary.setText(employee.getSalary());

        view.findViewById(R.id.btnGoToUpdateEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateEmployee(employee);
            }
        });

        view.findViewById(R.id.btnDeleteEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteEmployee(employee);
            }
        });

        return view;
    }
    public void UpdateEmployee(final Employee employee){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.dialog_update_employee, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editTextFName = view.findViewById(R.id.editFName);
        editTextFName.setText(employee.getFirstname());

        final EditText editTextLName = view.findViewById(R.id.editLName);
        editTextLName.setText(employee.getLastname());

        final RadioGroup radioGroup = view.findViewById(R.id.radio_gender);
        radioGroup.check(employee.getGender().equals("Male") ? R.id.radio_male : R.id.radio_female);

        final EditText editTextDepartments = view.findViewById(R.id.editDepartment);
        editTextDepartments.setText(employee.getDepartments());

        final EditText editTextSalary = view.findViewById(R.id.editSalary);
        editTextSalary.setText(employee.getSalary());

        view.findViewById(R.id.btnUpdateEmployee).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioButton;
                int checkId = radioGroup.getCheckedRadioButtonId();
                radioButton = view.findViewById(checkId);
                String fname = editTextFName.getText().toString().trim();
                String lname = editTextLName.getText().toString().trim();
                String dept = editTextDepartments.getText().toString().trim();
                String sal = editTextSalary.getText().toString().trim();
                String gender = radioButton.getText().toString().trim();

                if(fname.isEmpty()){
                    editTextFName.setError("First Name can't by empry");
                    editTextFName.requestFocus();
                    return;
                }
                if(lname.isEmpty()){
                    editTextLName.setError("Last Name can't by empry");
                    editTextLName.requestFocus();
                    return;
                }
                if(dept.isEmpty()){
                    editTextDepartments.setError("Departments can't by empry");
                    editTextDepartments.requestFocus();
                    return;
                }
                if(sal.isEmpty()){
                    editTextSalary.setError("Salary can't by empry");
                    editTextSalary.requestFocus();
                    return;
                }
                if(db.updateEmployee(employee.getEmpid(),fname,lname,gender,dept,Double.parseDouble(sal)))
                {
                    Toast.makeText(context,"Employee Update Successfull",Toast.LENGTH_LONG).show();
                    loadEmployeesFromDatabaseAgain();
                }
                alertDialog.dismiss();
            }
        });

        view.findViewById(R.id.btnCancelUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    public void DeleteEmployee(final Employee employee){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete Employee");
        builder.setMessage("Are you sure ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(db.deleteEmployee(employee.getEmpid())){
                    loadEmployeesFromDatabaseAgain();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void loadEmployeesFromDatabaseAgain() {

        Cursor cursor = db.getAllEmployees();

        employeeList.clear();
        if (cursor.moveToFirst()) {
            do {
                employeeList.add(new Employee(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                ));
            } while (cursor.moveToNext());
        }
        notifyDataSetChanged();
    }

}
