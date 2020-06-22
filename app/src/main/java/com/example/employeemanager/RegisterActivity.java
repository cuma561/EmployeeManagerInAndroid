package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button back;
    Button register;
    LRDBManager db;
    EditText e1,e2,e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new LRDBManager(this);

        e1 = (EditText)findViewById(R.id.Email);
        e2 = (EditText)findViewById(R.id.Password);
        e3 = (EditText)findViewById(R.id.ConfPassword);

        back = (Button)findViewById(R.id.Back);
        back.setOnClickListener(myBackListener);

        register = (Button)findViewById(R.id.Register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                if(s1.equals("")|| s2.equals("") || s3.equals("")){
                    Toast.makeText(getApplicationContext(),"Empty Data",Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                }
                else
                {
                    if(s2.equals(s3)){
                        Boolean chkemail = db.chkemial(s1);
                        if(chkemail == true){
                            Boolean insert = db.insert(s1,s2);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(),"Registered Successfull",Toast.LENGTH_LONG).show();
                                e1.setText("");
                                e2.setText("");
                                e3.setText("");
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Not Registered",Toast.LENGTH_SHORT).show();
                                e1.setText("");
                                e2.setText("");
                                e3.setText("");
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Email Already exists",Toast.LENGTH_LONG).show();
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password do not match",Toast.LENGTH_LONG).show();
                        e1.setText("");
                        e2.setText("");
                        e3.setText("");
                    }
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
        Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(i);
    }
}
