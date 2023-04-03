package com.example.newsapi.ui;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapi.R;
import com.example.newsapi.sqliteHelper.Database;

public class RegistrationActivity extends AppCompatActivity {


    String NameHolder, EmailHolder, PasswordHolder, MobileHolder;

    EditText regName, regMobile, regEmail, regPassword;
    RadioGroup gender;
    Button reg_btn;
    TextView login_btn;

    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;


    String F_Result = "Not_Found";
    private Database db;
    Cursor cursor;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_activtiy);

        regName = findViewById(R.id.reg_name);
        regEmail = findViewById(R.id.reg_email);
        regMobile = findViewById(R.id.reg_mobile);
        regPassword = findViewById(R.id.reg_password);
        gender = findViewById(R.id.gender);
        reg_btn = findViewById(R.id.reg_btn);
        login_btn = findViewById(R.id.login_btn);

        db = new Database(RegistrationActivity.this);

        //SharedPreferences
        preferences = getSharedPreferences("login", MODE_PRIVATE);
        editor = preferences.edit();



        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.male_btn:
                        // do operations specific to this selection
                        break;
                    case R.id.female_btn:
                        // do operations specific to this selection
                        break;
                }
            }
        });


        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating SQLite database if dose n't exists

                String name = regName.getText().toString();
                String mobile = regMobile.getText().toString();
                String email = regEmail.getText().toString();
                String password = regPassword.getText().toString();
                db.register(name, mobile, password,email);

                editor.putString("mobile", mobile);
                editor.putString("password", password);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegistrationActivity.this, DashboardActivity.class));

            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

    }


}


