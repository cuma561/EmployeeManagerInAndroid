package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button back;
    Button login;
    TextView textGoToRegistered;
    EditText e1,e2;
    LRDBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new LRDBManager(this);

        back = (Button)findViewById(R.id.Back);
        back.setOnClickListener(myBackListener);

        textGoToRegistered = (TextView)findViewById(R.id.notRegistered);
        textGoToRegistered.setOnClickListener(myGoToRegister);

        e1 = (EditText)findViewById(R.id.Email);
        e2 = (EditText)findViewById(R.id.Password);

        login = (Button)findViewById(R.id.LoggedIn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean Chkemailpass = db.emailpassword(email,password);
                if(email.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(),"Empty Data",Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");
                }
                else if(Chkemailpass == true){
                    Intent i = new Intent(LoginActivity.this,EmployeeMenu.class);
                    startActivity(i);
                    e1.setText("");
                    e2.setText("");
                    Toast.makeText(getApplicationContext(),"Logged is Successfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Login is not successfully",Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");
                }
            }
        });
    }
    private View.OnClickListener myBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Back();
        }
    };
    public void Back(){
        Intent i = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(i);
    }

    private View.OnClickListener myGoToRegister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GoToRegister();
        }
    };

    public void GoToRegister(){
        Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }
}
