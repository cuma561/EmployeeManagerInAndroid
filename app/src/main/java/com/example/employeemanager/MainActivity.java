package com.example.employeemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGoToLogin = (Button)findViewById(R.id.btnGoToLoginApplication);
        btnGoToLogin.setOnClickListener(myGoToLogin);
    }

    private View.OnClickListener myGoToLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GoToLogin();
        }
    };

    public void GoToLogin()
    {
        Intent i = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(i);
    }
}
