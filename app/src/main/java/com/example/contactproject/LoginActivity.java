package com.example.contactproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contactproject.db.DBHelper;
import com.example.contactproject.db.Data;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    private SQLiteDatabase db;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.etName);
        password = findViewById(R.id.etPassword);
        TextView registerView = findViewById(R.id.tvRegister);
        registerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
        //connect to db
        DBHelper helper = new DBHelper(this);
        db = helper.getReadableDatabase();

        //sharedprefs
        preferences = getSharedPreferences("user", MODE_PRIVATE);
    }

    public void login(View v){
        String name  = username.getText().toString();
        String pass = password.getText().toString();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pass)){
            boolean b = isUser(name , pass);
            if (b){
                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                preferences.edit().putBoolean("login",true);
                finish();
            }
            else {
                Toast.makeText(this,"invalid user", Toast.LENGTH_SHORT).show();

            }
        }
        else
        {
            Toast.makeText(this,"all fields required", Toast.LENGTH_SHORT).show();
        }


    }
    private boolean isUser(String name, String pass){
        String cols [] = {Data.USER_COL_NAME,Data.USER_COL_PASS};
        Cursor cursor = db.query(Data.USER_TABLE_NAME,cols,Data.USER_COL_NAME+"="+name+" and "+Data.USER_COL_PASS+" = "+pass,null,null,null,null);
        return cursor.moveToNext();

    }
}
