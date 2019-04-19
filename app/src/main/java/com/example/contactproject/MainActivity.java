package com.example.contactproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //check from shared
        final boolean b = getSharedPreferences("user", MODE_PRIVATE).getBoolean("login",false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (b) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
                else{
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));

                }

            }
        }, 3000);
    }
}
