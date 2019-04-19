package com.example.contactproject.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, Data.DB_NAME, null, Data.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "create table " + Data.USER_TABLE_NAME+"(" +Data.USER_COL_ID+"integer primary key autoincrement, "+
                Data.USER_COL_NAME+" text, "+Data.USER_COL_PASS+" text )";
        sqLiteDatabase.execSQL(sql);

        String sql1 = "create table " + Data.FRIENDS_TABLE_NAME+"(" +Data.FRIENDS_COL_ID+"integer primary key autoincrement, "+
                Data.FRIENDS_COL_NAME+" text, "+Data.FRIENDS_COL_PHONE+"text, "+Data.FRIENDS_COL_EMAIL+" text )";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql1);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "droptable "+Data.USER_TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);

    }
}
