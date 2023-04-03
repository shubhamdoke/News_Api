package com.example.newsapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.newsapi.R;
import com.example.newsapi.sqliteHelper.Database;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn, regBtn;
    EditText Mobile, Password ;
    Database db;

    SharedPreferences.Editor editor;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Mobile = findViewById(R.id.user_mobile);
        Password = findViewById(R.id.user_password);
        loginBtn = findViewById(R.id.user_login_btn);
        regBtn= findViewById(R.id.user_registration_btn);

        db = new Database(LoginActivity.this);


        //SharedPreferences
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        editor = preferences.edit();


        //Adding click listener to log in button.
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String mobile = Mobile.getText().toString();
                    String password = Password.getText().toString();

                try {
                    boolean checkUsernamePassword=db.login(mobile, password);

                    if (checkUsernamePassword)
                    {
                        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("mobile",mobile);
                        editor.putString("password",password);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Username/Password incorrect",Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }


            }
        });

        // Adding click listener to register button.
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

        if (preferences.contains("mobile")){
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }

    }
}