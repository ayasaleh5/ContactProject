package com.example.contactproject;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactproject.db.DBHelper;
import com.example.contactproject.db.Data;

public class RegisterActivity extends AppCompatActivity {

    EditText username, passwordet, repassword;
    private SQLiteDatabase db;
    Button btcreate;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.etName);
        passwordet = findViewById(R.id.etPassword);
        repassword = findViewById(R.id.etretypePas);
        TextView loginView = findViewById(R.id.tvlogin);
        loginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        //connect to db
        DBHelper helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        //sharedprefs
         preferences = getSharedPreferences("user", MODE_PRIVATE);

    }
    public void register(View view){
        String name = username.getText().toString();
        String password = passwordet.getText().toString();
        String password1 =  repassword.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)&&!TextUtils.equals(password,password1) ){
            saveUser(name, password);
        }
        else
        {
            Toast.makeText(this,"invalid data try again", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUser(String name, String password){
        ContentValues values = new ContentValues();
        values.put(Data.USER_COL_NAME,name);
        values.put(Data.USER_COL_PASS,password);
        long row = db.insert(Data.USER_TABLE_NAME, null,values);
        if (row>0){
            preferences.edit().putBoolean("login", true).commit();
            Toast.makeText(RegisterActivity.this,"User created",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
        }

    }

}
